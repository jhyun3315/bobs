import CommunityPost from "../components/community/CommunityPost";
import './css/CommunityPage.css';
import {useHistory  } from "react-router-dom";

function CommunityPage() {
    const history = useHistory();
    const toCommunityCreate = (e) =>{
      history.push("/communityCreate");
  };
    return (
      <div>
        <div className="community-title">
          커뮤니티 
        </div>
        <div className="community-button">
          <div onClick={toCommunityCreate}>
            글쓰기
          </div>
          <div>
            내가쓴글
          </div>
        </div>
        <div>
            <CommunityPost>
            </CommunityPost>
        </div> 
      </div>
    );
  }
  
  export default CommunityPage;