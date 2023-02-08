import CommunityPost from "../components/community/CommunityPost";
import './css/CommunityPage.css';
import { useHistory } from "react-router-dom";
import pen from "../img/pen.png";
import Toggle from '../components/Toggle.component'
import { useState } from "react";
import SearchBar from '../components/SearchBar'

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
          <SearchBar className="search_bar" placeholder='검색어를 입력해주세요.' />
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
          <CommunityPost id="0" key="0"/>
          <CommunityPost id="1" key="1"/>
          <CommunityPost id="2" key="2"/>
          <CommunityPost id="3" key="3"/>
          <CommunityPost id="4" key="4"/>
          <CommunityPost id="5" key="5"/>
          <CommunityPost id="6" key="6"/>
          <CommunityPost id="7" key="7"/>
        </div> 
      </div>
    );
  }
  
  export default CommunityPage;