import { Route, Routes } from 'react-router-dom';
import LoginView from './loginView';
import MainView from "./mainView";



function App() {
  return (
    <div className="App">
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <Routes>
          <Route path="/" element={<MainView />} />
          <Route path="/login" element={<LoginView />} />
          
        </Routes>
    </div>
  );
}

export default App;
