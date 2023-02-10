import React, {useState} from 'react'
import { useRouteMatch } from 'react-router-dom'
import StepsRecipe from '../components/recipe/StepsRecipe'
import data from '../components/recipe/recipe.data.js'
import './css/recipeDetailPage.css'
import food from '../img/food.jpg'

function RecipeDetail() {

  const [recipes] = useState(data);
  const match = useRouteMatch();
  const item = recipes.filter(i => i.id === Number(match.params.id))
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