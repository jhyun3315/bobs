import React from 'react';
import "./css/item.css"
import plus from "../../img/plus.png";
import { useHistory } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';

function AddItem() {
  // const [getUserItem,setgetUserItem] =useState([]);

  const history = useHistory()
  useEffect(() => {
    // const url="https://i8b304.p.ssafy.io/api/refriges/user";
    // axios.post(url,{
      
    // })
    //   .then(function(response) {
    //     setgetUserItem(response.data);
    //     console.log("성공");
    // })
    //   .catch(function(error) {
    //       console.log("실패");
    // })
  
    
  }, [])
  

  function onClick() {
    history.push('/refridgerator/add')
  }
    return (
        <div className='item' onClick={()=> onClick()} >
          <div className='ref_icon'><img src={plus} alt="plus" className='ref_btn_img' ></img></div>
          <div className='itemText'>추가하기</div>
        </div>
    );
  }
  
  export default AddItem;
  