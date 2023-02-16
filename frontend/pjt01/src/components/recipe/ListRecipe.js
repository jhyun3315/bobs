import React, { useEffect, useRef } from 'react'
import ItemRecipe from '../recipe/ItemRecipe'
import { useState } from 'react'
import './css/ListRecipe.css'
import '../SearchBar.css'
import delete_icon from '../../img/delete_btn.png'
import search_icon from '../../img/search_item.png'
import Toggle from "../Toggle.component";
import axios from 'axios'
import {useLocation} from "react-router";

function ListRecipe(props) {
  const [data, setData] = useState();
  const [text, setText] = useState('');
  const [userlike,setUserlike] =useState([]);
  const [recipes, setRecipes] = useState([]);
  const [recomrecipes, setRecomrecipes] = useState([]);
  const [tmprecipes, settmprecipes] = useState([]);
  const [isrecom, setIsrecom] = useState(false)
  const [likeRecipes, setLikeRecipes] = useState([]);
  const [checked, setChecked] = useState(false)
  const [recomCheck,setRecomCheck] =useState(false);
  const onBtn = useRef(null);
  const offBtn = useRef(null);
  const id=localStorage.getItem("id")
  const location = useLocation();
  const url="https://i8b304.p.ssafy.io/api";
  const [userRef, setuserRef] = useState([])
   // const url="http://localhost:8080";
   
  useEffect(() => {
    console.log(location.state)
      if(location.state!=undefined){
        if(location.state.check){
          setRecomCheck(true);
          
          const datainput={
           "user_id":id,
           "selectedIngredients" : location.state.recipe
          }
           var config = {
             method: 'post',
             url: "https://i8b304.p.ssafy.io/api/recipes/recommendations",
             headers: { 
               'Content-Type': 'application/json'
             },
             data: datainput
           };
           axios(config)
            .then(function(response) {
              setRecomrecipes(response.data.data)
              console.log(response.data)
              console.log(response.data.data)
            })
            .catch(function(error) {
                 console.log("실패",error);
            })
        }
      } 
    
    },[])

  useEffect(() => {

    // 레시피 가져오기
    axios.get(url+"/recipes",{
    })
      .then(function(response) {
        console.log(response);
        setRecipes(response.data.data);
        setData(response.data.data);
        settmprecipes(response.data.data);
    })
      .catch(function(error) {
          console.log(error);
    })

    // 좋아요 가져오기
    axios.post(url+"/recipes/likes",{"user_id":id})
      .then(function(response) {
        const getlike=response.data.data.contents
        setLikeRecipes(getlike);
        getuserlike();
        // setUserlike(getlike.map(item=>{item}))
      })
        .catch(function(error) {
            console.log("실패");
      })

    // 냉장고 재료 가져오기
      var data = JSON.stringify("6");
      var config = {
        method: 'post',
        url: "https://i8b304.p.ssafy.io/api/refriges",
        headers: { 
          'Content-Type': 'application/json'
        },
        data : data
      };
      axios(config)
        .then(function(res) {
          setuserRef(res.data.data);
        })
        .catch(function(error) {
            console.log("실패",error);
        })
       
  }, [])  


  function golike(){
    
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
  }

  function getuserlike(){
    setUserlike(Object.values(likeRecipes)?.map(item=>item.recipe_id))
  }

  function on(){
    console.log(userlike)
  }

  const onRecom = () => {
    onBtn.current.className += " is_checked"
    offBtn.current.className = "offrecom"
    setRecipes(tmprecipes)
    setIsrecom(true)
  }
  const offRecom = () => {
    offBtn.current.className = "offrecom is_checked"
    onBtn.current.className = "onrecom"
    setIsrecom(false)
    setRecipes(recomrecipes)
  }

  const Recipe = () => {
    return (
      <div className='recipes'>
        {
          recipes?.map((a, i) => {
            if(a !== null && a !== {})
            return <ItemRecipe recipes={a} userRef={userRef} num={i} key={i} like={likeRecipes} />            
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
            if(a !== null && a !== {})
            return <ItemRecipe recipes={a} userRef={userRef} num={i} key={i} like={likeRecipes}/>            
          })
        }
      </div>
    );
  };

  const RecomRecipe = () => {
    return (
      <div className='recipes'>
        {
          recomrecipes?.map((a, i) => {
            if(a !== null && a !== {})
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
           <button className='onrecom' ref={onBtn} onClick={onRecom} >기본 레시피</button>          
           <button className='offrecom is_checked' ref={offBtn} onClick={offRecom} >추천 레시피</button>
        </div> 
      :
        <div className='is_btn'>
          <div className="study_title" style={{"marginTop":"5px","marginBottom":"5px"}}>밥스 레시피</div>
          {/* <button className='onrecom is_checked' ref={onBtn} onClick={onRecom} >기본 레시피</button>           */}
          {/* <button className='offrecom' ref={offBtn} onClick={offRecom} >추천 레시피</button> */}
        </div>   

      }

      <div className='search_input'>
      <div className='img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
      <input type="text" value={text} id='search_input'
        onChange={(e) => {
          setText(e.target.value);
          setRecipes(data?.filter(i => i.recipe_name.includes(e.target.value)))
        }}
        placeholder="레시피를 검색하세요."/>
      <div className='img_icon'><img src={delete_icon} alt="delete" className="delete_item" onClick={() => setText("")} /></div>
    </div>
      <div className='recipe_toggle'>
        <Toggle
            checked = {checked}
            onChange = {(e) => {
              setChecked(e.target.checked)
              golike()
            }}
            offstyle="off"
            onstyle="on"
            text="좋아요만"
          />
        </div>
        {recomCheck ? 
          <>
          {isrecom ?
          <>
            {checked ? <LikeRecipe/> : <Recipe />}
          </>
                :
          <>
            {checked ? <LikeRecipe/> : <RecomRecipe />}
          </>
          }
          </>
          :
          <>
          {checked ? <LikeRecipe/> : <Recipe />}
          </>
        }
        
        
    </div>
  )
}

export default ListRecipe