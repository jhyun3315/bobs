import React from 'react'
import DetailRecipe from './DetailRecipe'
import "./css/ItemRecipe.css"


function ItemRecipe() {
  return (
    <div className='itemrecipe'>
        <div>
          요리제목
        </div>
        <DetailRecipe></DetailRecipe>
    </div>
  )
}

export default ItemRecipe

