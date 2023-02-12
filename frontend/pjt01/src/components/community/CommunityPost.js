import "./css/CommunityPost.css"
import { useHistory} from "react-router-dom";
import like from '../../img/red_heart.png'

function CommunityPost(props) {
    const data = props.id
    const history = useHistory();
    function goCommunityDetail(){
      history.push({pathname: "/community/" + data.community_id, state: {id: data.community_id}});
    }
    return (
      <div className="community_post" onClick={goCommunityDetail}>
          <img className="img" src={data.community_img} alt="" />
          <div className="content">
            <div className="title">
              {data.community_title}
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