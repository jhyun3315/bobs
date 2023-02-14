import { useState } from 'react'
import './css/addItemPage.css'
import data from './item.data.js'
import x_btn from '../img/x_btn.png'
import SearchBar from '../components/SearchBar'

function AddItemPage() {
  
  const [item, setItem] = useState();
  const [havelist, setHave_list] = useState([]);

  const additem=(item)=>{
    if (!havelist.includes(item))
    setHave_list([ item, ...havelist ])
  };
  
  return(
    <div className="add_item_page">
      <div className='add_item_top'>
        <div className='add_item_title'>냉장고 재고 추가하기</div>
        <div className='add_item_complete'>완료</div>
      </div>

      <SearchBar 
        placeholder={"재료를 검색하세요."}
        data = {data}
        setData = {setItem}
        className="add_item_search" />
      <div className='add_item_middle'>
        <div className='add_item_choice'>선택된 항목</div>
        <div className='add_all_delete'>전체 삭제</div>
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
      <div className='add_search_list'>
        {
          item?.map((item) => {
            return <div key={item.id} className="add_search_item" onClick={() => additem(item) }>{item.name}</div>
          })
        }
      </div>

    </div>
  )
} export default AddItemPage
