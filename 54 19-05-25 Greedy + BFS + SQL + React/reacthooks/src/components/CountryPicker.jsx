import React, { useState, useMemo, useEffect } from 'react';
import allCountries from './countries.json';   // ≈ 250 items

function CountryPicker() {
  const [region, setRegion]       = useState('Europe');
  const [pickedId, setPickedId]   = useState(null);

  /* ──────────────────────────────────────────────────────────
     1️⃣ MEMOIZED DERIVATION
     Only re-runs if either `allCountries` or `region` changes.
  ────────────────────────────────────────────────────────── */
  const countriesInRegion = useMemo(() => {
    // pretend this is slow (complex sort, i18n collation, etc.)
    return allCountries
      .filter(c => c.region === region)
      .sort((a, b) => a.name.localeCompare(b.name));
  }, [allCountries, region]);

  /* ──────────────────────────────────────────────────────────
     2️⃣ SIDE-EFFECT
     Save the chosen country to localStorage whenever `pickedId`
     changes so we can restore it on the next visit.
  ────────────────────────────────────────────────────────── */
  useEffect(() => {
    console.log("Countries Fetched");
    if (pickedId !== null) {
      localStorage.setItem('lastCountry', pickedId);
    }
  }, [pickedId]);

  // Restore previous pick on first mount (another effect)
  useEffect(() => {
    const saved = localStorage.getItem('lastCountry');
    if (saved) setPickedId(saved);
  }, []);

  return (
    <section style={{ maxWidth: 340, margin: '2rem auto' }}>
      <h2>Pick a country</h2>

      {/* region filter */}
      <label>
        Region&nbsp;
        <select value={region} onChange={e => setRegion(e.target.value)}>
          {['Africa','Americas','Asia','Europe','Oceania'].map(r =>
            <option key={r}>{r}</option>
          )}
        </select>
      </label>

      {/* country list */}
      <ul style={{ maxHeight: 160, overflowY: 'auto', border: '1px solid #ddd' }}>
        {countriesInRegion.map(c => (
          <li
            key={c.id}
            onClick={() => setPickedId(c.id)}
            style={{
              cursor: 'pointer',
              background: pickedId === c.id ? '#e0f7ff' : 'transparent'
            }}
          >
            {c.name}
          </li>
        ))}
      </ul>

     {pickedId && (() => {
      const selectedCountry = allCountries.find(c => c.id === pickedId);
      return selectedCountry ? (
        <p style={{ marginTop: 8 }}>
          You picked <strong>{selectedCountry.name}</strong>.
        </p>
      ) : null;
    })()}
    </section>
  );
}

export default CountryPicker;
