import CommunityPost from "../components/community/CommunityPost";
import './css/CommunityPage.css';
import { useHistory } from "react-router-dom";
import pen from "../img/pen.png";
import Toggle from '../components/Toggle.component'
import { useEffect, useState } from "react";
import SearchBar from '../components/SearchBar'
import axios from "axios";

function CommunityPage() {
    

    const tmpdata= [
      {
        post:1
    }

    ]
    const history = useHistory();
    const toCommunityCreate = (e) =>{
      history.push("/communityCreate");
    };
    const [communityItem, setcommunityItem] = useState(tmpdata)
    const [scommunityItem, setscommunityItem] = useState([])
    const [checked, setChecked] = useState(false)
    useEffect(() => {
      const url="https://i8b304.p.ssafy.io/api/communities";
      axios.get(url,{
        params : {
          key1: JSON.stringify({
            "page": 1,
          })
        }
      })
        .then(function(response) {
          setcommunityItem(response.data);
          console.log("성공");
      })
        .catch(function(error) {
            console.log("실패");
      })
    
    }, [communityItem])

    
  const Post = () => {
    return (
      <div>
        {
          communityItem.map((post, index) => {
            return  <CommunityPost id={post} key={index}/>
          })
        }
      </div>
    );
  };

  const MyPost = () => {
    return (
      <div>
        {
          scommunityItem.map((post, index) => {
            return  <CommunityPost id={post} key={index}/>
          })
        }
      </div>
    );
  };


    return (
      <div className="community">
        <div className="community_title">
          소통해요 
        </div>
        <div className="search_bar">
          <SearchBar className="search_bar"

            placeholder={'검색어를 입력해주세요.'} />
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