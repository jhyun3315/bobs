import { useRouteMatch } from "react-router-dom";
// import CommunityDetailChat from "../components/community/CommunityDetailChat";
import Comment from '../components/community/CoummunityComment'
import CommentForm from '../components/community/CommunityCommentForm'
import CommentList from '../components/community/CommunityCommentList'
import proimg from "../img/nor.jpeg";
import './css/CommunityPostDetail.css';
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import axios from 'axios'

function CommunityPostDetail() {
  const match = useRouteMatch();
  const history = useHistory();
  const id = match.params.id;
  const [post, setPost] = useState();
  const [cmt, setCmt] = useState([]);
  const [isuser, setIsuser] = useState()
  const local_id =(localStorage.getItem('id'));

  useEffect(() => {
    // const url_post = "http://localhost:8080/communities/"
    // const url_comment = "http://localhost:8080/community/comment"
 
    axios
      .all([axios.get("https://i8b304.p.ssafy.io/api/communities/" + id),
            axios.get("https://i8b304.p.ssafy.io/api/community/comment", {params : { "value" : id }})])
      .then(
        axios.spread((res1, res2) => {
          setPost(res1.data.data)
          // setIsuser(res1.data.data.user_id)
          console.log(res1.data.data)
          setCmt(res2.data.data)
        })
      )
      .catch((e) => console.log(e))
      },[])


  const delete_post = () => {
    const url = "https://i8b304.p.ssafy.io/api/community/"
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

  const addList = (content) => {

    let data =  {
      "user_id" : local_id,
      "community_id" : Number(id),
      "community_comment_content" : content
    }
    const config = {"Content-Type": 'application/json'};
    
    axios.post("https://i8b304.p.ssafy.io/api/community/comment",data, config)
    .then((res) => console.log(res.data))
    .catch((err) => console.log(err))
   

    setCmt([...cmt, 
    {
      "user_id": local_id,
      "community_id": Number(id),
      "community_comment_id": cmt.length + 1,
      "community_comment_content": content,
      "community_comment_created": "2023-02-12T00:00:00",
      "community_comment_deleted": false
    }])

  }

  console.log(local_id, isuser)

  const updateList = list => {   
    setCmt(list)
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
          {
            local_id === isuser ?
          <><div className="community_edit_btn" onClick={edit_post}>수정하기</div>
          <div className="community_delete_btn" onClick={delete_post}>삭제하기</div></>
          : null
        }
      </div>
      <div className="food">
        <img className="food_img" src={post?.community_img} alt="" />
      </div>
      <div className="article">
        <div className="title">{post?.community_title}</div> 
        <div className="content">
          {post?.community_content}
        </div>
      </div>
      <div className="community_chat">
        { cmt !== [] ? 
          <Comment>
            <CommentList list={cmt} updateList = {updateList}  />
            <CommentForm addList = {addList}
            />
          </Comment>
        : null}
      </div>
    </div>
  );
}

export default CommunityPostDetail