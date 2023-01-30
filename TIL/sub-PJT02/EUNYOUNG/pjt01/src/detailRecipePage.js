import React from 'react'
import StepsRecipe from './components/recipe/StepsRecipe'

function DetailRecipe() {
  return (
    <div>
      <Link to="/detailRecipePage" />
      <StepsRecipe></StepsRecipe>
    </div>
  )
}

export default DetailRecipe