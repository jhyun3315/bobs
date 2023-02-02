import React from 'react';
import "./css/item.css"
import minus from "../../img/minus.png";


function EditItem() {
    return (
      <div className='item'>
        <div className='ref_icon'><img src={minus} alt="minus" className='ref_btn_img' ></img></div>
        <div className='itemText'>삭제하기</div>
      </div>
    );
  }
  
  export default EditItem;
  