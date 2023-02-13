import React, {useEffect, useState} from 'react'
import { useRouteMatch } from 'react-router-dom'
// import StepsRecipe from '../components/recipe/StepsRecipe'
// import data from '../components/recipe/recipe.data.js'
import './css/recipeDetailPage.css'
import food from '../img/food.jpg'
import axios from 'axios'
import recipe_next from '../img/recipe_next.png'
import recipe_back from '../img/recipe_back.png'
import { useHistory } from 'react-router-dom'

function RecipeDetail(props) {
  const [recipes, setRecipes] = useState([]);
  const match = useRouteMatch();
  const [nowpage, setNowpage] = useState(0)
  const [lastpage, setLastpage] = useState(null)
  const [recipe_step_content,setrecipe_step_content] =useState("")
  const [recipe_img,setrecipe_img] =useState("")
  // const item = recipes.filter(i => i.id === Number(match.params.id))
  // const url="http://localhost:8080";
  const url="https://i8b304.p.ssafy.io";
  useEffect(() => {
    // console.log(match.params.id)
    axios.get(url+"/api/recipes/step/"+match.params.id,{
    })
      .then(function(response) {
        const res=response.data.data;
        setRecipes(res);
        setrecipe_step_content(res[nowpage].recipe_step_content);
        setrecipe_img(res[nowpage].recipe_img);
        setLastpage(res.length+1);
    })
      .catch(function(error) {
    })
  }, [nowpage])

  const nextPage = () => {
    setNowpage(nowpage + 1)
    setrecipe_step_content(recipes[nowpage].recipe_step_content);
    setrecipe_img(recipes[nowpage].recipe_img);
    
  }

  const prevPage = () => {
    setNowpage(nowpage - 1)
    setrecipe_step_content(recipes[nowpage].recipe_step_content);
    setrecipe_img(recipes[nowpage].recipe_img);
    
  }
  const history = useHistory()
  // 임의로 넣은 데이터
  const ingredients = ['감자', '돼지고기']
  const toRefridgeratorEditPage = () => {
    history.push({
      pathname: "/refridgerator/edit",
      content: {
        ingredients: ingredients
      }
    })
  }
  


  return (
    <div className='recipe_detail'>
          <div className='steps_recipe'>
      {
        nowpage === 0 ? 
        <div className='img_box'></div>:
        <div className='img_box' onClick={
          prevPage}><img src={recipe_back} alt="<" /></div>
      }
      <div className='steps'>
        <div className='now_step'>{nowpage+1}</div>
        <div className='slash'>/</div>
        <div className='last_step'>{lastpage}</div>
      </div>
      {
        nowpage === lastpage-1 ? 
        <div className='finish' onClick={toRefridgeratorEditPage}>완료</div> : 
        <div className='img_box' onClick={nextPage}><img src={recipe_next} alt="<" /></div>
      }     
      </div>
      {/* <StepsRecipe step={recipes} key={response.data.data}/> */}
      {   recipe_img !== " " ?
        <div className="recipe">
          <img src={recipe_img} alt="레시피 사진" />
            <div className='recipe_content'>
          {recipe_step_content} 
        </div>
          </div> :
        <div className="recipe">
          <img src={food} alt="레시피 사진" />
            <div className='recipe_content'>
              {recipe_step_content} 
            </div>
        </div>
        }
      </div>
  )
}

export default RecipeDetail;