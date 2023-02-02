import { Route, Switch } from 'react-router-dom';
import CommunityPage from './communityPage';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";
import RecipePage from './recipePage';
import RefridgeratorPage from './refridgeratorPage';
import StudyPage from './studyPage';
import NavBar from './components/navBar';
import StudyDetail from './StudyDetail';


import './App.css';


function App() {
  return (
    <div className="App">

        <Switch>
          <Route exact path={"/"} component={MainPage} />
          <Route path={"/login"} component={LoginPage} />
          <Route exact path={"/study"} component={StudyPage} />
          <Route path={"/study/:id"} component={StudyDetail} />
          <Route path={"/refridgerator"} component={RefridgeratorPage} />
          <Route path={"/recipe"} component={RecipePage} />
          <Route path={"/community"} component={CommunityPage} />
          <Route path={"/oauth/callback/kakao"} component={KakaoRedirectHandler} />
        </Switch>
        <NavBar>

        </NavBar>
    </div>
  );
}

export default App;
