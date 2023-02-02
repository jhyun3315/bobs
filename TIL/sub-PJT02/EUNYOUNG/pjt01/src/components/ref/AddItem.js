import React from 'react';
import "./css/item.css"
import plus from "../../img/plus.png";
import { Link } from 'react-router-dom';

function AddItem() {
    return (
      <Link to={"/refridgerator/add"}>
        <div className='item'>
          <div className='ref_icon'><img src={plus} alt="plus" className='ref_btn_img' ></img></div>
          <div className='itemText'>추가하기</div>
        </div>
    </Link>
    );
  }
  
  export default AddItem;
  