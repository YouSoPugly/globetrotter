import {useEffect, useState} from "react";
import type {Country} from "../types.tsx";
import {useCountry} from "../contexts/CountryContext.tsx";
import Globe from "react-globe.gl";

let explored: string[] = [
  'FR', 'US'
];

export const World = () => {
  const [countries, setCountries] = useState<{ features: Country[] }>({features: []});
  const [hoverD, setHoverD] = useState<Country | null>(null);
  const {selectD, setSelectD} = useCountry();

  useEffect(() => {
    fetch('/countries.geojson').then(res => res.json()).then(setCountries);
  }, []);

  const handlePolygonHover = (polygon: object | null) => {
    setHoverD(polygon as Country | null);
  };
  const handlePolygonClick = (polygon: object) => {
    setSelectD(polygon as Country);
  };
  return (
    <div style={{position: 'absolute', top: 0, left: 0, right: 0, bottom: 0}}>
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
        polygonLabel={({properties: d}: any) => `
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
  );
};