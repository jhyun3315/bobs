import React from 'react'
import StepsRecipe from './components/recipe/StepsRecipe'
import { Link } from 'react-router-dom'

function DetailRecipe() {
  return (
    <div>
      <Link to="/detailRecipePage" />
      <StepsRecipe></StepsRecipe>
    </div>
  )
}

export default DetailRecipe