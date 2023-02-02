import CommunityPost from "./components/community/CommunityPost";
import './css/CommunityPage.css';


function CommunityPage() {
    return (
      <div>
        <div className="community-title">
          커뮤니티 
        </div>
        <div className="community-button">
          <div>
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