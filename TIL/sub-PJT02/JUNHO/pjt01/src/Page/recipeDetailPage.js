import React, {useState} from 'react'
import { useRouteMatch } from 'react-router-dom'
import StepsRecipe from '../components/recipe/StepsRecipe'
import './css/recipeDetailPage.css'
import food from '../img/food.jpg'

function RecipeDetail() {
  const match = useRouteMatch();

  return (
    <div className='recipe_detail'>
      <StepsRecipe />
      <div className="recipe">
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