import "./css/StudyDetailInfo.css"
import proImg from "../../img/nor.jpeg";

function StudyDetailInfo() {
    return (
        <div className="study-detail-info">
            <div className="study-detail-info-top">
                <div className="study-detail-info-time"># n시</div>
                <div className="study-detail-info-update">수정하기</div>
            </div>
            <div className="study-detail-info-content">
            이곳은 방장이 공지사항을 작성하기 위해 사용하는 곳으로 스터디의 각종 적보를 적어놓는 곳입니다.<br></br>
            방장만이 수정할 수 있고요 위의 편집하기 버튼을 눌러서 편집할 수 있습니다 하하하
            최대 작성가능한 글자수를 제한해야하는데 몇자로 제한하는 것이 적당할까 고민이 많습니다만 한 200자로 하면 적당하지 않을까?? 생각중입니다 그리고 편집시 이 화면 크기와 동일하게 작성되도록 하면 좋을듯
            </div>
            
            <div className="study-detail-info-member">
                <div>
                    참여자 {}/4
                </div>
                <div className="study-detail-info-member-display">
                    <div className="study-detail-info-member-img">
                        <img src={proImg} alt={""}/>
                    </div >
                    <div className="study-detail-info-member-name">
                        방장
                    </div>
                    <div className="study-detail-info-member-img">
                        <img src={proImg} alt={""}/>
                    </div>
                    <div className="study-detail-info-member-name">
                        나의이름
                    </div>
                    <div className="study-detail-info-member-img">
                        <img src={proImg} alt={""}/>
                    </div>
                    <div className="study-detail-info-member-name">
                        팀원
                    </div>
                    <div className="study-detail-info-member-img">
                        <img src={proImg} alt={""}/>
                    </div>
                    <div className="study-detail-info-member-name">
                        팀원
                    </div>
                </div>
            </div>
            <div className="study-detail-info-button">
                <div >탈퇴</div>
                <div >미팅참여</div>
            </div>

        </div>
      
    );
  }
  
  export default StudyDetailInfo;
  