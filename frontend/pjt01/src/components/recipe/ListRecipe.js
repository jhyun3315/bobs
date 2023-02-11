import React, { useEffect, useRef } from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import { useState } from 'react'
import './css/ListRecipe.css'
import SearchBar from '../SearchBar'
import Toggle from "../Toggle.component";
import data from './recipe.data.js'
import recom_data from './recom.data.js'
import axios from 'axios'

function ListRecipe() {

  const [recipes, setRecipes] = useState([]);
  const [isrecom, setIsrecom] = useState(true)
  const [likeRecipes, setLikeRecipes] = useState([]);
  const [checked, setChecked] = useState(false)
  const onBtn = useRef(null);
  const offBtn = useRef(null);
  const tmpdata= [
    {
      
    } 
  ]
  useEffect(() => {
    const url="http://localhost:8080/api/recipes";
    // const url="https://i8b304.p.ssafy.io/api/recipes";
      axios.get(url,{
        params : {
          "page" : 1
        }
      })
        .then(function(response) {
          console.log(response.data.data)
          setRecipes(response.data.data);
          console.log("성공");
      })
        .catch(function(error) {
            console.log("실패");
      })

  }, [])
  

  const onRecom = () => {
    onBtn.current.className += " is_checked"
    offBtn.current.className = "offrecom"
    setIsrecom(true)
    setRecipes(recom_data)
  }
  const offRecom = () => {
    offBtn.current.className += " is_checked"
    onBtn.current.className = "onrecom"
    setRecipes(data)
    setIsrecom(false)
  }

  const Recipe = () => {
    return (
      <div className='recipes'>
        {
          recipes.map((a, i) => {
            return <ItemRecipe recipes={a} num={i} key={i} />            
          })
        }
      </div>
    );
  };

  const LikeRecipe = () => {
    return (
      <div className='recipes'>
        {
          likeRecipes.map((a, i) => {
            return <ItemRecipe recipes={a} num={i} key={i} />            
          })
        }
      </div>
    );
  };

  return (
    <div className='listrecipe'>
      <div className='is_btn'>
        <button className='onrecom is_checked' ref={onBtn} onClick={onRecom} >추천 레시피</button>          
        <button className='offrecom' ref={offBtn} onClick={offRecom} >기본 레시피</button>
      </div>
      <SearchBar data={isrecom ? recom_data : data} setData = {setRecipes}
        placeholder={'레시피를 검색하세요.'} />
      
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
        {checked ? <LikeRecipe /> : <Recipe />}
    </div>
  )
}

export default ListRecipe