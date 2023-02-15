import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import "./css/ItemRecipe.css"
import heart_b from "../../img/heart_b.png"
import heart from "../../img/heart.png"
import rank from "../../img/Star.png"
import time from "../../img/Clock.png"
import axios from 'axios'
import ref from '../ref/ref.data'

// import down from "../../img/detailbtn.png"


function ItemRecipe(props) {

  const [modal, setModal] = useState(false);
  const data = props.recipes;
  const [islike, setIslike] = useState(false);
  const [likecnt, setLikecnt] = useState(props.recipes?.recipe_hit)
  const [ingredient, setIngredient] = useState();

  useEffect(() => {
    const i=Object.values(props.like).map(item=>item.recipe_id)
    for (let index = 0; index < i.length; index++) {
      if(i[index]===props.recipes.recipe_id){
        setIslike(true);
      }
    }

  }, [islike])
  

  function recipe_ingredient() {
    const url=`https://i8b304.p.ssafy.io/api/recipes/` + props?.recipes.recipe_id;
    // const url=`https://localhost:8080/api/recipes/` + props?.recipes.recipe_id;
      axios.get(url,{
      })
        .then(function(response) {
          console.log(response.data)
      })
        .catch(function(error) {
          console.log(error);
      })
  }


  return ( 
    <div className='itemrecipe' >
      <div className='recipe_item_food'>
        <img className='foodpic' src={props.recipes.recipe_img} alt='food'/>
        {/* <img className='foodpic' src={heart} alt='food'/> */}
        <div className='foodinfo'>
          <div className='foodinfo_top'>
            <div className='food_name'>{ props.recipes?.recipe_name }</div>
            <div className='food_match'>{ props.recipes?.match }</div>
          </div>
          <div className='foodinfo_bottom'>
            <div className='recipe_like'>
              {
                islike === true ?
                <img src={heart} alt="heart" className='recipe_heart_img'/> :
                <img src={heart_b} alt="heart" className='recipe_heart_img'/>
              }
              { 
                likecnt > 1000 ?
                <div>{likecnt/1000}k</div> : <div>{likecnt}</div>
              }</div>
            <div className='recipe_rank'><img src={rank} alt="rank" className='recipe_img'/><br/>{ props.recipes?.recipe_level }</div>
            <div className='recipe_time'><img src={time} alt="time" className='recipe_img'/><br/>{ props.recipes?.getRecipe_time }</div>
          </div>
          { modal === true ? <Modal data={data} setModal={setModal} setLikecnt={setLikecnt} setIslike={islike} /> : null }
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
  const [islike, setIslike] = useState(data.setIslike);
  // const [ingredients,setingredients] =useState([]);
  // const myref = data;
  const [have,sethave] = useState([]);
  const [nohave,setnohave] = useState([]);
  const [likecnt, setLikecnt] = useState(recipe.recipe_hit);
  const ingre= useState(ref);
  const id=localStorage.getItem("id")
  const url="https://i8b304.p.ssafy.io/api";
  // const url="http://localhost:8080";
  useEffect(() => {
    setLikecnt(recipe.recipe_hit);
    
    axios.get(url+"/recipes/ingredients/"+data.data.recipe_id,{
  
    })
      .then(function(response) {
        // setingredients(response.data.data);
        sethave(response.data.data)
        const i=response.data.data
        var tmph=[]
        var tmpnh=[]
        for (let index = 0; index < i.length; index++) {
          for (let i2 = 0; i2 < ingre[0].data.length; i2++) {

            if(ingre[0].data[i2].ingredient_name===i[index].recipe_ingredient){
              console.log(1)
              tmph.push(ingre[0].data[i2].ingredient_name)
            }else{
              tmpnh.push(i[index].recipe_ingredient)
              continue;
            }

          } 
        }
        tmph = [...new Set(tmph)];
        tmpnh = [...new Set(tmpnh)];
        sethave(tmph)
        setnohave(tmpnh)        
    })
      .catch(function(error) {
    })

  }, [])

    function setLike(){
      axios.put(url+"/recipes/"+data.data.recipe_id+"/like?userId="+id,{

      }).then(function(response) {
        console.log(response.data)
      })
        .catch(function(error) {
          console.log(error);
      })
    }


    function con(){
      console.log(ingre[0].data);

    }

  
 
  return (
    <div className="recipe_modal">
        <div className="modal_close_recipe" onClick={()=> {data.setModal(false); 
          data.setLikecnt(likecnt); 
          // data.setIslike(islike); 
          con()}}>X</div>
      <div className='modal_recipe_top'>
        <img className='foodpic' src={recipe.recipe_img} alt='food' />
        {/* <img className='foodpic' src={heart} alt='food'/> */}
        <div className='modal_foodinfo'>
          <div className='modal_foodinfo_top'>
            <div className='modal_food_name'>{recipe?.recipe_name }</div>
            <div className='modal_food_match'>{ recipe?.match }</div>
          </div>
          <div className='modal_foodinfo_bottom'>
            <div className='modal_recipe_like'>
              {
                islike === true ?
                <img src={heart} alt="heart" className='recipe_heart_img' onClick={() => {setIslike(!islike); setLikecnt(likecnt-1); setLike()}}/> :
                <img src={heart_b} alt="heart" className='recipe_heart_img' onClick={() => {setIslike(!islike); setLikecnt(likecnt+1); setLike()}}/>
              }
              { 
                likecnt > 1000 ?
                <div>{likecnt/1000}k</div> : <div>{likecnt}</div>
              }</div>
            <div className='modal_recipe_rank'><img src={rank} alt="rank" className='recipe_img'/><br/>{ recipe?.recipe_level }</div>
            <div className='modal_recipe_time'><img src={time} alt="time" className='recipe_img'/><br/>{ recipe?.getRecipe_time }</div>
          </div>
        </div>
      </div>
      <div className='modal_recipe_item'>
        <div className="modal_item_left">필요한 재료
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
      <Link to={'/recipe/' + data.data.recipe_id} r_id={recipe.recipe_id} >
        <div className="move_study_detail">레시피 상세보기</div>
      </Link>
    </div>
  )
}


export default ItemRecipe

