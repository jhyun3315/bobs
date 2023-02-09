import React, { useRef } from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import { useState } from 'react'
import './css/ListRecipe.css'
import Toggle from "../Toggle.component";
import data from './recipe.data.js'
import recom_data from './recom.data.js'
import SearchBar from '../SearchBar'
// import axios from 'axios'

function ListRecipe() {

  const [recipes, setRecipes] = useState(recom_data);
  const [is_data, setIsdata] = useState(true)

  const [checked, setChecked] = useState(false)
  const onBtn = useRef(null);
  const offBtn = useRef(null);

  // const tmpdata= [
  //   {
      
  //   } 
  // ]
  // useEffect(() => {
  //   const url="/communities";
  //     axios.post(url,{
  //       params : {
  //         "page" : 1
  //       }
  //     })
  //       .then(function(response) {
  //         setRecipes(response.data);
  //         ("성공");
  //     })
  //       .catch(function(error) {
  //           console.log("실패");
  //     })

  // }, [])
  


  const onRecom = () => {
    onBtn.current.className += " is_checked"
    offBtn.current.className = "offrecom"
    setRecipes(recom_data)
    setIsdata(true)
  }
  const offRecom = () => {
    offBtn.current.className += " is_checked"
    onBtn.current.className = "onrecom"
    setRecipes(data)
    setIsdata(false)
  }

  return (
    <div className='listrecipe'>
      <div className='is_btn'>
        <button className='onrecom is_checked' ref={onBtn} onClick={onRecom} >추천 레시피</button>          
        <button className='offrecom' ref={offBtn} onClick={offRecom} >기본 레시피</button>
      </div>
      <SearchBar type="text" id='recipe_search_input'
        placeholder={"레시피를 검색하세요."}
        data = {is_data === true ? recom_data : data}
        setData = {setRecipes} />
      
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
            return <ItemRecipe recipes={a} num={i} key={i} />            
          })
        }
      </div>
    </div>
  )
}

export default ListRecipe