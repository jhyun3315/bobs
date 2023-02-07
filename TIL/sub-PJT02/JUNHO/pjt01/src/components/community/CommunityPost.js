import "./css/CommunityPost.css"
import proImg from "../../img/food.jpg";
import like from '../../img/heart.png'

function CommunityPost() {
    return (
      <div className="community_post">
          <img className="img" src={proImg} alt="" />
          <div className="content">
            <div className="title">
              오늘은 매운 떡뽁이eeeeeee
            </div>
            <div className="info">
              <div className="like">
                <img className="like_img" src={like} alt="" />
                <div className="like_cnt">15.5k</div>
              </div>
              <div className="comment_cnt">댓글 {'15.4k'}개</div>
            </div>
          </div>
        
      </div>
    );
  }
  
  export default CommunityPost;