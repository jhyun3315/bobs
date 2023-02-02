import React from 'react'
import { Link } from 'react-router-dom'
import "./css/ItemRecipe.css"
// import heart_b from "../../img/heart_b.png"
import heart from "../../img/heart.png"

function ItemRecipe(props) {

  return (
    <Link to={'/recipe/'+ props.num} state={{id: props.num}} >  
    <div className='itemrecipe'>
        <img className='foodpic' src='https://recipe1.ezmember.co.kr/cache/recipe/2017/12/28/2ae16d56729371528da4a84b2afdb2f01_m.jpg' alt='food' />
        <div className='foodinfo' >
          <div className='food_title'>
            <h4>{props.recipes.name}</h4>
            <div><img src={heart} alt="heart_b" className='heart_b'></img><div className='cnt_heart'>{ props.recipes.cnt_like }</div></div>
          </div>
          <div className='cookinfo'><div className='rank'>{ props.recipes.rank }</div><div className='time'>{ props.recipes.time }</div><div className='sync'>85%</div></div>
        </div>        
    </div>
    </Link>
  )
}

export default ItemRecipe

