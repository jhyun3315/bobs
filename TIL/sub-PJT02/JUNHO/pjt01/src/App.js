import { Route, Switch } from 'react-router-dom';
import CommunityPage from './communityPage';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";
import RecipePage from './recipePage';
import RefridgeratorPage from './refridgeratorPage';
import AddItemPage from './addItemPage'
import RecipeDetail from './RecipeDetail'
import StudyPage from './studyPage';
import StudyDetail from './StudyDetail';
import CommunityPostDetail from './CommunityPostDetail';
import CommunityPostCreate from './CommunityPostCreate';
import NavBar from './components/navBar';
import "./css/App.css"
import {MobileView} from 'react-device-detect';

function App() {
  return (
    <div className="App">
      <MobileView>
        <Switch>
          <Route exact path={"/"} component={MainPage} />
          <Route path={"/login"} component={LoginPage} />
          <Route exact path={"/study"} component={StudyPage} />
          <Route path={"/study/:id"} component={StudyDetail} />
          <Route exact path={"/refridgerator"} component={RefridgeratorPage} />
          <Route path={"/refridgerator/add"} component={AddItemPage} />
          <Route exact path={"/recipe"} component={RecipePage} />
          <Route path={"/community"} component={CommunityPage} />
          <Route path={"/community/:id"} component={CommunityPostDetail} />
          <Route path={"/communityCreate"} component={CommunityPostCreate} />
          <Route path={"/recipe/:id"} component={RecipeDetail} />
          <Route path={"/oauth/callback/kakao"} component={KakaoRedirectHandler} />

          </Switch>
          <NavBar></NavBar>
        </MobileView>
    </div>
  );
}

export default App;
