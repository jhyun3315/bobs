import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import "./css/ItemRecipe.css"
import heart_b from "../../img/empty_heart.png"
import heart from "../../img/red_heart.png"
import rank from "../../img/Star.png"
import time from "../../img/Clock.png"
// import down from "../../img/detailbtn.png"


function ItemRecipe(props) {

  const [modal, setModal] = useState(false);
  const data = props.recipes;
  const [islike, setIslike] = useState(false);
  const [likecnt, setLikecnt] = useState(props.recipes.cnt_like)

  return ( 
    <div className='itemrecipe' >
      <div className='recipe_item_food'>
        <img className='foodpic' src='https://recipe1.ezmember.co.kr/cache/recipe/2017/12/28/2ae16d56729371528da4a84b2afdb2f01_m.jpg' alt='food'/>
        <div className='foodinfo'>
          <div className='foodinfo_top'>
            <div className='food_name'>{ props.recipes.name }</div>
            <div className='food_match'>{ props.recipes?.match }</div>
          </div>
          <div className='foodinfo_bottom'>
            <div className='recipe_like'>
              {
                islike === true ?
                <img src={heart} alt="heart" className='reciepe_heart_img'/> :
                <img src={heart_b} alt="heart" className='recipe_heart_img'/>
              }
              { 
                likecnt > 1000 ?
                <div>{likecnt/1000}k</div> : <div>{likecnt}</div>
              }</div>
            <div className='recipe_rank'><img src={rank} alt="rank" className='recipe_img'/><br/>{ props.recipes.rank }</div>
            <div className='recipe_time'><img src={time} alt="time" className='recipe_img'/><br/>{ props.recipes.time }</div>
          </div>
          { modal === true ? <Modal data={data} setModal={setModal} setLikecnt={setLikecnt} setIslike={setIslike} /> : null }
        </div>
      </div>        
      <div className='recipe_detail_btn'>
        {/* <img src={down} alt="" className="recipe_down_img" onClick={()=> setModal(true)}/> */}
        <div onClick={()=> setModal(true)}>자세히보기</div>
      </div>
    </div>
  )
}

function Modal(data) {

  const recipe = data.data;
  const [islike, setIslike] = useState(false);
  const have = ['멸치', '돼지고기', '멸치', '돼지고기', '멸치', '돼지고기']
  const nohave = ['돼지고기', '멸치', '돼지고기', '멸치', '돼지고기', '멸치']
  const [likecnt, setLikecnt] = useState(recipe.cnt_like);
 
  return (
    <div className="recipe_modal">
        <div className="modal_close_recipe" onClick={()=> {data.setModal(false); data.setLikecnt(likecnt); data.setIslike(islike)}}>X</div>
      <div className='modal_recipe_top'>
        <img className='foodpic' src='https://recipe1.ezmember.co.kr/cache/recipe/2017/12/28/2ae16d56729371528da4a84b2afdb2f01_m.jpg' alt='food' />
        <div className='modal_foodinfo'>
          <div className='modal_foodinfo_top'>
            <div className='modal_food_name'>{recipe.name }</div>
            <div className='modal_food_match'>{ recipe?.match }</div>
          </div>
          <div className='modal_foodinfo_bottom'>
            <div className='modal_recipe_like'>
              {
                islike === true ?
                <img src={heart} alt="heart" className='recipe_heart_img' onClick={() => {setIslike(!islike); setLikecnt(likecnt-1)}}/> :
                <img src={heart_b} alt="heart" className='recipe_heart_img' onClick={() => {setIslike(!islike); setLikecnt(likecnt+1)}}/>
              }
              { 
                likecnt > 1000 ?
                <div>{likecnt/1000}k</div> : <div>{likecnt}</div>
              }</div>
            <div className='modal_recipe_rank'><img src={rank} alt="rank" className='recipe_img'/><br/>{ recipe.rank }</div>
            <div className='modal_recipe_time'><img src={time} alt="time" className='recipe_img'/><br/>{ recipe.time }</div>
          </div>
        </div>
      </div>
      <div className='modal_recipe_item'>
        <div className="modal_item_left">냉장고 속 재료
          <div className='modal_have_item'>
            {
              have.map((item, index) => {
                return <div className='modal_item' key={index}>{item}</div>
              })
            }
          </div>
        </div>
        <div className="modal_item_right">그 외 재료
          <div className='modal_nohave_item'>
            {
              nohave.map((item, index) => {
                return <div className='modal_item' key={index}>{item}</div>
              })
            }
          </div>
        </div>
      </div>
      <Link to={'/recipe/' + recipe.id} state={{id: recipe.num}} >
        <div className="move_study_detail">레시피 상세보기</div>
      </Link>
    </div>
  )
}


export default ItemRecipe

