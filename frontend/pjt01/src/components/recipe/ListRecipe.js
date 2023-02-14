import React, { useEffect, useRef } from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import { useState } from 'react'
import './css/ListRecipe.css'
import '../SearchBar.css'
import delete_icon from '../../img/delete_btn.png'
import search_icon from '../../img/search_item.png'
import Toggle from "../Toggle.component";
import axios from 'axios'

function ListRecipe(props) {
  const [data, setData] = useState();
  const [text, setText] = useState('');
  const [userlike,setUserlike] =useState([]);
  const [recipes, setRecipes] = useState([]);
  const [recomrecipes, setRecomrecipes] = useState([]);
  const [tmprecipes, settmprecipes] = useState([]);
  const [isrecom, setIsrecom] = useState(true)
  const [likeRecipes, setLikeRecipes] = useState([]);
  const [checked, setChecked] = useState(false)
  const [recomCheck,setRecomCheck] =useState(false);
  const onBtn = useRef(null);
  const offBtn = useRef(null);
  const id=localStorage.getItem("id")
  useEffect(() => {
    // const url="http://localhost:8080";
    const url="https://i8b304.p.ssafy.io/api";
      axios.get(url+"/recipes",{
      })
        .then(function(response) {
          setRecipes(response.data.data);
          setData(response.data.data);
          settmprecipes(response.data.data);
          console.log("성공");
      })
        .catch(function(error) {
            console.log(error);
      })

      axios.post(url+"/recipes/likes",{"user_id":id})
        .then(function(response) {
          const getlike=response.data.data.contents
          console.log(getlike)
          setLikeRecipes(getlike);
          getuserlike();
          // setUserlike(getlike.map(item=>{item}))
        })
          .catch(function(error) {
              console.log("실패");
        })
      

      // if(false){
      //   axios.get(url+"/recipes/reocomment/:user_id",{
      //     params : {
      //       "userid" : id
      //     }
      //   })
      //     .then(function(response) {
      //       setRecomrecipes(response.data.data);
      //   })
      //     .catch(function(error) {
      //   })
      // }
       
  }, [])  

  function getuserlike(){
    setUserlike(Object.values(likeRecipes).map(item=>item.recipe_id))
  }

  function on(){
    console.log(userlike)
  }

  const onRecom = () => {
    onBtn.current.className += " is_checked"
    offBtn.current.className = "offrecom"
    setRecipes(tmprecipes)
    setIsrecom(false)
  }
  const offRecom = () => {
    offBtn.current.className = "offrecom is_checked"
    onBtn.current.className = "onrecom"
    setIsrecom(true)
    setRecipes(recomrecipes)

  }

  const Recipe = () => {
    return (
      <div className='recipes'>
        {
          recipes?.map((a, i) => {
            return <ItemRecipe recipes={a} num={i} key={i} like={likeRecipes} />            
          })
        }
      </div>
    );
  };

  const LikeRecipe = () => {
    return (
      <div className='recipes'>
        {
          likeRecipes?.map((a, i) => {
            return <ItemRecipe recipes={a} num={i} key={i} like={likeRecipes}/>            
          })
        }
      </div>
    );
  };

  return (
    <div className='listrecipe'>
      {recomCheck?
        <div className='is_btn'>
           <button className='offrecom' ref={onBtn} onClick={onRecom} >기본 레시피</button>          
           <button className='onrecom is_checked' ref={offBtn} onClick={offRecom} >추천 레시피</button>
        </div> 
      :
        <div className='is_btn'>
          <button className='onrecom is_checked' ref={onBtn} onClick={onRecom} >기본 레시피</button>          
          <button className='offrecom' ref={offBtn} onClick={offRecom} >추천 레시피</button>
        </div>   

      }

      <div className='search_input'>
      <div className='img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
      <input type="text" value={text} id='search_input'
        onChange={(e) => {
          setText(e.target.value);
          setRecipes(data.filter(i => i.recipe_name.includes(e.target.value)))
        }}
        placeholder="레시피를 검색하세요."/>
      <div className='img_icon'><img src={delete_icon} alt="delete" className="delete_item" onClick={() => setText("")} /></div>
    </div>
      <div className='recipe_toggle'>
        <Toggle
            checked = {checked}
            onChange = {(e) => {
              setChecked(e.target.checked)
              on()
            }}
            offstyle="off"
            onstyle="on"
            text="좋아요만"
          />
        </div>
        {checked ? <LikeRecipe/> : <Recipe   />}
    </div>
  )
}

export default ListRecipe