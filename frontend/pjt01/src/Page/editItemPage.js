import { useEffect, useState } from 'react'
import './css/addItemPage.css'
import data from './item.data.js'
import x_btn from '../img/x.png'
import SearchBar from '../components/SearchBar'
import axios from 'axios'
import ref from '../components/ref/ref.data'

function EditItemPage() {
  // const url="https://i8b304.p.ssafy.io";
  const url="http://localhsot:8080";
  const [useritem, setuserItem] = useState([]);
  const [item, setItem] = useState([]);
  const [havelist, setHave_list] = useState([]);
  const id= localStorage.getItem("id");
  useEffect(() => {
    
    // axios.post(url+"/api/refriges",{    
    //   "user_id": id,
    //   "page" : 1
    // }).then((res) => {
    //   console.log(res);
    //   setuserItem(res);
    // })


  }, [])
  


  const additem=(item)=>{
    if (!havelist.includes(item)){
      setHave_list([ item, ...havelist ]);
  };

  }

  
  const goEdit=()=>{
    console.log(havelist)
    const list=havelist.map((item)=>item.ingredient_id)
    var inlist=[]
    for (let index = 0; index < list.length; index++) {
       inlist =[...inlist,{
        "ingredient_id" : list[index],
        "is_deleted" : 0,
        "is_prior" : 0
       }];
    }
    console.log(list)
    console.log(inlist)
    axios.put(url+"/api/refriges",
      {
        "user_id" : 1,
        "ingredient_list":inlist
      }
      
    )
  }



  const deleteall=()=>{
    setHave_list([]);
  }

  return(
    <div className="add_item_page">
      <div className='add_item_top'>
        <div className='add_item_title'>냉장고 재고 삭제하기</div>
        <div className='add_item_complete' onClick={()=>goEdit()}>완료</div>
      </div>

      <div className='add_item_middle'>
        <div className='add_item_choice'>선택된 항목</div>
        <div className='add_all_delete' onClick={()=>deleteall()}>전체 삭제</div>
      </div>
      <div className='add_choice_item'>
        {
          havelist?.map((item) => {
            return (
              <div className='have_item' key={item.id}>{item.name}<img src={x_btn} alt="" className="add_x_btn" /></div>
            )
          })
        }
      </div>
    </div>
  )
} export default EditItemPage
