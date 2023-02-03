import "./css/CommunityPost.css"
import proImg from "../../img/nor.jpeg";

function CommunityPost() {
    return (
      <div className="community-post">
          <img className="community-post-img" src={proImg} alt="" />
          <div className="community-post-box1">
            <div className="community-post-title">
              오늘은 매운 떡뽁이
            </div>
            <div className="community-post-box2">
              <div className="community-post-like">15.5k</div>
              <div className="community-post-commentcnt">댓글 {}개</div>
            </div>
          </div>
        
      </div>
    );
  }
  
  export default CommunityPost;