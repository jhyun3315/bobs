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

function RefMain() {


  const [f_item,setf_item] = useState([]);
  const [s_item,sets_item] = useState([]);
  const [getUserItem,setgetUserItem] =useState([]);
  // const [getUserItem,setgetUserItem] =useState([]);
  const [getitem,setgetitem] =useState([]);
  const [checked, setChecked] = useState(false);
  const [fixchecked, setFixChecked] = useState(false);
  // const [name,setName] =useState("");
  // const [profile,setProfile] =useState("")
  const [id,setId] =useState(localStorage.getItem("id"))
  const [checkedasync, setCheckedasync] = useState(false);
  // const local_id = localStorage.getItem("id")
  const url="https://i8b304.p.ssafy.io/api/refriges";
  // const url="http://localhost:8080/refriges";

  useEffect(() => {
    // setName(localStorage.getItem("name"))
    // setProfile(localStorage.getItem("profile"))
    // setId(localStorage.getItem("id"))
    if(getitem.length ===0){
      setChecked(false);
    }

    var data = JSON.stringify(id);
    var config = {
      method: 'post',
      url: url,
      headers: { 
        'Content-Type': 'application/json'
      },
      data : data
    };
    axios(config)
      .then(function(response) {
          console.log(response.data.data)
          setgetUserItem(response.data.data);
          // console.log(response.data.data);
          setf_item(getUserItem.filter(item => item.refrige_ingredient_prior === true)
          )
        
          sets_item(getUserItem.filter(item => item.refrige_ingredient_prior === false)
          )
          setCheckedasync(true);
      })
      .catch(function(error) {
          console.log("실패",error);
      })
  }, [checkedasync,checked])



  const addItem=(item)=>{
    console.log(getitem);
    setChecked(true);
    setgetitem([...getitem, item ])
  };

  const deleteItem=(item)=>{
    console.log(getitem)
    if(getitem.length===1){
      setChecked(false);
    }
    setgetitem(getitem.filter(items => items !== item));
  };

  const changeitemToPriority=(item)=>{
    const itemarray={ingredient_id:item.ingredient_id,ingredient_name:item.ingredient_name}
    sets_item(s_item.filter(items => items.ingredient_id !== item.ingredient_id));
    setf_item([...f_item, itemarray ]);
  };
  const changeitemToNormal=(item)=>{
    const itemarray={ingredient_id:item.ingredient_id,ingredient_name:item.ingredient_name}
    setf_item(f_item.filter(items => items.ingredient_id !== item.ingredient_id));
    sets_item([...s_item, itemarray ]);
  };

  const onstatechange=()=>{
    console.log(f_item)
    console.log(s_item)
    const setf=f_item.map((items) => items.ingredient_id)
    const sets=s_item.map((items) => items.ingredient_id)
    var flist = []
    var slist = []
    console.log(sets)
    console.log(setf)
    for (let index = 0; index < setf.length; index++) {
      flist = [...flist, {
        "ingredient_id": setf[index],
        "is_deleted": false,
        "is_prior": true
      }];
    }

    for (let index = 0; index < sets.length; index++) {
      slist = [...slist, {
        "ingredient_id": sets[index],
        "is_deleted": false,
        "is_prior": false
      }];
    }

    if(flist.length!==0){
      console.log(flist)
      axios.put(url,
        {
          "user_id" : id,
          "ingredient_list":flist
        }    
      ).then((res)=>{
        console.log(res)
      }
      )
     
    }
    if(slist.length!==0){
      axios.put(url,
        {
          "user_id" : id,
          "ingredient_list":slist
        }    
      )
    }



  }

  return (
  <div className='ref_main'>
    <div className="ref_title">나의 냉장고</div>
      <div className="itembox">
        <AddItem ></AddItem>
        { checked === true ? <EditItem item={getitem}/> : <Allergy />}
        <GetItem  item={getUserItem}></GetItem>
      </div>
      <div className='priority_item_box'>
        <div className='text'>우선소비</div>
        <Toggle
          checked = {fixchecked}
          onChange = {(e) => {
            setFixChecked(e.target.checked)
            if(fixchecked){
              onstatechange()
            }
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
              // onstatechange={onstatechange}
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
              // onstatechange={onstatechange}
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
  