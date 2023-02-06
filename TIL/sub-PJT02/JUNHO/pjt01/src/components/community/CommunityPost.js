import "./css/CommunityPost.css"
import proImg from "../../img/food.jpg";

function CommunityPost() {
    return (
      <div className="community_post">
          <img className="community_post_img" src={proImg} alt="" />
          <div className="community_post_content">
            <div className="community_post_title">
              오늘은 매운 떡뽁이
            </div>
            <div className="community_post_info">
              <div className="community-post-like">15.5k</div>
              <div className="community-post-commentcnt">댓글 {}개</div>
            </div>
          </div>
        
      </div>
    );
  }
  
  export default CommunityPost;