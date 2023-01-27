import { Route, Routes } from 'react-router-dom';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";

function App() {
  return (
    <>
        
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/login" element={<LoginPage />} />
          
          <Route path="/oauth/callback/kakao" element={<KakaoRedirectHandler/>} />
        </Routes>
    </>
  );
}

export default App;
