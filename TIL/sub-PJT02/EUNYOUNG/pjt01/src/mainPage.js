import React from 'react';
import Allergy from './components/main/Allergy';
import FirstMain from './components/main/FirstMain';
import Main from './components/main/Main';
import {Link} from 'react-router-dom';
import './css/mainPage.css';
import logo from "./img/logo.png";
import proImg from "./img/nor.PNG";
import search_icon from './img/search_item.png'
import delete_icon from './img/delete_btn.png'


function MainPage() {


  return (
    <div className='mainpage'>
      <div className="logo"><img src={logo} alt="logo" id='logo_img'/></div>             
        <div className="mypage">
          <div className="kakaodata">
            <img src={proImg} alt="profile" className="profileImg"/>
            <div className="profileName"><p id='nickName'>익명의 코끼리</p></div>
          </div>          
          <div className="search">
            <div className='your_alergy'><b id='your_alergy'>당신의 알레르기를 추가해 주세요.</b></div>
            <input type="search" required className='alergy_input' placeholder='검색어를 입력하세요.'/>
            <img src={search_icon} alt="search" id="search_item" />
            <img src={delete_icon} alt="delete" id="delete_item" />
          </div>

          <Allergy>
            <Link to="/login"> Login</Link>    
          </Allergy>        
          
            
            <FirstMain></FirstMain>
            <Main></Main>
        </div>

        
      </div>
        
  );
}

export default MainPage;
