import CommunityPost from "../components/community/CommunityPost";
import './css/CommunityPage.css';
import { useHistory } from "react-router-dom";
import pen from "../img/pen.png";
import '../components/SearchBar.css'
import delete_icon from '../img/delete_btn.png'
import search_icon from '../img/search_item.png'
import Toggle from '../components/Toggle.component'
import { useEffect, useState } from "react";
import axios from "axios";

function CommunityPage() {
  const history = useHistory();
  const toCommunityCreate = (e) =>{
    history.push("/communityCreate");
  };
  
  const [text, setText] = useState('');
  const [sdata, setsData] = useState();
  const [data, setData] = useState();
  const [communityItem, setcommunityItem] = useState()
  const [scommunityItem, setscommunityItem] = useState()
  const [checked, setChecked] = useState(false)
  const local_id = localStorage.getItem("id");

  useEffect(() => {
    const url = "https://i8b304.p.ssafy.io/api/communities";
    // const url = "http://localhost:8080/communities";
    axios.get(url, {
    })
      .then(function(response) {
        setcommunityItem(response.data.data);
        setData(response.data?.data)
    })
      .catch(function(error) {
        console.log(error);
    })
  
  }, []
)

useEffect(() => {
  // const url="https://i8b304.p.ssafy.io/api/communities/user";
  const url = "http://localhost:8080/communities/user"
  let data = local_id
  axios.post(url, JSON.stringify(data),{
    headers : {
      "Content-Type" : 'application/json'
    },
  })
    .then(function(response) {
      setscommunityItem(response?.data?.data)
      setsData(response.data?.data)
  })
    .catch(function(error) {
      console.log(error);
  })

}, [])

  
const Post = () => {
  return (
    <div>
    {
      communityItem?.map((post) => {
        return  <CommunityPost id={post} key={post?.community_id}/>
      })
    }
    </div>
  );
};

const MyPost = () => {
  return (
    <div>
      {
        scommunityItem?.map((post) => {
          return  <CommunityPost id={post} key={post?.community_id
          }/>
        })
      }
    </div>
  );
};


  return (
    <div className="community">
      <div className="ref_title">
        소통해요 
      </div>
      <div className="com_search">
        <div className='search_input'>
          <div className='img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
          <input type="text" value={text} id='search_input'
            onChange={(e) => {
              setText(e.target.value);
              checked ?
              setscommunityItem(sdata?.filter(i => i.community_title.includes(e.target.value))) :
              setcommunityItem(data?.filter(i => i.community_title.includes(e.target.value)))
            }}
            placeholder="제목을 검색해 주세요."/>
          <div className='img_icon'><img src={delete_icon} alt="delete" className="delete_item" onClick={() => setText("")} /></div>
        </div>
      </div>
      <div className="community_button">
        <div className="community_write" onClick={toCommunityCreate}>
          <div>글쓰기</div>
          <div><img src={pen} alt="" /></div>
        </div>
        <Toggle
          checked = {checked}
          onChange = {(e) => {
            setChecked(e.target.checked)
          }}
          offstyle="off"
          onstyle="on"
          text="내가 쓴 글"
        />
      </div>
      <div className="community_list">
        {checked ? <MyPost /> : <Post />}
      </div> 
    </div>
  );
}
  
  export default CommunityPage;