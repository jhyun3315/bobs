import React from 'react';
import './css/mainPage.css';
// import proImg from "../img/profile_default.png";
import SearchBar from'../components/SearchBar';
import data from './item.data.js'
import { useState, useEffect } from 'react';
import AllergyButton from '../components/main/AllergyButton';
import axios from 'axios';


function MainPage() {
  // const allergy_list = data;
  const [items, setItems] = useState([])
  const [allergylist, setallergylist] = useState([]);
  const [delallergyitem,setdelallergyitem] =useState([]);
  const [name,setName] =useState("");
  const [profile,setProfile] =useState("")
  const [id,setId] =useState("")
  // const url="https://i8b304.p.ssafy.io"
  const url="http://localhost:8080"
  useEffect(()=>{
    setName(localStorage.getItem("name"))
    setProfile(localStorage.getItem("profile"))
    setId(localStorage.getItem("id"))
    var data = JSON.stringify(id);
    var config = {
      method: 'post',
      url: url+"/api/allergy/user",
      headers: { 
        'Content-Type': 'application/json'
      },
      data : data
    };
    axios(config)
    .then(function (response) {
      setallergylist(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });


    console.log(delallergyitem)
    console.log(allergylist)
  }, [delallergyitem]) 

  const addallergy=(item)=>{
    if (!allergylist.includes(item))
    setallergylist([ item, ...allergylist ])
  };

  // const addItem=(item)=>{
  //   setallergyitem([...allergyitem, item ])
  // };

  const deleteItem=(item)=>{
    
    setdelallergyitem([...delallergyitem, item ])
  };

  const goAdd=()=>{
    console.log(allergylist)
    const list=allergylist.map((item)=>item.ingredient_id)
    var inlist=[]
    for (let index = 0; index < list.length; index++) {
       inlist =[...inlist,{
        "ingredient_id" : list[index],
        "is_deleted" : false
       }];
    }
    console.log(list)
    console.log(inlist)
    axios.put(url+"/api/allergy",
      {
        "user_id" : id,
          "allergy_list": inlist

      }
    )
  }

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
              <button id='allergy_save' onClick={()=>goAdd()}>저장</button>
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
            data = {data.data}
            setData = {setItems} />
        </div> 
        <div className='add_alergy'>
        {
          items?.map((item, index) => {
            return <div key={index} className="add_search_item" onClick={() => addallergy(item) }>{item.ingredient_name}</div>
          })
        }
        </div>     
    </div>        
  );
}

export default MainPage;

