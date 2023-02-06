import React, { useRef } from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import { useState } from 'react'
import './css/ListRecipe.css'
import search_icon from '../../img/search_item.png'
import delete_icon from '../../img/delete_btn.png'
import Toggle from "../Toggle.component";
import data from './recipe.data.js'

function ListRecipe() {

  const [text, setText] = useState('');
  const [recipes] = useState(data);

  const [checked, setChecked] = useState(false)
  const onBtn = useRef(null);
  const offBtn = useRef(null);

  const onRecom = () => {
    onBtn.current.className += " is_checked"
    offBtn.current.className = "offrecom"
  }
  const offRecom = () => {
    offBtn.current.className += " is_checked"
    onBtn.current.className = "onrecom"
  }

  return (
    <div className='listrecipe'>
      <div className='is_btn'>
        <button className='onrecom is_checked' ref={onBtn} onClick={onRecom} >추천 레시피</button>          
        <button className='offrecom' ref={offBtn} onClick={offRecom} >기본 레시피</button>
      </div>
      <div className='search_input'>
        <div className='img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
        <input type="text" value={text} id='search_input'
          onChange={(e) => {
            setText(e.target.value);
          }}
          placeholder="검색어를 입력하세요"/>
        <div className='img_icon'><img src={delete_icon} alt="delete" className="delete_item" /></div>
      </div>
      
      <div className='recipe_toggle'>
        <Toggle
            checked = {checked}
            onChange = {(e) => {
              setChecked(e.target.checked)
            }}
            offstyle="off"
            onstyle="on"
            text="좋아요만"
          />
        </div>

      <div className='recipes'>
        {
          recipes.map((a, i) => {
            return <ItemRecipe recipes={a} num={i} key={i}/>            
          })
        }
      </div>
    </div>
  )
}

export default ListRecipe