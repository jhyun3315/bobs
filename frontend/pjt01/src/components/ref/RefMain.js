import React, { useState,useEffect } from 'react';
import AddItem from './AddItem';
import EditItem from './EditItem';
import GetItem from './GetItem';
import Allergy from './AllergyItem'
import SelectedItem from './SelectedItem';
import axios from 'axios';
import './css/RefMain.css'

function RefMain() {
  useEffect(() => {
    console.log(getitem)
    //요청 보낼 api 주소
    const url2 = "https://www.naver.com";
    axios.get(url2,{
      params : {
        user_id: ""
      }
    })
      .then(function(response) {
        setgetUserItem(response.data);
        console.log("성공");
    })
      .catch(function(error) {
          console.log("실패");
    })
  
    
  }, [getUserItem,getitem])
  

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
  {
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

  // const first = ['우유', '양파', '파', '마늘', '청경채', '양상추', '파인애플', '사과']
  // const second = ['돼지고기', '소고기', '간장', '고추장', '된장', '쌈장', '이준호', '이인호']
  const [f_item,setf_item] = useState(first);
  const [s_item,sets_item] = useState(first);
  const [getUserItem,setgetUserItem] =useState({});
  const [getitem,setgetitem] =useState([]);
  const [checked, setChecked] = useState(false);


  const addItem=(item)=>{
    setgetitem([...getitem, item ])
  };

  const deleteItem=(item)=>{
    setgetitem(getitem.filter(aitem => aitem !== item));
  };

  return (
  <div className='ref_main'>
    <div className="ref_title">나의 냉장고</div>
      <div className="itembox">
        <AddItem></AddItem>
        { checked === true ? <EditItem /> : <Allergy />}
        <GetItem></GetItem>
      </div>

      <div className='text'>우선소비</div>
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
  );
}
  
export default RefMain;
  