import "./css/StudyInfo.css"

function StudyInfo() {
    
    return (
        <div className="study_info">
              
                <div className="study_name">이름</div>
                <div className="study_short">스터디 짧 정보</div>
                
                <div className="study_time">#n시</div>
                <div className="study_member">방장</div>

                <div className="study_detail">스터디 정보</div>
                <input id="study_btn" type="checkbox"></input>
                <label htmlFor="study_btn"></label>
                <div className="accordion" id="accordionExample">
                <div className="accordion-item">
                    <h2 className="accordion-header" id="headingOne">
                    <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                        자세히 보기
                    </button>
                    </h2>
                    <div id="collapse1" className="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                    <div className="accordion-body">
                        <strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                    </div>
                    </div>
                </div>
                
                </div>
        </div>
    );
  }
  
  export default StudyInfo;
  