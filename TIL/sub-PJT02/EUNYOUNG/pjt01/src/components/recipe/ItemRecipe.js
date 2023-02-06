import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import "./css/ItemRecipe.css"
import heart_b from "../../img/empty_heart.png"
import heart from "../../img/red_heart.png"
import rank from "../../img/Star.png"
import time from "../../img/Clock.png"


function ItemRecipe(props) {

  const [modal, setModal] = useState(false);
  const data = props.recipes;
  const [islike] = useState(false);
  let cnt = props.recipes.cnt_like;

  return ( 
    <div className='itemrecipe' >
        <img className='foodpic' src='https://recipe1.ezmember.co.kr/cache/recipe/2017/12/28/2ae16d56729371528da4a84b2afdb2f01_m.jpg' alt='food'/>
        <div className='foodinfo'>
          <div className='foodinfo_top'>
            <div className='food_name'>{ props.recipes.name }</div>
            <div className='food_match'>{ props.recipes?.match }</div>
          </div>
          <div className='foodinfo_bottom'>
            <div className='receipe_like'>
              {
                islike === true ?
                <img src={heart} alt="heart" className='reciepe_heart_img'/> :
                <img src={heart_b} alt="heart" className='receipe_heart_img'/>
              }
              { 
                cnt > 1000 ?
                <div>{cnt/1000}k</div> : <div>{cnt}</div>
              }</div>
            <div className='receipe_rank'><img src={rank} alt="rank" className='receipe_img'/><br/>{ props.recipes.rank }</div>
            <div className='receipe_time'><img src={time} alt="time" className='receipe_img'/><br/>{ props.recipes.time }</div>
          </div>
          { modal === true ? <Modal data={data} setModal={setModal} /> : null }
        </div>        
    </div>
  )
}

function Modal(data) {

  const receipe = data.data;
  const [islike, setIslike] = useState(false);
  const have = ['멸치', '돼지고기', '멸치', '돼지고기', '멸치', '돼지고기']
  const nohave = ['돼지고기', '멸치', '돼지고기', '멸치', '돼지고기', '멸치']
 
  return (
    <div className="receipe_modal">
        <div className="modal_close_receipe" onClick={()=> data.setModal(false)}>X</div>
      <div className='modal_receipe_top'>
        <img className='foodpic' src='https://recipe1.ezmember.co.kr/cache/recipe/2017/12/28/2ae16d56729371528da4a84b2afdb2f01_m.jpg' alt='food' />
        <div className='modal_foodinfo'>
          <div className='modal_foodinfo_top'>
            <div className='modal_food_name'>{receipe.name }</div>
            <div className='modal_food_match'>{ receipe?.match }</div>
          </div>
          <div className='modal_foodinfo_bottom'>
            <div className='modal_receipe_like'>
              {
                islike === true ?
                <img src={heart} alt="heart" className='recipe_heart_img' onClick={() => setIslike(!islike)}/> :
                <img src={heart_b} alt="heart" className='recipe_heart_img' onClick={() => setIslike(!islike)}/>
              }{ receipe.cnt_like }</div>
            <div className='modal_receipe_rank'><img src={rank} alt="rank" className='receipe_img'/><br/>{ receipe.rank }</div>
            <div className='modal_receipe_time'><img src={time} alt="time" className='receipe_img'/><br/>{ receipe.time }</div>
          </div>
        </div>
      </div>
      <div className='modal_receipe_item'>
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
      <Link to={'/receipe/' + receipe.id} state={{id: receipe.num}} >
        <div className="move_study_detail">레시피 상세보기</div>
      </Link>
    </div>
  )
}


export default ItemRecipe

