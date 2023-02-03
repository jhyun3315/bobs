import "./css/CommunityPost.css"
import foodimg from "../../img/food.jpg";
import {useHistory  } from "react-router-dom";

function CommunityPost() {
    const history = useHistory();
    const id=6;
    function goCommunityDetail(){
      history.push("/community/id="+id);
    }
    return (
      <div className="community-post" onClick={goCommunityDetail}>
          <img className="community-post-img" src={foodimg} alt="" />
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