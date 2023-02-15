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
  const [delitem,setdelitem] =useState([]);
  const [checked, setChecked] = useState(false);
  const [fixchecked, setFixChecked] = useState(false);
  const [name,setName] =useState("");
  const [profile,setProfile] =useState("")
  const [id,setId] =useState("")
  const [checkedasync, setCheckedasync] = useState(false);
  // const local_id = localStorage.getItem("id")
  const local_id = 5
  // const url="https://i8b304.p.ssafy.io/api/refriges";
  const url="http://localhost:8080/refriges";

  useEffect(() => {
    setName(localStorage.getItem("name"))
    setProfile(localStorage.getItem("profile"))
    setId(localStorage.getItem("id"))

    if(getitem.length ===0){
      setChecked(false);
    }

    var data = JSON.stringify(local_id);
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
          setgetUserItem(response.data.data);
          // console.log(response.data.data);
          setf_item(getUserItem?.filter(item => item.refrige_ingredient_prior === true)
          )
        
          sets_item(getUserItem?.filter(item => item.refrige_ingredient_prior === false)
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

  const onstatechange = () =>{
    
    const f_list = f_item.map((item) => item.ingredient_id)
    const s_list = s_item.map((item) => item.ingredient_id)  
    var inlist = []

    for (let index = 0; index < f_list.length; index++){
      inlist = [... inlist, {
        "ingredient_id" : f_list[index],
        "is_deleted" : false,
        "is_prior" : true
      }]

    for (let index = 0; index < s_list.length; index++){
      inlist = [... inlist, {
        "ingredient_id" : s_list[index],
        "is_deleted" : false,
        "is_prior" : false
      }]}
    
    console.log(f_list, s_list)
    console.log(inlist)
    
    axios.put(url,
    {
      "user_id" : local_id,
      "ingredient_list": inlist
    }).then((res) => console.log(res.data.data)).catch((e) => console.log(e))
    }}

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
            if (fixchecked) onstatechange();
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
            f_item?.map((item, index) => {
              return <SelectedItem key={index} item={item}  
              addItem={addItem}
              deleteItem={deleteItem}/>
            })
          }    
        </div>
          <div className='text'>일반</div>
          <div className='last_item'>
          {
            s_item?.map((item, index) => {
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
            f_item?.map((item, index) => {
              return <SelectedItemMove key={index} item={item} check={true} 
              setf_item = {setf_item}
              sets_item = {sets_item}
              f_item = {f_item}
              s_item = {s_item}
              />
            })
          }    
        </div>
          <div className='text'>일반</div>
          <div className='last_item'>
          {
            s_item?.map((item, index) => {
              return <SelectedItemMove key={index} item={item} check={false} 
              setf_item = {setf_item}
              sets_item = {sets_item}
              f_item = {f_item}
              s_item = {s_item}
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
