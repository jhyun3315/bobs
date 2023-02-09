import React, { useState,useEffect} from 'react';
import './Allergy.css';
import AllergyButton from './AllergyButton';

function Allergy() {

  const allergy_list = [{
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

  const [allergylist, setallergy_list] = useState(allergy_list);
  const [delallergyitem,setallergyitem] =useState([]);

  useEffect(()=>{
    // console.log(delallergyitem)
  }, [delallergyitem]) 

  const addallergy=(item)=>{
    setallergy_list([...allergylist, item ])
  };

  // const addItem=(item)=>{
  //   setallergyitem([...allergyitem, item ])
  // };

  const deleteItem=(item)=>{
    setallergyitem([...delallergyitem, item ])
  };

  const renderAllergy = allergy_list.map((item, index) => {
    return (
      <AllergyButton key={index} item={item}  
      deleteItem={deleteItem}/>
    )
  })
  return (
    <div className='allergy'>
      <div id="top_allergy">
        <div id='choice_allergy'>나의 알레르기</div>
        <button id='delete_all'>전체 삭제</button>
      </div>
      <div className="allergyBox">
        {renderAllergy}      
      </div>        
    </div>
  );
}

export default Allergy;
  
