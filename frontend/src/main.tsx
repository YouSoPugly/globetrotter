import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {CountryProvider} from "./contexts/CountryContext.tsx";
import {UserProvider} from "./contexts/UserContext.tsx";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <CountryProvider>
    <UserProvider>
      <App/>
    </UserProvider>
    </CountryProvider>
  </StrictMode>,
)
