import { Route, Routes } from 'react-router-dom';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";



function App() {
  return (
    <div className="App">
        <p>
          헤더이다
        </p>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/oauth/callback/kakao" element={<KakaoRedirectHandler/>} />
        </Routes>
    </div>
  );
}

export default App;
