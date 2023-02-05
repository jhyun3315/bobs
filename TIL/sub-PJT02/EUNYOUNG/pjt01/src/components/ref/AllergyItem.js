import React from 'react';
import "./css/item.css"
import allergy from "../../img/allergy.png";
import { useHistory } from 'react-router-dom';

function EditItem() {

  const  history = useHistory();

    return (
      <div className='item' onClick={()=>{history.push('/')}}>
        <div className='ref_icon'><img src={allergy} alt="allergy" className='ref_btn_img' ></img></div>
        <div className='itemText'>알레르기</div>
      </div>
    );
  }
  
  export default EditItem;
  