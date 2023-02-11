import React, {useEffect, useState} from 'react'
import { useRouteMatch } from 'react-router-dom'
import StepsRecipe from '../components/recipe/StepsRecipe'
// import data from '../components/recipe/recipe.data.js'
import './css/recipeDetailPage.css'
import food from '../img/food.jpg'
import axios from 'axios'

function RecipeDetail(props) {
  const [recipes, setRecipes] = useState([]);
  const match = useRouteMatch();
  const item = recipes.filter(i => i.id === Number(match.params.id))

  useEffect(() => {
    console.log(props)
    const url="http://localhost:8080/api/recipes";
    // const url="https://i8b304.p.ssafy.io/api/recipes";
      axios.get(url+props.state,{
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

    
  
    return () => {
      
    }
  }, [])
  


  return (
    <div className='recipe_detail'>
      <StepsRecipe />
      <div className="recipe">
        {/* 임의 데이터 넣음 */}
        <img src={food} alt="레시피 사진" />
        <div className='recipe_content'>
          당근, 버섯, 애호박은 채썰어서 준비하세요!
          당근, 버섯, 애호박은 채썰어서 준비하세요!
          당근, 버섯, 애호박은 채썰어서 준비하세요!
          당근, 버섯, 애호박은 채썰어서 준비하세요!
          당근, 버섯, 애호박은 채썰어서 준비하세요!
          당근, 버섯, 애호박은 채썰어서 준비하세요!
        </div>
      </div>
    </div>
  )
}

export default RecipeDetail