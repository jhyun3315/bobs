import React, {useState} from 'react'
import { useRouteMatch } from 'react-router-dom'
import StepsRecipe from './components/recipe/StepsRecipe'
import data from '../src/components/recipe/recipe.data.js'

function DetailRecipe() {

  const [recipes] = useState(data);
  const match = useRouteMatch();
  const item = recipes.filter(i => i.id === Number(match.params.id))
  return (
    <div>
      <h1>{item[0].name}</h1>
      <h2>{item[0].time}</h2>
      <StepsRecipe></StepsRecipe>
    </div>
  )
}

export default DetailRecipe