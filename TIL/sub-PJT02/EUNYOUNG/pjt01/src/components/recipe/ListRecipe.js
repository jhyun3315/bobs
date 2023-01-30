import React from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import { useState } from 'react'
import './css/ListRecipe.css'
import search_icon from '../../img/search_item.png'
import delete_icon from '../../img/delete_btn.png'


function ListRecipe() {

  const [text, setText] = useState('');

  return (
    <div className='listrecipe'>
      <img src={search_icon} alt="search" className="search_item" />
      <img src={delete_icon} alt="delete" className="delete_item" />
      <input type="text" value={text}
      onChange={(e) => {
        setText(e.target.value);
      }}
      className='search_list' />
      

      <div className='recipes'>
        {Array.from(Array(10), i => <ItemRecipe key={i} />)}
      </div>
    </div>
  )
}

export default ListRecipe