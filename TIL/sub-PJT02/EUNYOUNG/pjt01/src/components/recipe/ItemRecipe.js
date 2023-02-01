import React from 'react'
import { Link } from 'react-router-dom'
import "./css/ItemRecipe.css"
// import heart_b from "../../img/heart_b.png"
import heart from "../../img/heart.png"
import { useNavigate } from 'react-router-dom'



function ItemRecipe(props) {
  
  const navigate = useNavigate();
  const number = props.key;

  return (
    <div className='itemrecipe'         
      onClick = {(i) => {
      navigate('/detailrecipe', { state: number });
      console.log(i)
    }}>
        <img className='foodpic' src='https://recipe1.ezmember.co.kr/cache/recipe/2017/12/28/2ae16d56729371528da4a84b2afdb2f01_m.jpg' alt='food' />
        <div className='foodinfo'>
          <div className='food_title'>
            <h4>요리제목</h4>
            <div><img src={heart} alt="heart_b" className='heart_b'></img><div className='cnt_heart'>1.5k</div></div>
          </div>
          <div className='cookinfo'><div className='rank'>하</div><div className='time'>10분이내</div><div className='sync'>85%</div></div>
          <Link to="/detailrecipe"></Link>
        </div>
        
    </div>
  )
}

export default ItemRecipe

