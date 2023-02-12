import React, { useEffect } from 'react'
import recipe_next from '../../img/recipe_next.png'
import recipe_back from '../../img/recipe_back.png'
import './css/StepsRecipe.css'
import { useState } from 'react'
import { useHistory } from 'react-router-dom'

function StepsRecipe(props) {
  const [nowpage, setNowpage] = useState([])
  const [lastpage, setLastpage] = useState([])

  useEffect(() => {
    console.log(props.step)
    setNowpage(props.step[0])

    
  }, [])
  
  const nextPage = () => {
    setNowpage(nowpage + 1)
  }
  const prevPage = () => {
    setNowpage(nowpage - 1)
  }
  const history = useHistory()
  // 임의로 넣은 데이터
  const ingredients = ['감자', '돼지고기']
  const toRefridgeratorEditPage = () => {
    history.push({
      pathname: "/refridgerator/edit",
      content: {
        ingredients: ingredients
      }
    })
  }
  return (
    <div className='steps_recipe'>
      {
        nowpage === 1 ? 
        <div className='img_box'></div>:
        <div className='img_box' onClick={prevPage}><img src={recipe_back} alt="<" /></div>
      }
      <div className='steps'>
        <div className='now_step'>{nowpage}</div>
        <div className='slash'>/</div>
        <div className='last_step'>{lastpage}</div>
      </div>
      {
        nowpage === lastpage ? 
        <div className='finish' onClick={toRefridgeratorEditPage}>완료</div> : 
        <div className='img_box' onClick={nextPage}><img src={recipe_next} alt="<" /></div>
      }     
    </div>
  )
}

export default StepsRecipe