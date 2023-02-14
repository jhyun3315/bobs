import "./css/CommunityPost.css"
import { useHistory} from "react-router-dom";

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
              <div className="comment_cnt">댓글 {'15.4k'}개</div>
            </div>
          </div>
      </div>
    );
  }
  
  export default CommunityPost;