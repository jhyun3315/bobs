import './css/RefridgeratorEditPage.css'
import { useHistory, useLocation } from "react-router-dom"
import { useState } from 'react'
import data from '../Page/item.data'
import SearchBar from '../components/SearchBar'


function RefridgeratorEditPage() {
  const loction = useLocation()
  const [ingredients, setIngredients] = useState(loction.content.ingredients)
  const [item, setItem] = useState(data)
  const ingredientsLIst = ingredients.map((ingredient) => <div key={ingredient}>{ingredient}</div>)
  
  console.log(loction.content.ingredients)
  
  return (
    <div className="refridgerator_edit_page">
      <div className="top">
        <div className="title">다 쓴 재료 등록</div>
        <div className='finish' onClick={null}>완료</div>
      </div>
      <div className='select_ingredients'>
        <div className='info'>다 쓴 재료를 선택해주세요</div>
        <div className='ingredients_list'
          onClick={(e) => {
            setIngredients(ingredients.filter(item => item !== e.target.innerText))
          }}>{ingredientsLIst}</div>
      </div>
      <div className="select_ex_ingredients">
        <div className='info'>추가 재료를 등록해주세요</div>
        <SearchBar
          data = {data}
          setData = {setItem}
          placeholder={'추가로 사용한 재료를 검색해 주세요.'} />
      </div>
      <div className='select_ex_list'>
        {
          item.map((item) => {
            return(
              <div key={item.id} className='select_ex_item'
                onClick={()=> {
                  if(!ingredients.includes(item.name))
                  setIngredients([ item.name, ...ingredients])
                }}>{item.name}</div>
            )
          })
        }
      </div>
    </div>
  )
}

export default RefridgeratorEditPage