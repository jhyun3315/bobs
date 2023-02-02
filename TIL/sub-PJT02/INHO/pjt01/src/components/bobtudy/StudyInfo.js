import "./css/StudyInfo.css"
import {useHistory  } from "react-router-dom";

function StudyInfo() {
    const history = useHistory();
    const id= 6;
    function changeBtnName()  {
        const btnElement = document.getElementsByClassName('accordion-button');
        btnElement.innerText = '새이름!';
      }

      function goStudyDetail(){
        history.push("/study/id="+id);
      }
    return (
        <div className="study_info">
              
                <div className="study_name">이름</div>
                <div className="study_short">스터디 짧 정보</div> 
                <div className="study_time">#n시</div>
                


                <div className="accordion" id="accordionExample">
                <div className="accordion-item">
                    <h2 className="accordion-header" id="headingOne">
                    
                    </h2>
                    
                    <div id="collapse1" className="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                    <div className="accordion-body">
                        <div className="study_member">방장</div>
                        <div className="study_detail">스터디 정보</div>
                        <button type="button" className="btn btn-primary" onClick={goStudyDetail}>가입하기</button>
                    </div>
                    </div>
                    <button className="accordion-button" type="button" data-hover="dd" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1" onClick={changeBtnName}>
                        자세히 보기
                    </button>
                </div>
                
                </div>
        </div>
    );
  }
  
  export default StudyInfo;
  