import './css/RefridgeratorEditPage.css'
import '../components/SearchBar.css'
import delete_icon from '../img/x.png'
import search_icon from '../img/search_item.png'
import { useState } from 'react';
import axios from "axios"
import { useEffect } from 'react';
import { useHistory, Prompt } from 'react-router-dom';


function RefridgeratorEditPage() {
  // const [ingredients, setIngredients] = useState(loction.content.ingredients)
  const [item, setItem] = useState(null)
  const [text, setText] = useState('');
  const [data, setData] = useState(null)
  const [ingredients, setIngredients] = useState([])
  const history = useHistory();
  const ingredientsLIst = ingredients?.map((item) => <div key={item.ingredient_id}>{item.ingredient_name}</div>)
  const local_id = localStorage.getItem("id")
  const url = "https://i8b304.p.ssafy.io/api"
  const [notset,setnotset] =useState(false)
  const [confirmModal, setconfirmModal] = useState(false)
  const modalOn = () => {
    setconfirmModal(true)
  }
  useEffect(() => {
    var data = JSON.stringify(local_id);
    var config = {
      method: 'post',
      url: url+"/refriges",
      headers: { 
        'Content-Type': 'application/json'
      },
      data : data
    };
    axios(config)
    .then((res) => {
      setItem(res.data.data); 
      setData(res.data.data);
      // console.log(res.data.data)

    })
    // .catch((e) => console.log(e))
  },[])

  const editRefrige = () => {
    setnotset(true);
    const list = ingredients?.map((item)=>item.ingredient_id)
      var inlist=[]
      for (let index = 0; index < list.length; index++) {
         inlist =[...inlist,{
          "ingredient_id" : list[index],
          "is_deleted" : true,
          "is_prior" : false
         }];
      }
      // console.log(list)
      // console.log(inlist)
      axios.put(url + "/refriges",
        {
          "user_id" : local_id,
          "ingredient_list": inlist
        }).then((res) => {
          // console.log(res.data); 
          
          history.push("/refridgerator")
        })
        //   .catch((e) => 
        //   console.log(e
        // ))
  }



  return (
    <div className="refridgerator_edit_page">
      {notset ? 
        null
      :
        <Prompt when={true} message={'다 쓴 재료를 \n등록하지 않으시면 \n나의 냉장고 재고가 \n달라질 수 있어요😥\n그래도 나가시겠어요?'} />
      }
      <div className="top">
        <div className="title">다 쓴 재료 등록</div>
        <div className='finish' onClick={editRefrige}>완료</div>
      </div>
      <div className='select_ingredients'>
        <div className='info'>다 쓴 재료를 선택해주세요</div>
        <div className='ingredients_list'
          onClick={(e) => {
            setIngredients(ingredients?.filter(item => item.ingredient_name !== e.target.innerText))
          }}>
          {ingredientsLIst}
        </div>
      </div>
      <div className="select_ex_ingredients">
        <div className='info'>추가 재료를 등록해주세요</div>
        <div className='search_input'>
          <div className='img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
          <input type="text" value={text} id='search_input'
            onChange={(e) => {
              setText(e.target.value);
              setItem(data?.filter(i => i.ingredient_name.includes(e.target.value)))
            }}
            placeholder="재료를 검색하세요." />
          <div className='img_icon'><img src={delete_icon} alt="delete" className="delete_item" onClick={() => setText("")} /></div>
        </div>
      </div>
      <div className='select_ex_list'>
        {
          item?.map((item, index) => {
            return (
              <div key={index} className='select_ex_item'
                onClick={() => {
                  if (!ingredients.includes(item.ingredient_name))
                    setIngredients([{ "ingredient_id": item.ingredient_id, "ingredient_name": item.ingredient_name }, ...ingredients])
                }}>{item.ingredient_name}</div>
            )
          })
        }
      </div>
      { confirmModal ? 
        <ConfirmModal setconfirmModal={setconfirmModal}/> :
        null
      }
    </div>
  )
}

function ConfirmModal(props) {
  const history = useHistory()
  const confirmYes = () => {
    props.setconfirmModal(false)
    
  }
  return (
    <div className='confirm_modal'>
      <div className='alert'>
        <div className='title'>{props.title}</div>
        <div className='content'>{props.content}</div>
        <div className="btn_box">
          <div className='yes_btn' onClick={confirmYes}>네</div>
          <div className='no_btn' onClick={() => props.setconfirmModal(false)}>아니요</div>
        </div>
      </div>
    </div>
  )
}

export default RefridgeratorEditPage