'''

Reference implementation skeleton (Python, framework‑free)
=========================================================
This is a teaching skeleton: it shows control flow, typed tools, budgets, and safe prompts. Replace call_llm() with your model API. Note we never ask the model to reveal chain‑of‑thought, only structured JSON or final bullets


'''

# minimal_agent.py
from dataclasses import dataclass, field
from typing import List, Dict, Any, Tuple
import json, time, pathlib

# ---------- Tool interfaces (typed, allow-listed) ----------

@dataclass
class Chunk:
    doc_id: str
    title: str
    year: int
    page: int
    text: str
    cite_label: str  # e.g., "Smith 2021, p.4"

class DocSearch:
    """Read-only retrieval over pre-chunked PDFs."""
    def __init__(self, index):
        self.index = index  # simple in-memory index: List[Chunk]

    def __call__(self, query: str, k: int = 3, section: str = "") -> List[Dict[str, Any]]:
        # MINIMAL retrieval: keyword match score; replace with real embeddings later.
        q = (section + " " + query).lower().split()
        scored = []
        for ch in self.index:
            score = sum(1 for t in q if t in ch.text.lower())
            if score > 0:
                scored.append((score, ch))
        scored.sort(key=lambda x: x[0], reverse=True)
        return [vars(c) for _, c in scored[:k]]

class SaveOutput:
    """Write-only sink for brief & sources with size checks."""
    def __call__(self, brief_markdown: str, sources: List[Dict[str, Any]]) -> Dict[str, str]:
        if len(brief_markdown) > 10_000:
            raise ValueError("Brief too large.")
        outdir = pathlib.Path("out"); outdir.mkdir(exist_ok=True)
        brief_path = outdir / "brief.md"
        sources_path = outdir / "sources.json"
        brief_path.write_text(brief_markdown, encoding="utf-8")
        sources_path.write_text(json.dumps(sources, indent=2), encoding="utf-8")
        return {"brief_path": str(brief_path), "sources_path": str(sources_path)}

# ---------- Agent state & configuration ----------

@dataclass
class Budgets:
    max_tool_calls: int = 12
    max_time_s: int = 60

@dataclass
class Memory:
    working: Dict[str, List[Dict[str, Any]]] = field(default_factory=dict)   # section -> recent chunks
    notes: Dict[str, str] = field(default_factory=dict)                      # section -> issue tag

@dataclass
class TraceEvent:
    state: str
    tool: str
    args: Dict[str, Any]
    duration_ms: int

# ---------- Utilities (LLM wrappers) ----------

SYSTEM_PROMPT = (
    "You are ResearchBriefAgent. Use only allowed tools. "
    "Do not reveal private reasoning. Produce either JSON or final bullets with citations."
)

def call_llm(prompt: str) -> str:
    """
    Replace with your model API call.
    Must return plain text. For planner/reflector it should be valid JSON.
    For drafting, return markdown bullets with inline [cite_label].
    """
    raise NotImplementedError("Plug in your LLM here.")

def jloads_safe(s: str) -> Dict[str, Any]:
    try:
        return json.loads(s)
    except json.JSONDecodeError:
        return {}

# ---------- The agent controller (S0..S6) ----------

class ResearchBriefAgent:
    def __init__(self, docsearch: DocSearch, save: SaveOutput, budgets: Budgets):
        self.docsearch, self.save, self.budgets = docsearch, save, budgets
        self.tool_calls = 0
        self.trace: List[TraceEvent] = []
        self.t0 = time.time()

    def _check_budgets(self):
        if self.tool_calls > self.budgets.max_tool_calls:
            raise RuntimeError("Tool-call budget exceeded.")
        if (time.time() - self.t0) > self.budgets.max_time_s:
            raise RuntimeError("Time budget exceeded.")

    def _tool(self, name: str, fn, **kwargs):
        t_start = time.time()
        out = fn(**kwargs)
        self.tool_calls += 1
        self._check_budgets()
        self.trace.append(TraceEvent(state=self.state, tool=name, args=kwargs,
                                     duration_ms=int((time.time()-t_start)*1000)))
        return out

    def run(self, topic: str) -> Tuple[str, str]:
        mem = Memory()
        self.state = "S1-PLAN"

        # ---- S1: Plan sections (JSON) ----
        plan_prompt = (
            f"{SYSTEM_PROMPT}\n"
            f"TOPIC: {topic}\n"
            "Propose 3–5 section headings and the evidence needed. "
            'Output JSON: {"sections":[{"name":..., "need":...}]}'
        )
        plan = jloads_safe(call_llm(plan_prompt))
        sections = [s["name"] for s in plan.get("sections", [])][:5] or ["Background","Key Findings","Open Questions"]

        # ---- S2 & S3: Retrieve + Draft for each section ----
        section_bullets = {}
        for sec in sections:
            self.state = "S2-RETRIEVE"
            chunks = self._tool("DocSearch", self.docsearch, query=topic, k=3, section=sec)
            mem.working[sec] = chunks

            self.state = "S3-DRAFT"
            draft_prompt = (
                f"{SYSTEM_PROMPT}\n"
                f"SECTION: {sec}\nCHUNKS:\n{json.dumps(chunks, indent=2)}\n"
                "Write ≤3 bullets, each ≤2 lines, each with exactly one inline citation "
                "using the provided cite_label. If unsupported, omit the claim."
            )
            section_bullets[sec] = call_llm(draft_prompt)

        # ---- S4: Reflect (one pass) ----
        self.state = "S4-REFLECT"
        brief_md = self._assemble_brief(section_bullets)
        reflect_prompt = (
            f"{SYSTEM_PROMPT}\nRubric: relevance, fidelity, brevity, coverage.\n"
            f"Draft:\n{brief_md}\n"
            'Output JSON: {"missing_sections":[], "long_bullets":[], '
            '"uncited_claims":[], "low_diversity": false}'
        )
        issues = jloads_safe(call_llm(reflect_prompt)) or {}
        needs_retry = bool(issues.get("uncited_claims") or issues.get("long_bullets") or issues.get("low_diversity"))

        if needs_retry:
            # Focused retry: re-retrieve and redraft only problematic sections if any named;
            # otherwise retry the longest section.
            target_secs = issues.get("missing_sections") or [sections[-1]]
            for sec in target_secs[:1]:  # one focused retry
                self.state = "S2-RETRIEVE-RETRY"
                chunks = self._tool("DocSearch", self.docsearch, query=topic, k=3, section=sec)
                mem.working[sec] = chunks
                self.state = "S3-DRAFT-RETRY"
                draft_prompt = (
                    f"{SYSTEM_PROMPT}\n"
                    f"SECTION: {sec}\nCHUNKS:\n{json.dumps(chunks, indent=2)}\n"
                    "Revise ≤3 bullets with strict citations and brevity."
                )
                section_bullets[sec] = call_llm(draft_prompt)
            brief_md = self._assemble_brief(section_bullets)

        # ---- S5: Gate ----
        if self._violates_gate(brief_md):
            brief_md += "\n\n**Note:** Some claims lacked sufficient evidence; they were omitted."

        # ---- S6: Save ----
        self.state = "S6-SAVE"
        sources = self._collect_sources(mem.working)
        paths = self._tool("SaveOutput", self.save, brief_markdown=brief_md, sources=sources)

        # Persist trace (observability)
        pathlib.Path("out/trace.jsonl").write_text(
            "\n".join(json.dumps(vars(e)) for e in self.trace), encoding="utf-8"
        )
        return paths["brief_path"], paths["sources_path"]

    # ---------- helpers ----------
    def _assemble_brief(self, section_bullets: Dict[str, str]) -> str:
        parts = []
        for sec, bullets in section_bullets.items():
            parts.append(f"### {sec}\n{bullets.strip()}\n")
        parts.append("### Open questions\n- …")
        return "\n".join(parts)

    def _collect_sources(self, working: Dict[str, List[Dict[str, Any]]]) -> List[Dict[str, Any]]:
        seen = {}
        for sec, chunks in working.items():
            for ch in chunks:
                key = ch["doc_id"]
                seen.setdefault(key, {"doc_id": key, "title": ch["title"], "year": ch["year"], "pages_used": set()})
                seen[key]["pages_used"].add(ch["page"])
        out = []
        for v in seen.values():
            v["pages_used"] = sorted(list(v["pages_used"]))
            out.append(v)
        return out

    def _violates_gate(self, brief_md: str) -> bool:
        # Naive checks: line length and presence of [citation]
        lines = [ln for ln in brief_md.splitlines() if ln.strip().startswith("- ")]
        too_long = any(len(ln) > 200 for ln in lines)
        uncited = any("[" not in ln or "]" not in ln for ln in lines if ln.startswith("- "))
        return too_long or uncited
