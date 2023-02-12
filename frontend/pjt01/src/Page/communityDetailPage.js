import { useRouteMatch } from "react-router-dom";
import CommunityDetailChat from "../components/community/CommunityDetailChat";
import heart from "../img/red_heart.png";
import heart_b from "../img/empty_heart.png";
import proimg from "../img/nor.jpeg";
import './css/CommunityPostDetail.css';
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import axios from 'axios'

function CommunityPostDetail() {
  const match = useRouteMatch();
  const history = useHistory();
  const id = match.params.id;
  const [isLike, setIsLIke] = useState(true)
  const [post, setPost] = useState()
  const likeClik = () => {
    setIsLIke(!isLike)
  }

  useEffect(() => {
    const url = "http://localhost:8080/api/communities/" + id
    axios.get(url,{
    })
      .then(function(response) {
        setPost(response.data.data)
        console.log(response.data.data);
    })
      .catch(function(error) {
        console.log(error);
    })
  
  }, [])

  const delete_post = () => {
    const url = "http://localhost:8080/api/communities/"
    axios.delete(url,{
      params : {
        "value" : match.params.id
      }
    })
      .then(function(response) {
        console.log(response.data.data);
        history.push('/community')
    })
      .catch(function(error) {
        console.log(error);
    })
  
  }

  const edit_post = () => {
    history.push({pathname: '/communityCreate/', state: {title : post?.community_title, content : post?.community_content, img : post?.community_img, id : match.params.id}})
  }

  return (
    <div className="community_post_detail">
      <div className="top">
        <div className="writer">
          <img className="profile" src={proimg} alt={""} />
          <div className="name">
            {post?.user_name}
          </div>
        </div>
        </div>
        <div className="edit_delete_btn">
          <div className="community_edit_btn" onClick={edit_post}>수정하기</div>
          <div className="community_delete_btn" onClick={delete_post}>삭제하기</div>
        
      </div>
      <div className="food">
        <img className="food_img" src={post?.community_img} alt="" />
        <div className="like_btn" onClick={likeClik}>
          <img className="like_icon" src={isLike ? heart : heart_b} alt="❤" />
        </div>
      </div>
      <div className="article">
        <div className="title">{post?.community_title}</div> 
        <div className="content">
          {post?.community_content}
        </div>
      </div>
      <div className="community_chat">
        <CommunityDetailChat />
      </div>
    </div>
  );
}

export default CommunityPostDetail