import { useRouteMatch } from "react-router-dom";
import CommunityDetailChat from "./components/community/CommunityDetailChat";
import foodimg from "./img/food.jpg";
import heartimg from "./img/heart.png";
import proimg from "./img/nor.jpeg";
import './css/CommunityPostDetail.css';

function CommunityPostDetail() {
    const match = useRouteMatch();
    const id = match.params.id
    console.log(id)
    return(
        <div className="community-post-detail">
            <div className="post-writer">
                <img className="community-detail-info-member-img" src={proimg} alt={""}/>

                <div className="community-detail-info-member-name">
                     팀원
                </div>
            </div>
            <div>안뇽 여기는 {id}방</div>
            <div className="post-id"></div>
                <div className="food-img">
                    <img className="community-post-detail-img" src={foodimg} alt="">
                    
                    </img>
                    <div className="like-circle">
                        <img className="like-icon" src={heartimg} alt=""/>
                    </div>
                </div>
                
                <div className="post-content">
                    <div>제목</div> 
                    <div>내용 </div>
                </div>
            
            <div className="community-chat">
                <CommunityDetailChat></CommunityDetailChat>
            </div>
        </div>
    ); 
}

export default CommunityPostDetail