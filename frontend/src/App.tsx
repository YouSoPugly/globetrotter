import './App.css'
import {Sidebar} from "./components/Sidebar.tsx";
import {World} from "./components/World.tsx";

function App() {

  return (
    <>
        <div style={{ height: '100%', width:'100%', overflow: 'hidden' }}>
        <Sidebar />
        <World />
        </div>
    </>
  )
}

export default App
