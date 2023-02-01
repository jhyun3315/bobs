import React from 'react'
import StepsRecipe from './components/recipe/StepsRecipe'
import { useLocation } from 'react-router-dom'

function DetailRecipe() {

  const { state } = useLocation();

  return (
    <div>
      <h1>{state}</h1>
      
      <StepsRecipe></StepsRecipe>
    </div>
  )
}

export default DetailRecipe