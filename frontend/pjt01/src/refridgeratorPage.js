import React, { useState } from 'react';
import AddItem from './components/ref/AddItem';
import EditItem from './components/ref/EditItem';
import GetItem from './components/ref/GetItem';
import SelectedItem from './components/ref/SelectedItem';

import "./css/refridgeratorPage.css"

function RefridgeratorPage() {

  const first = ['우유', '양파', '파', '마늘', '청경채', '양상추', '파인애플', '사과']
  const second = ['돼지고기', '소고기', '간장', '고추장', '된장', '쌈장', '이준호', '이인호']
  const [f_item] = useState(first);
  const [s_item] = useState(second);

  return (
    <div className='refridgerator'>
      <div className="main">나의 냉장고</div>
      <div className="itembox">
        <AddItem></AddItem>
        <EditItem></EditItem>
        <GetItem></GetItem>
      </div>

        <div className='text'>우선소비</div>
        <div className='priority_item'>
        {
          f_item.map((item, index) => {
            return <SelectedItem item={item} key={index}/>
          })
        }    
      </div>
        <div className='text'>일반</div>
        <div className='last_item'>
        {
          s_item.map((item, index) => {
            return <SelectedItem item={item} key={index}/>
          })
        }
       </div>      
    </div>
  );
}

export default RefridgeratorPage;
