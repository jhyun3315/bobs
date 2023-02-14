import React, {useEffect} from 'react';
import Allergy from '../components/main/Allergy';
import './css/mainPage.css';
import logo from "../img/logo.png";
import proImg from "../img/profile_default.png";
import search_icon from '../img/search_item.png'
import delete_icon from '../img/delete_btn.png'
import { useState } from 'react'


function MainPage() {
  useEffect(() => {
    
  
    return () => {
      
    }
  }, [])
  
  const [text, setText] = useState('');

  return (
    <div className='mainpage'>
      <div className="logo"><img src={logo} alt="logo" id='logo_img'/></div>             
        <div className="mypage">
          <div className="kakaodata">
            <img src={proImg} alt="profile" className="profileImg"/>
            <div className="profileName"><p id='nickName'>익명의 코끼리</p></div>
          </div>
          <div className='main_allergy'><Allergy /></div>           
        </div>
        <div className='add_alergy'>
          <div className='your_alergy'>당신의 알레르기를 추가해 주세요.</div>
          <div className='allergy_search_input'>
            <div className='allergy_img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
            <input type="text" value={text} id='allergy_search_input'
              onChange={(e) => {
                setText(e.target.value);
              }}
              placeholder="검색어를 입력하세요"/>
            <div className='allergy_img_icon'><img src={delete_icon} alt="delete" className="delete_item" /></div>
          </div>
      </div>      
    </div>        
  );
}

export default MainPage;
