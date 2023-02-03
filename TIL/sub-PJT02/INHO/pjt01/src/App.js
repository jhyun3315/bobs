import { Route, Switch } from 'react-router-dom';
import CommunityPage from './communityPage';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";
import RecipePage from './recipeDetailPage';
import RefridgeratorPage from './refridgeratorPage';
import StudyPage from './studyPage';
import NavBar from './components/navBar';
import StudyDetail from './StudyDetail';
import CommunityPostDetail from './CommunityPostDetail';
import CommunityPostCreate from './CommunityPostCreate';

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
          <Route exact path={"/community"} component={CommunityPage} />
          <Route path={"/community/:id"} component={CommunityPostDetail} />
          <Route path={"/oauth/callback/kakao"} component={KakaoRedirectHandler} />
          <Route path={"/communityCreate"} component={CommunityPostCreate} />
        </Switch>
        <NavBar>

        </NavBar>
    </div>
  );
}

export default App;
