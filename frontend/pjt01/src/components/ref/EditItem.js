import React from 'react';
import "./css/item.css"
import minus from "../../img/minus.png";
import { useHistory } from 'react-router-dom';


function EditItem() {
  const history=useHistory();
  function onClick() {
    history.push('/refridgerator/edit')
  }

    return (
      <div className='item' >
        <div className='ref_icon'><img src={minus} alt="minus" className='ref_btn_img' onClick={()=> onClick()}></img></div>
        <div className='itemText'>삭제하기</div>
      </div>
    );
  }
  
  export default EditItem;
  