import { useState } from 'react'
import './css/addItemPage.css'
import data from './item.data.js'
import SearchBar from '../components/SearchBar'

function AddItemPage() {
  
  const [item, setItem] = useState();
  
  return(
    <div className="add_item_page">
      <div className='add_item_top'>
        <div className='add_item_title'>냉장고 재고 추가하기</div>
        <div className='add_item_complete'>완료</div>
      </div>

      <SearchBar 
        placeholder={"재료를 검색하세요."}
        data = {data}
        setItem = {setItem}
        className="add_item_search" />
      <div className='add_item_middle'>
        <div className='add_item_choice'>선택된 항목</div>
        <div className='add_all_delete'>전체 삭제</div>
      </div>
      <div className='add_choice_item'>
        
      </div>
      <div className='add_search_list'>
        {
          item?.map((item) => {
            return <div key={item.id} className="add_search_item" >{item.name}</div>
          })
        }
      </div>

    </div>
  )
} export default AddItemPage