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

  

  const first = [{
    "itemid":"우유",
  },
  {
    "itemid":"사과",
  },
  {
    "itemid":"돼지고기",
  },
  {
    "itemid":"오리고기",
  },

  ];

  const second = [{
      "itemid":"소고기",
    },
    {
      "itemid":"복숭아",
    },
    {
      "itemid":"고등어",
    },
    {
      "itemid":"땅콩",
    }

  ];

  const [f_item,setf_item] = useState([]);
  const [s_item,sets_item] = useState([]);
  const [getUserItem,setgetUserItem] =useState({});
  const [getitem,setgetitem] =useState([]);
  const [checked, setChecked] = useState(false);
  const [fixchecked, setFixChecked] = useState(false);


  useEffect(() => {
    if(getitem.length ===0){
      setChecked(false);
    }
    //요청 보낼 api 주소
    const url="https://i8b304.p.ssafy.io/api/refrigerators/:user_id";
    axios.get(url,)
      .then(function(response) {
        setgetUserItem(response.data);
        console.log("성공");
    })
      .catch(function(error) {
          console.log("실패");
    })
    
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
    const itemarray={itemid:item}
    sets_item(s_item.filter(items => items.itemid !== item));
    setf_item([...f_item, itemarray ]);
  };
  const changeitemToNormal=(item)=>{
    const itemarray={itemid:item}
    setf_item(f_item.filter(items => items.itemid !== item));
    sets_item([...s_item, itemarray ]);
  };




  return (
  <div className='ref_main'>
    <div className="ref_title">나의 냉장고</div>
      <div className="itembox">
        <AddItem></AddItem>
        { checked === true ? <EditItem /> : <Allergy />}
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
  