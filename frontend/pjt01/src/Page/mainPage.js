import React from 'react';
import './css/mainPage.css';
// import proImg from "../img/profile_default.png";
import SearchBar from'../components/SearchBar';
import data from './item.data.js'
import { useState, useEffect } from 'react';
import AllergyButton from '../components/main/AllergyButton';


function MainPage() {
  // const allergy_list = data;
  const [items, setItems] = useState(data)
  const [allergylist, setallergy_list] = useState([]);
  const [delallergyitem,setallergyitem] =useState([]);
  const [name,setName] =useState("");
  const [profile,setProfile] =useState("")
  const [id,setId] =useState("")
  useEffect(()=>{
    setName(localStorage.getItem("name"))
    setProfile(localStorage.getItem("profile"))
    setId(localStorage.getItem("id"))
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
      <div className="logo">Bobs</div>             
        <div className="mypage">
          <div className="kakaodata">
            <img src={profile} alt="profile" className="profileImg"/>
            <div className="profileName"><p id='nickName'> {name}</p></div>
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
            setData = {setItems} />
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

