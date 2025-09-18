from mcp.server import Server
from mcp.types import Tool, Schema, ToolCall
# ---------- Define Tools ----------
docsearch_tool = Tool(
	name="DocSearch",
	description="Retrieve text chunks with cite_labels",
	input_schema=Schema.object({
		"query": Schema.string(),
		"k": Schema.integer(default=3),
		"section": Schema.string()
	}),
	output_schema=Schema.array(Schema.object({
		"doc_id": Schema.string(),
		"title": Schema.string(),
		"year": Schema.integer(),
		"page": Schema.integer(),
		"text": Schema.string(),
		"cite_label": Schema.string()
	}))
)

saveoutput_tool = Tool(
	name="SaveOutput",
	description="Persist brief and sources",
	input_schema=Schema.object({
		"brief_markdown": Schema.string(),
		"sources": Schema.array(Schema.object({
			"doc_id": Schema.string(),
			"pages_used": Schema.array(Schema.integer())
		}))
	}),
	output_schema=Schema.object({
		"brief_path": Schema.string(),
		"sources_path": Schema.string()
	})
)
# ---------- Agent Controller ----------
class ResearchBriefAgentMCP:
	def __init__(self, server: Server):
		self.server = server
		self.server.add_tool(docsearch_tool, self.docsearch_impl)
		self.server.add_tool(saveoutput_tool, self.saveoutput_impl)

	async def run(self, topic: str):
		plan = await self.server.call_llm("Propose sections", context={"topic": topic})
		retrieval = await self.server.call_tool("DocSearch", {"query": topic, "k": 3})
		draft = await self.server.call_llm("Draft bullets", context={"chunks": retrieval})
		reflection = await self.server.call_llm("Check rubric", context={"draft": draft})
		await self.server.call_tool("SaveOutput", {"brief_markdown": draft, "sources": self._collect_sources(retrieval)})

	async def docsearch_impl(self, call: ToolCall):
		# Example implementation, replace with real retrieval logic
		return [{
			"doc_id": "doc123",
			"title": "Role of Ubiquitin in Cancer",
			"year": 2021,
			"page": 4,
			"text": "p53 degradation is tightly controlled by MDM2 ubiquitination.",
			"cite_label": "Smith 2021, p.4"
		}]

	async def saveoutput_impl(self, call: ToolCall):
		# Example implementation, replace with real save logic
		return {"brief_path": "out/brief.md", "sources_path": "out/sources.json"}

	def _collect_sources(self, retrieval):
		return [
			{
				"doc_id": r["doc_id"],
				"pages_used": [r["page"]]
			} for r in retrieval
		]