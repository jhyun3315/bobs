import { useEffect, useState } from 'react'
import './css/addItemPage.css'
import data from './item.data.js'
import x_btn from '../img/x.png'
import SearchBar from '../components/SearchBar'
import axios from 'axios'
import Scanimage from './scanimage'

function AddItemPage() {
  const url="https://i8b304.p.ssafy.io/api";
  // const url="http://localhost:8080";
  const [useritem, setuserItem] = useState([]);
  const [item, setItem] = useState([]);
  const [ingitem, setIngItem] = useState([]);
  const [havelist, setHave_list] = useState([]);
  const [text,settext] =useState("");
  const local_id= localStorage.getItem("id");
  useEffect(() => {
    
    axios.get(url+"/ingredients"
    ).then((res) => {
      const getdata=res.data;
      setIngItem(res.data.data);
      delete getdata.result;
      
    })


  }, [])
  


  const additem=(item)=>{
   
    if (!havelist.includes(item)){
      setHave_list([ item, ...havelist ]);
  };
  console.log(havelist)
  }

  
  const goAdd=()=>{
    console.log(havelist)
    const list=havelist.map((item)=>item.ingredient_id)
    var inlist=[]
    for (let index = 0; index < list.length; index++) {
       inlist =[...inlist,{
        "ingredient_id" : list[index],
        "is_deleted" : false,
        "is_prior" : false
       }];
    }
    console.log(list)
    console.log(inlist)
    axios.put(url+"/refriges",
      {
        "user_id" : local_id,
        "ingredient_list":inlist
      }   
    )
  }


  const deleteall=()=>{
    setHave_list([]);
  }

  const setreftext=(text)=>{
    settext(text)
    const getimage=ingitem.filter(item=>item.ingredient_name===text)
    if (!havelist.includes(getimage)){
      setHave_list(getimage, ...havelist);
    };
  }

  return(
    <div className="add_item_page">
      <div className='add_item_top'>
        <div className='add_item_title'>냉장고 재고 추가하기</div>
        <Scanimage setreftext={setreftext}/>
        <div className='add_item_complete' onClick={()=>goAdd()}>완료</div>
      </div>

      <SearchBar 
        placeholder={"재료를 검색하세요"}
        data = {ingitem}
        setData = {setItem}
        value ={text}
        className="add_item_search" />
      <div className='add_item_middle'>
        <div className='add_item_choice'>선택된 항목</div>
        <div className='add_all_delete' onClick={()=>deleteall()}>전체 삭제</div>
      </div>
      <div className='add_choice_item'>
        {
          havelist?.map((item,index) => {
            return (
              <div className='have_item' key={index}>{item.ingredient_name}<img src={x_btn} alt="" className="add_x_btn" /></div>
            )
          })
        }
      </div>
      <div className='add_search_list'>
        {
          item?.map((item) => {
            return <div key={item.ingredient_id} className="add_search_item" onClick={() => additem(item) }>{item.ingredient_name}</div>
          })
        }
      </div>

    </div>
  )
} export default AddItemPage
