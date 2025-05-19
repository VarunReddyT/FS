import React, { useMemo, useState } from 'react';

const countries = [
    { code: 'US', name: 'United States' },
    { code: 'CA', name: 'Canada' },
    { code: 'MX', name: 'Mexico' },
    { code: 'GB', name: 'United Kingdom' },
    { code: 'FR', name: 'France' },
    { code: 'DE', name: 'Germany' },
    { code: 'IT', name: 'Italy' },
    { code: 'ES', name: 'Spain' },
    { code: 'JP', name: 'Japan' },
    { code: 'CN', name: 'China' },
    { code: 'IN', name: 'India' },
    { code: 'BR', name: 'Brazil' }
]

export default function Country() {
    const [selectedCountry, setSelectedCountry] = useState('');

    const countryOptions = useMemo(() => {
        console.log('Mapping countries to options');
        return countries.map(country => (
            <option key={country.code} value={country.code}>
                {country.name}
            </option>
        ));
    });
    return (
        <div>
            <label className="block mb-2 font-semibold">Select Country:</label>
            <select
                value={selectedCountry}
                onChange={(e) => setSelectedCountry(e.target.value)}
                className="border p-2 rounded"
            >
                <option value="">-- Choose a country --</option>
                {countryOptions}
            </select>
            {selectedCountry && (
                <div className="mt-4">
                    <p className="mt-2 text-green-600">
                        You selected: <strong>{selectedCountry}</strong>
                    </p>
                </div>
            )}
        </div>
    );
}