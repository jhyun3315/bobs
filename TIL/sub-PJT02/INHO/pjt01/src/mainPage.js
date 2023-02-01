import Allergy from './components/main/Allergy';
import FirstMain from './components/main/FirstMain';
import Main from './components/main/Main';
import {Link} from 'react-router-dom';
import './css/mainPage.css';
import logo from "./img/Bobs_logo.png";
import proImg from "./img/nor.jpeg";
function MainPage() {


  return (
    <div>
      <div className="container">
        <div><img src={logo} alt="logo" className="logo"/></div>
        
        
        <div className="mypage">
          <div className="kakaodata">
            <div className="profileImg"><img src={proImg} alt="profile"/></div>
            <div className="profileName"><b>이름</b></div>

          </div>
          
          <div className="search">
            <div><b>회원님의 알러지</b></div>
            <form action="#">
              <input type="search" required/>
                <i className="bi bi-search"></i>
              <div id="clear-btn"></div>
            </form>
          </div>

          <Allergy>
            <Link to="/login"> Login</Link>    
          </Allergy>        
          
            
            <FirstMain></FirstMain>
            <Main></Main>
        </div>

        
      </div>
        
    </div>
  );
}

export default MainPage;
