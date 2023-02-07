import CommunityPost from "./components/community/CommunityPost";
import './css/CommunityPage.css';
import { useHistory } from "react-router-dom";
import pen from "./img/pen.png";
import Toggle from './components/Toggle.component'
import { useState } from "react";
import SearchBar from './components/SearchBar'

function CommunityPage() {
    const history = useHistory();
    const toCommunityCreate = (e) =>{
      history.push("/communityCreate");
    };
    const [checked, setChecked] = useState(false)
    return (
      <div className="community">
        <div className="community_title">
          소통해요 
        </div>
        <div className="search_bar">
          <SearchBar className="search_bar" placeholder='방제, 초대코드를 입력해주세요.' />
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
          <CommunityPost />
          <CommunityPost />
          <CommunityPost />
          <CommunityPost />
          <CommunityPost />
          <CommunityPost />
          <CommunityPost />
          <CommunityPost />
        </div> 
      </div>
    );
  }
  
  export default CommunityPage;