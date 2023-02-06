import React from 'react';
import "./css/item.css"
import nambi from "../../img/nambi.png";

function GetItem() {
    return (
      <div className='item'>
        <div className='ref_icon'><img src={nambi} alt="nambi" className='nambi_img' ></img></div>
        <div className='itemText'>추천받기</div>
      </div>
    );
  }
  
  export default GetItem;
  