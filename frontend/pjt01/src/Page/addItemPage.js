import { useEffect, useState } from 'react'
import './css/addItemPage.css'
import x_btn from '../img/x.png'
import SearchBar from '../components/SearchBar'
import axios from 'axios'

function AddItemPage() {
  const url="https://i8b304.p.ssafy.io/api";
  // const url="http://localhost:8080";
  const [useritem, setuserItem] = useState([]);
  const [item, setItem] = useState([]);
  const [ingitem, setIngItem] = useState([]);
  const [havelist, setHave_list] = useState([]);
  const local_id= localStorage.getItem("id");
  
  useEffect(() => {
    axios.get(url+"/ingredients"
    ).then((res) => {
      const getdata=res.data;
      delete getdata.result;
      setIngItem(res.data.data);
    })
  }, [])
  
  // 재료 클릭 시 선택된 항목에 추가
  const additem=(item)=>{
    if (!havelist.includes(item)){
      setHave_list([ item, ...havelist ]);
    };
  }
  
  // 냉장고 재고 추가 axios
  const goAdd=()=>{
    const list=havelist.map((item)=>item.ingredient_id)
    var inlist=[]
    for (let index = 0; index < list.length; index++) {
       inlist =[...inlist,{
        "ingredient_id" : list[index],
        "is_deleted" : false,
        "is_prior" : false
       }];
    }
    axios.put(url+"/refriges",
      {
        "user_id" : local_id,
        "ingredient_list":inlist
      }   
    )
  }

  // 선택된 항목 전체 삭제
  const deleteall=()=>{
    setHave_list([]);
  }

  // x버튼 클릭 시 선택된 항목에서 삭제
  // ingredientId는 string, item.ingredient_id는 int임을 주의하자!
  const deleteItem = (e) => {
    const ingredientId = e.currentTarget.getAttribute('value')
    const newHavelist = havelist.filter((item) => item.ingredient_id != ingredientId)
    setHave_list(newHavelist)
  }

  return(
    <div className="add_item_page">
      <div className='add_item_top'>
        <div className='add_item_title'>냉장고 재고 추가하기</div>
        <div className='add_item_complete' onClick={()=>goAdd()}>완료</div>
      </div>

      <SearchBar 
        placeholder={"재료를 검색하세요."}
        data = {ingitem}
        setData = {setItem}
        className="add_item_search" />
      <div className='add_item_middle'>
        <div className='add_item_choice'>선택된 항목</div>
        <div className='add_all_delete' onClick={()=>deleteall()}>전체 삭제</div>
      </div>
      <div className='add_choice_item'>
        {
          havelist?.map((item) => {
            return (
              <div className='have_item' value={item.ingredient_id} key={item.ingredient_id}  onClick={(e) => deleteItem(e)}>
                {item.ingredient_name}
                <img src={x_btn} alt="X" className="add_x_btn"/>
              </div>
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
