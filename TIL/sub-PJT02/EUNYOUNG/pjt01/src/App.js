import { Route, Routes } from 'react-router-dom';
import CommunityPage from './communityPage';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";
import RecipePage from './recipePage';
import RefridgeratorPage from './refridgeratorPage';
import detailRecipePage from './detailRecipePage'
import StudyPage from './studyPage';
import NavBar from './components/navBar';
import "./css/App.css"
import {MobileView} from 'react-device-detect';


function App() {
  return (
    <div className="App">
      <MobileView>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/study" element={<StudyPage />} />
          <Route path="/refridgerator" element={<RefridgeratorPage />} />
          <Route path="/recipe" element={<RecipePage />} />
          <Route path="/community" element={<CommunityPage />} />
          <Route path="/detailrecipe" element={<detailRecipePage />} />
          <Route path="/oauth/callback/kakao" element={<KakaoRedirectHandler/>} />

          </Routes>
          <NavBar></NavBar>
        </MobileView>
    </div>
  );
}

export default App;
