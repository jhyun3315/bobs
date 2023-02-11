import "./css/CommunityPost.css"
import foodimg from "../../img/food.jpg";
import { useHistory} from "react-router-dom";
import like from '../../img/heart.png'

function CommunityPost(props) {
    // console.log(props.id.post)
    const history = useHistory();
    function goCommunityDetail(){
      history.push({pathname: "/community/" + props.id.post, state: {id: props.id}});
    }
    return (
      <div className="community_post" onClick={goCommunityDetail}>
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