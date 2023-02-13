import React from 'react';
import "./css/item.css"
import minus from "../../img/minus.png";


function EditItem(props) {
    function del(){
      const deliteme=props.item
          // axios.put(url,{
          // })
          //   .then(function(response) {
          //     setgetUserItem(response.data);
          //     console.log("성공");
          // })
          //   .catch(function(error) {
          //       console.log("실패");
          // })
    }

    return (
      <div className='item' >
        <div className='ref_icon'><img src={minus} alt="minus" className='ref_btn_img' onClick={()=> del()}></img></div>
        <div className='itemText'>삭제하기</div>
      </div>
    );
  }
  
  export default EditItem;
  