/* eslint-disable @typescript-eslint/no-explicit-any */

import './App.css'
import Globe from 'react-globe.gl'
import {useEffect, useState} from "react";

type Country = {
    properties: {
        ADMIN: string;
        ISO_A2: string;
        GDP_MD_EST?: number;
        POP_EST?: number;
        [key: string]: any; // for extra fields
    };
    geometry?: any;
};

let explored : string[] = [
    'FR', 'US'
];

const World = () => {
    const [countries, setCountries] = useState<{features: Country[]}>({ features: []});
    const [hoverD, setHoverD] = useState<Country | null>(null);
    const [selectD, setSelectD] = useState<Country | null>(null);

    useEffect(() => {
        // load data
        fetch('/countries.geojson').then(res => res.json()).then(setCountries);
    }, []);

    const wikiUrl = selectD ? `https://en.wikipedia.org/wiki/${encodeURIComponent(selectD.properties.ADMIN)}`
        : undefined;

    const closeSidebar = () => setSelectD(null);

    const handlePolygonHover = (polygon: object | null) => {
        setHoverD(polygon as Country | null);
    };
    const handlePolygonClick = (polygon: object) => {
        setSelectD(polygon as Country);
    };
    return (
        <div style={{ height: '100%', width:'100%', overflow: 'hidden' }}>
            {/* Collapsible Sidebar */}
            <div
                style={{
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    height: '100%',
                    width: '30%',
                    minWidth: '420px',
                    padding: '0',
                    borderRight: '1px solid #ccc',
                    boxShadow: '2px 0 8px rgba(0,0,0,0.1)',
                    transform: selectD ? 'translateX(0)' : 'translateX(-100%)',
                    transition: 'transform 0.4s ease',
                    overflowY: 'hidden',
                    zIndex: 10,
                }}
            >
                {selectD ? (
                    <>
                        <button
                            onClick={closeSidebar}
                            style={{
                                position: 'absolute',
                                top: '10px',
                                right: '10px',
                                background: 'transparent',
                                border: 'none',
                                fontSize: '1.5rem',
                                cursor: 'pointer',
                                backgroundColor: '#242424'
                            }}
                        >
                            ✕
                        </button>
                        <iframe
                            src={wikiUrl}
                            title="Wikipedia"
                            width="100%"
                            height="100%"
                            style={{ border: 'none' }}
                        />
                    </>
                ) : (
                    <p style={{ opacity: 0.7 }}>Select a country to view details</p>
                )}
            </div>

            {/* Globe */}
            <div style={{ position: 'absolute', top: 0, left: 0, right: 0, bottom: 0}}>
                <Globe
                    globeImageUrl="//cdn.jsdelivr.net/npm/three-globe/example/img/earth-day.jpg"
                    backgroundImageUrl="//cdn.jsdelivr.net/npm/three-globe/example/img/night-sky.png"
                    lineHoverPrecision={0}
                    polygonsData={countries.features.filter(d => d.properties.ISO_A2 !== '')}
                    polygonAltitude={d => (d === hoverD ? 0.03 : 0.01)}
                    polygonCapColor={d => {
                        const country = d as Country
                        let col = explored.includes(country.properties.ISO_A2) ? 'rgba(0,255,0,0.3)' : 'rgba(255,0,0,0.3)'
                        if (d === selectD) col = col.replace(/0\.3\)/, '0.7)');
                        return col;
                        }}
                    polygonSideColor={() => 'rgba(0, 100, 0, 0.15)'}
                    polygonStrokeColor={() => '#111'}
                    polygonLabel={({ properties : d }: any) => `
                        <b>${d.ADMIN}</b>
                      `}
                    onPolygonHover={handlePolygonHover}
                    onPolygonClick={handlePolygonClick}
                    onPolygonRightClick={d => {
                        const country = d as Country
                        if (explored.includes(country.properties.ISO_A2)) explored = explored.filter(i => i !== country.properties.ISO_A2)
                        else explored.push(country.properties.ISO_A2)
                    }}
                    polygonsTransitionDuration={100}
                />
            </div>
        </div>
    );
};

function App() {

  return (
    <>
        <World></World>
    </>
  )
}

export default App
