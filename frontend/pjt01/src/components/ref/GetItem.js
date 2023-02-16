import React from 'react';
import "./css/item.css"
import nambi from "../../img/nambi.png";
import { useHistory, useLocation } from 'react-router-dom';

function GetItem(props) {
  const history=useHistory();
  const location = useLocation();
  
  function gorecipe(){
    history.push({pathname: "/recipe",state : {recipe : "recommend"}})
  }

    return (
    <div className='item' onClick={()=>gorecipe()}>
        <div className='ref_icon'><img src={nambi} alt="nambi" className='nambi_img' ></img></div>
        <div className='itemText'>추천받기</div>
      </div>
    );
  }
  
  export default GetItem;
  