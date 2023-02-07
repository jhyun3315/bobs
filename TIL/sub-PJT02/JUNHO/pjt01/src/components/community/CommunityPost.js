import "./css/CommunityPost.css"
import foodimg from "../../img/food.jpg";
import { useHistory } from "react-router-dom";
import like from '../../img/heart.png'

function CommunityPost() {
    const history = useHistory();
    function goCommunityDetail(){
      // history.push({pathname: "/community/" + "2", state: {id: id}});
      history.push('/study/id='+ 2 )
    }
    return (
      <div className="community_post">
          <img className="img" src={foodimg} alt="" />
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