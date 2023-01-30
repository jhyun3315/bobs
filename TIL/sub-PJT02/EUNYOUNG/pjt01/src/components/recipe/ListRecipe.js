import React from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import './css/ListRecipe.css'

function ListRecipe() {
  return (
    <div className='listrecipe'>
      {Array.from(Array(10), i => <ItemRecipe key={i} />)}
    </div>
  )
}

export default ListRecipe