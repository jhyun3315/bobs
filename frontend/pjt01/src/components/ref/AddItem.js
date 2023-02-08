import React from 'react';
import "./css/item.css"
import plus from "../../img/plus.png";
import { useHistory } from 'react-router-dom';

function AddItem() {

    const history = useHistory();

    return (
        <div className='item' onClick={history.push("/refidgerator/add")}>
          <div className='ref_icon'><img src={plus} alt="plus" className='ref_btn_img' ></img></div>
          <div className='itemText'>추가하기</div>
        </div>
    );
  }
  
  export default AddItem;
  