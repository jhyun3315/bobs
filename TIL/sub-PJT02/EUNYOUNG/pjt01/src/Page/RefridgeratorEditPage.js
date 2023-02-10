import './css/RefridgeratorEditPage.css'
import { useHistory, useLocation } from "react-router-dom"
import { useState } from 'react'
import SearchBar from '../components/SearchBar'


function RefridgeratorEditPage() {
  const loction = useLocation()
  const ingredients = loction.content.ingredients
  const ingredientsLIst = ingredients.map((ingredient) => <div key={ingredient}>{ingredient}</div>)
  return (
    <div className="refridgerator_edit_page">
      <div className="top">
        <div className="title">다쓴 재료 등록</div>
        <div className='finish' onClick={null}>완료</div>
      </div>
      <div className='select_ingredients'>
        <div className='info'>재료를 선택해주세요</div>
        <div className='ingredients_list'>{ingredientsLIst}</div>
      </div>
      <div className="select_ex_ingredients">
        <div className='info'>추가 재료를 선택해주세요</div>
        <SearchBar />
      </div>
    </div>
  )
}

export default RefridgeratorEditPage