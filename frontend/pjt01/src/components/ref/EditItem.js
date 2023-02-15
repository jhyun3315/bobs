import React from 'react';
import "./css/item.css"
import minus from "../../img/minus.png";
import axios from 'axios';


function EditItem(props) {
    const url="https://i8b304.p.ssafy.io";
    const local_id = localStorage.getItem("id");
    // const url="http://localhost:8080";
    function del(){
      console.log(props.item)
      const list=props.item
      var inlist=[]
      for (let index = 0; index < list.length; index++) {
         inlist =[...inlist,{
          "ingredient_id" : list[index],
          "is_deleted" : true,
          "is_prior" : false
         }];
      }
      console.log(list)
      console.log(inlist)
      axios.put(url+"/api/refriges",
        {
          "user_id" : local_id,
          "ingredient_list":inlist
        }
        
      )
    }

    return (
      <div className='item' >
        <div className='ref_icon'><img src={minus} alt="minus" className='ref_btn_img' onClick={()=> del()}></img></div>
        <div className='itemText'>삭제하기</div>
      </div>
    );
  }
  
  export default EditItem;
  