import { Route, Switch } from 'react-router-dom';
import CommunityPage from './communityPage';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import LoginPage from './loginPage';
import MainPage from "./mainPage";
import RecipePage from './recipePage';
import RefridgeratorPage from './refridgeratorPage';
import AddItemPage from './addItemPage'
import RecipeDetail from './recipeDetailPage'
import StudyPage from './studyPage';
import StudyDetailPage from './studyDetailPage';
import NavBar from './components/navBar';
import WebRtc from './webRtcPage'
import CommunityPostDetail from './CommunityPostDetail';
import CommunityPostCreate from './CommunityPostCreate';
import "./css/App.css"
import {MobileView} from 'react-device-detect';
import VideoRoom from './VideoRoom';

function App() {
  return (
    <div className="App">
      <MobileView>
        
      <div className='Main'>
        <Switch>
          <Route path={"/videoroom/:id"} component={VideoRoom} />
          <Route exact path={"/"} component={MainPage} />
          <Route path={"/login"} component={LoginPage} />
          <Route path={"/study/web/:room"} component={WebRtc} />
          <Route path={"/study/:id"} component={StudyDetailPage} />
          <Route exact path={"/study"} component={StudyPage} />
          <Route exact path={"/refridgerator"} component={RefridgeratorPage} />
          <Route path={"/refridgerator/add"} component={AddItemPage} />
          <Route path={"/recipe/:id"} component={RecipeDetail} />
          <Route exact path={"/recipe"} component={RecipePage} />
          <Route path={"/community/:id"} component={CommunityPostDetail} />
          <Route path={"/community"} component={CommunityPage} />
          <Route path={"/communityCreate"} component={CommunityPostCreate} />
          <Route path={"/oauth/callback/kakao"} component={KakaoRedirectHandler} />

          </Switch>
          </div>
          <NavBar></NavBar>
        </MobileView>
    </div>
  );
}

export default App;
