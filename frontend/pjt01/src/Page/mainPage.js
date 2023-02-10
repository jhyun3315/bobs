import React from 'react';
import './css/mainPage.css';
import logo from "../img/logo.png";
import proImg from "../img/profile_default.png";
import SearchBar from'../components/SearchBar';
import data from './item.data.js'
import { useState, useEffect } from 'react';
import AllergyButton from '../components/main/AllergyButton';


function MainPage() {

  // const allergy_list = data;
  const [items, setItems] = useState(data)
  const [allergylist, setallergy_list] = useState([]);
  const [delallergyitem,setallergyitem] =useState([]);

  useEffect(()=>{
    console.log(delallergyitem)
  }, [delallergyitem]) 

  const addallergy=(item)=>{
    if (!allergylist.includes(item))
    setallergy_list([ item, ...allergylist ])
  };

  // const addItem=(item)=>{
  //   setallergyitem([...allergyitem, item ])
  // };

  const deleteItem=(item)=>{
    setallergyitem([...delallergyitem, item ])
  };

  const renderAllergy = allergylist?.map((item, index) => {
    return (
      <AllergyButton key={index} item={item}  
      deleteItem={deleteItem}/>
    )
  })

  return (
    <div className='mainpage'>
      <div className="logo"><img src={logo} alt="logo" id='logo_img'/></div>             
        <div className="mypage">
          <div className="kakaodata">
            <img src={proImg} alt="profile" className="profileImg"/>
            <div className="profileName"><p id='nickName'>익명의 코끼리</p></div>
          </div>
          <div className='main_allergy'>
            <div id="top_allergy">
              <div id='choice_allergy'>나의 알레르기</div>
              <button id='allergy_save'>저장</button>
            </div>
            <div className="allergyBox">
              {renderAllergy}      
            </div>          
          </div>           
        </div>
        <div className='search_alergy'>
          <div className='your_alergy'>당신의 알레르기를 추가해 주세요.</div>
          <SearchBar type="text" id='allergy_search_input'
            placeholder={"알레르기를 검색하세요."}
            data = {data}
            setItem = {setItems} />
        </div> 
        <div className='add_alergy'>
        {
          items?.map((item) => {
            return <div key={item.id} className="add_search_item" onClick={() => addallergy(item) }>{item.name}</div>
          })
        }
        </div>     
    </div>        
  );
}

export default MainPage;

