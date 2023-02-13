import React, { useState,useEffect } from 'react';
import AddItem from './AddItem';
import EditItem from './EditItem';
import GetItem from './GetItem';
import Allergy from './AllergyItem'
import SelectedItem from './SelectedItem';
import SelectedItemMove from './SelectedItemMove';
import axios from 'axios';
import './css/RefMain.css'
import Toggle from '../Toggle.component';
import ref from './ref.data';

function RefMain() {


  const [f_item,setf_item] = useState([]);
  const [s_item,sets_item] = useState([]);
  const [getUserItem,setgetUserItem] =useState(ref.data);
  // const [getUserItem,setgetUserItem] =useState([]);
  const [getitem,setgetitem] =useState([]);
  const [checked, setChecked] = useState(false);
  const [fixchecked, setFixChecked] = useState(false);
  const [name,setName] =useState("");
  const [profile,setProfile] =useState("")
  const [id,setId] =useState("")

  useEffect(() => {
    setName(localStorage.getItem("name"))
    setProfile(localStorage.getItem("profile"))
    setId(localStorage.getItem("id"))

    if(getitem.length ===0){
      setChecked(false);
    }
    //요청 보낼 api 주소
    // const url="https://i8b304.p.ssafy.io/api/refrigerators/:user_id";
    // axios.get(url,)
    //   .then(function(response) {
    //     setgetUserItem(response.data);
    //     console.log("성공");
    // })
    //   .catch(function(error) {
    //       console.log("실패");
    // })
    console.log(getUserItem)
    const i=getUserItem.filter(item => item.refrige_ingredient_prior === true)
    console.log(i)
    setf_item(getUserItem.filter(item => item.refrige_ingredient_prior === true)
    )

    sets_item(getUserItem.filter(item => item.refrige_ingredient_prior === false)
    )

    
  }, [])

  const addItem=(item)=>{
    setChecked(true);
    setgetitem([...getitem, item ])
  };

  const deleteItem=(item)=>{
    setgetitem(getitem.filter(items => items !== item));
  };
  const changeitemToPriority=(item)=>{
    const itemarray={ingredient_name:item}
    sets_item(s_item.filter(items => items.ingredient_name !== item));
    setf_item([...f_item, itemarray ]);
  };
  const changeitemToNormal=(item)=>{
    const itemarray={ingredient_name:item}
    setf_item(f_item.filter(items => items.ingredient_name !== item));
    sets_item([...s_item, itemarray ]);
  };




  return (
  <div className='ref_main'>
    <div className="ref_title">나의 냉장고</div>
      <div className="itembox">
        <AddItem ></AddItem>
        { checked === true ? <EditItem recipe={getUserItem}/> : <Allergy />}
        <GetItem></GetItem>
      </div>
      <div className='priority_item_box'>
        <div className='text'>우선소비</div>
        <Toggle
          checked = {fixchecked}
          onChange = {(e) => {
            setFixChecked(e.target.checked)
          }}
          offstyle="off"
          onstyle="on"
          text="수정 모드">
        </Toggle>
      </div>
      { fixchecked === false ?  
      <div>
        <div className='priority_item'  onClick={()=>{setChecked(true)}}>
          {
            f_item.map((item, index) => {
              return <SelectedItem key={index} item={item}  
              addItem={addItem}
              deleteItem={deleteItem}/>
            })
          }    
        </div>
          <div className='text'>일반</div>
          <div className='last_item'>
          {
            s_item.map((item, index) => {
              return <SelectedItem key={index} item={item}  
              addItem={addItem}
              deleteItem={deleteItem}/>
            })
          }
        </div>
      </div>
      : 
      <div>
        <div className='priority_item'>
          {
            f_item.map((item, index) => {
              return <SelectedItemMove key={index} item={item} check={true} 
              changeitemToNormal={changeitemToNormal}
              />
            })
          }    
        </div>
          <div className='text'>일반</div>
          <div className='last_item'>
          {
            s_item.map((item, index) => {
              return <SelectedItemMove key={index} item={item} check={false} 
              changeitemToPriority={changeitemToPriority}
              />
            })
          }
        </div>
      </div>
      }
  </div>
  );
}
  
export default RefMain;
  