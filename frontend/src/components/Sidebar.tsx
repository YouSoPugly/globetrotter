import {useCountry} from "../contexts/CountryContext.tsx";

export const Sidebar = () => {
  const {selectD, setSelectD} = useCountry();

  const wikiUrl = selectD ? `https://en.wikipedia.org/wiki/${encodeURIComponent(selectD.properties.ADMIN)}`
    : undefined;

  const closeSidebar = () => setSelectD(null);

  return <div
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
      backgroundColor: '#242424',
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
          style={{border: 'none'}}
        />
      </>
    ) : (
      <p style={{opacity: 0.7}}>Select a country to view details</p>
    )}
  </div>
}