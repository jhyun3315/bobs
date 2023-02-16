import React from 'react';
import "./css/item.css"
import nambi from "../../img/nambi.png";
import { useHistory, useLocation } from 'react-router-dom';
import axios from 'axios';
import { useEffect } from 'react';

function GetItem(props) {
  const history=useHistory();
  const location = useLocation();
  const item=props.item;
  const url="https://i8b304.p.ssafy.io/api/recipes/recommendations";
  
  function gorecipe(){
    var itemarray=[]
    for (let index = 0; index < item.length; index++) {
      itemarray =[...itemarray,
       item[index].ingredient_name
      ];
   }
   console.log(props.item)
   var data = localStorage.getItem("id");
   const datainput={
    "user_id":data,
    "selectedIngredients" : itemarray
   }
  console.log(datainput)
    var config = {
      method: 'post',
      url: url,
      headers: { 
        'Content-Type': 'application/json'
      },
      data: datainput
    };
    axios(config)
      .then(function(response) {
          console.log(response)
      })
      .catch(function(error) {
          console.log("실패",error);
      })
      
  //  var data = localStorage.getItem("id");
  //  const datainput={
  //   "user_id":data,
  //   "selectedIngredients" : item
  //  }
  //   var config = {
  //     method: 'post',
  //     url: url,
  //     headers: { 
  //       'Content-Type': 'application/json'
  //     },
  //     data: datainput
  //   };
  //   axios(config)
  //     .then(function(response) {

  //       history.push({pathname: "/recipe",state:{recipe : response,checkrecom:true}})
  //       console.log(response.data)
  //     })
  //     .catch(function(error) {
  //         console.log("실패",error);
  //     })

    history.push({pathname: "/recipe",state : {recipe : item,check:true}})
  }

    return (
    <div className='item' onClick={()=>gorecipe()}>
        <div className='ref_icon'><img src={nambi} alt="nambi" className='nambi_img' ></img></div>
        <div className='itemText'>추천받기</div>
      </div>
    );
  }
  
  export default GetItem;
  