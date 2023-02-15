import React from 'react';
import './css/mainPage.css';
import SearchBar from'../components/SearchBar';
import { useState, useEffect } from 'react';
import AllergyButton from '../components/main/AllergyButton';
import axios from 'axios';


function MainPage() {
  // const allergy_list = data;
  const [items, setItems] = useState([])
  const [allergylist, setallergylist] = useState([]);
  const [updateAllergyList, setUpdateAllergyList] = useState([])
  const [getallergy,setgetAllergy] =useState([]);
  const [name,setName] =useState("");
  const [profile,setProfile] =useState("")
  const [id, setId] = useState("")

  const url="https://i8b304.p.ssafy.io/api"
  // const url="http://localhost:8080"

  useEffect(()=>{
    setName(localStorage.getItem("name"))
    setProfile(localStorage.getItem("profile"))
    setId(localStorage.getItem("id"))
    const iddata = JSON.stringify(id);
    var config = {
      method: 'post',
      url: url+"/allergy/user",
      headers: { 
        'Content-Type': 'application/json'
      },
      data : iddata
    };
    axios(config)  
    .then(function (response) {
      setallergylist(response.data.data)
      setUpdateAllergyList(response.data.data)
    })
    .catch(function (error) {
      console.log(error);
    });
    
    axios.get(url+"/ingredients"
    ).then((res) => {
      const getdata=res.data;
      delete getdata.result;
      setgetAllergy(res.data.data);
    })
  }, []) 

  const addallergy=(item)=>{
    if (!allergylist.includes(item))
    setallergylist([ item, ...allergylist ])
  };
  
  // 알레르기 삭제 기능
  const deleteItem=(item)=>{
    // 재료에서 검색해 선택한 것은 ingredient_id
    if (item.ingredient_id) {
      const newAllergyList = allergylist?.filter((allergy) => allergy.ingredient_id !== item.ingredient_id )
      setallergylist(newAllergyList)
    }
    // 기존에 있던 알레르기는 allergy_id
    if (item.allergy_id) {
      const newAllergyList = allergylist?.filter((allergy) => allergy.allergy_id !== item.allergy_id )
      setallergylist(newAllergyList)
    }
  };
  const goAdd= () => {
    const updateList = allergylist?.filter((item) => !updateAllergyList.includes(item))
    const deleteList = updateAllergyList?.filter((item) => !allergylist.includes(item))
    let apiList = [] // api로 보낼 리스트
    // 업데이트 할 재료, ingredient_id
    updateList?.map((item) => 
      apiList.push({ "ingredient_id" : item.ingredient_id, "is_deleted" : false})
    )
    // 삭제 할 재료, allergy_id
    deleteList?.map((item) => 
      apiList.push({ "ingredient_id" : item.allergy_id, "is_deleted" : true })
    )

    const putData = { 
      "user_id": id,
      "allergy_list": apiList
    }
    console.log(putData);
    axios.put(url + "/allergy", JSON.stringify(putData), {
      headers: {
        "Content-Type": "application/json",
      }
    })
    .then((res) => console.log(res))
    .catch((err) => console.log(err))
  }

  const renderAllergy = allergylist?.map((item, index) => {
    return (
      <AllergyButton key={index} item={item}  
      deleteItem={deleteItem}/>
    )
  })

  return (
    <div className='mainpage'>
      <div className="logo">밥스</div>             
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
            data = {getallergy}
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

