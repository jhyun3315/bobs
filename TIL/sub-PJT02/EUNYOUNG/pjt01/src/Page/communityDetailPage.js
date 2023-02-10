import { useRouteMatch } from "react-router-dom";
import CommunityDetailChat from "../components/community/CommunityDetailChat";
import foodimg from "../img/food.jpg";
import heart from "../img/red_heart.png";
import heart_b from "../img/empty_heart.png";
import proimg from "../img/nor.jpeg";
import './css/CommunityPostDetail.css';
import back_btn from "../img/back_btn.png";
import { useState } from "react";
import { useHistory } from "react-router-dom";

function CommunityPostDetail() {
  const match = useRouteMatch();
  const id = match.params.id;
  const [isLike, setIsLIke] = useState(true)
  const likeClik = () => {
    setIsLIke(!isLike)
  }
  const history = useHistory();
  const toCommunity = (e) => {
    history.push("/community");
  }
  return (
    <div className="community_post_detail">
      <div className="top">
        <div className="writer">
          <img className="profile" src={proimg} alt={""} />
          <div className="name">
            ㄴㅁ아ㅣ람니ㅓ랃저라ㅣㅓㅁㄴ아리ㅓ마지더라ㅣㅓㅏㅣㄷ럼ㄴㅇ러
          </div>
        </div>
        <img className="back_btn" src={back_btn} alt=""  onClick={toCommunity}/>
      </div>
      <div className="food">
        <img className="food_img" src={foodimg} alt="" />
        <div className="like_btn" onClick={likeClik}>
          <img className="like_icon" src={isLike ? heart : heart_b} alt="❤" />
        </div>
      </div>
      <div className="article">
        <div className="title">우와 풀이다</div> 
        <div className="content">
        내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용
        </div>
      </div>
      <div className="community_chat">
        <CommunityDetailChat />
      </div>
    </div>
  );
}

export default CommunityPostDetail