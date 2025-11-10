import './App.css'
import Sidebar from "./components/Sidebar.tsx";
import World from "./components/World.tsx";
import TopBar from "./components/Topbar.tsx";

function App() {

  return (
    <>
      <TopBar/>
      <div style={{height: '100%', width: '100%', overflow: 'hidden'}}>
        <Sidebar/>
        <World/>
      </div>
    </>
  )
}

export default App
