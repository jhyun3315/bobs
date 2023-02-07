import React from 'react';
import '../../css/Allergy.css';
import x_btn from "../../img/x_btn.png"

function Allergy(props) {
  const allergy_list = [{
    "itemid":"우유"
  },
  ];


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
  const renderAllergy = allergy_list.map(item => {
    return (
      <div className='allergyitem'>
        <p className="itemText">{item.itemid}</p>
        <img src={x_btn} className="x_btn" alt="x"/>
      </div>
    )
  })
  return (
    <div className='allergy'>
      <div id="top_allergy">
        <div id='choice_allergy'>선택된 항목</div>
        <button id='delete_all'>전체 삭제</button>
      </div>
      <div className="allergyBox">
        {renderAllergy}      
      </div>        
    </div>
  );
}

export default Allergy;
  