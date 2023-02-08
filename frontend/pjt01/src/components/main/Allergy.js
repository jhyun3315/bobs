import React, { useState } from 'react';
import '../../css/Allergy.css';
import AllergyButton from './AllergyButton';

function Allergy(props) {
  const allergy_list = [{
    "itemid":"우유",
  },
  {
    "itemid":"사과",
  }
  ];

  const [allergylist, setallergy_list] = useState(allergy_list);
  const addItem=(item)=>{
    setallergy_list([...allergylist, item ])
    console.log(allergylist);
  };

  const checkItem=(id)=>{
    console.log(id)
    
  }

  
  // const item = [{
  //   "itemid":"우유"
  // },
  // {
  //   "itemid":"땅콩"
  // }, 
  // {
  //   "itemid":"치즈"
  // },
  // {
  //   "itemid":"돼지고기"
  // },
  // {
  //   "itemid":"계란"
  // } 
  // ];
  // const renderAllergy = allergy_list.map((item,index) => {
  //   return (
  //     <AllergyButton key={index} item={item}  onClick={() =>checkItem(item)}/>
  //   )
  // })
  return (
    <div className='allergy'>
      <div id="top_allergy">
        <div id='choice_allergy'>선택된 항목</div>
        <button id='delete_all'>전체 삭제</button>
      </div>
      <div className="allergyBox">
          {allergy_list.map((item,index) => {
              console.log(index)
            return (
              <AllergyButton key={index} item={item}  onClick={() =>checkItem(item)}/>
            )
          })}
      </div>        
    </div>
  );
}

export default Allergy;
  