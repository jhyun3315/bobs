import { Route, Switch  } from 'react-router-dom';
import CommunityPage from './Page/communityPage';
import KakaoRedirectHandler from './components/main/KakaoRedirectHandeler';
import FirstPage from './Page/firstPage';
import MainPage from "./Page/mainPage";
import RecipePage from './Page/recipePage';
import RefridgeratorPage from './Page/refridgeratorPage';
import AddItemPage from './Page/addItemPage';
import RecipeDetail from './Page/recipeDetailPage';
import StudyPage from './Page/studyPage';
import StudyDetailPage from './Page/studyDetailPage';
import StrudyCreatePage from './Page/studyCreatePage';
import NavBar from './components/navBar';
import CommunityDetailPage from './Page/communityDetailPage';
import CommunityCreatePage from './Page/communityCreatePage';
import "./App.css"
import {MobileView} from 'react-device-detect';
import VideoRoom from './Page/VideoRoom';
import NotFound from './Page/NotFound';
import RefridgeratorEditPage from './Page/RefridgeratorEditPage';
import EditItemPage from './Page/editItemPage';

function App() {
  return (
    <div className="App">
      <MobileView>
        
      <div className='Main'>
        <Switch>
          { /* 로그인 페이지 */ }
          <Route exact path={"/"} component={FirstPage} />
          { /* 화상 채팅방 */ }
          <Route path={"/videoroom/:id"} component={VideoRoom} />
          { /* 메인 페이지 */ }
          <Route exact path={"/main"} component={MainPage} />
          { /* 스터디 페이지 */ }
          <Route path={"/study/:id"} component={StudyDetailPage} />
          <Route exact path={"/study"} component={StudyPage} />
          <Route exact path={"/studycreate"} component={StrudyCreatePage} />
          <Route path={"/refridgerator/add"} component={AddItemPage} /> 
          <Route path={"/refridgerator/edit"} component={EditItemPage} />
          <Route exact path={"/refridgerator"} component={RefridgeratorPage} />
          <Route path={"/recipe/:id"} component={RecipeDetail} />
          <Route exact path={"/recipe"} component={RecipePage} />
          <Route path={"/community/:id"} component={CommunityDetailPage} />
          <Route path={"/community"} component={CommunityPage} />
          <Route path={"/communityCreate"} component={CommunityCreatePage} />
          <Route path={"/oauth/callback/kakao"} component={KakaoRedirectHandler} />
          <Route path={"/refridgerator/edit"} component={RefridgeratorEditPage} />
          <Route path={"/log"} component={FirstPage} />
          <Route path="*" component={NotFound} />

          
          </Switch>
          </div>
          <NavBar></NavBar>
        </MobileView>
    </div>
  );
}

export default App;
