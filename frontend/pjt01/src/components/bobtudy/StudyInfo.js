import { useState } from "react";
import "./css/StudyInfo.css"
<<<<<<< HEAD
import {useHistory  } from "react-router-dom";

function StudyInfo() {
    const history = useHistory();
    const id= 6;
    function changeBtnName()  {
        const btnElement = document.getElementsByClassName('accordion-button');
        btnElement.innerText = '새이름!';
=======
import { Link } from "react-router-dom";
import user_img from '../../img/Users.png'
import StudyMember from "./StudyMember";

function StudyInfo(props) {

  const [modal, setModal] = useState(false);
  const cnt_mem = props.study.member.length;
  const data = props.study;

  return (
    <div className="study_info">
      { /* 이름과 인원 아이콘 */}
      <div className="study_top">
        <div className="study_name">{ props.study.name }</div>
        <div className='study_member'><img src={user_img} alt="user" className="study_member_img"/>{ cnt_mem + 1 }/{ cnt_mem + 1 }</div>
      </div>
      {/* 스터디 설명 간략히 */}
      <div className="study_short">{ props.study.summary.slice(0,25) }...</div> 
      {/* 스터디 시간 */}
      <div className="study_time"># { props.study.time }시</div>
      <div className="modal_btn" onClick={()=>setModal(true)}>자세히</div>
      { modal === true ? <Modal data={data} /> : null }
      { modal === true ? <div className="modal_close_btn" onClick={()=>setModal(false)}>X</div> : null }
    </div>
  );
}

function Modal(data) {

  const study = data.data
  const cnt_modal = study.member.length  

  return (
    <div className="study_modal">
      <div className="modal_name">{ study.name }</div>
      <div className="modal_short">{ study.summary }</div> 
      <div className="modal_time"># { study.time }시</div>
      <div className="modal_top">
        <div className='modal_member'><div className="study_joined_mem">참여자</div><img src={user_img} alt="user" className="modal_img"/>{ cnt_modal + 1 }/{ cnt_modal + 1 }</div>
      </div>
    <div className="modal_person">
      <StudyMember member={study.king} image={user_img} className="modal_king" />
      {
        study.member.map((member, index) => {
          return <StudyMember member={member} image={user_img} className="modal_person_item" key={index} />
        })
>>>>>>> develop
      }
    </div>
    <Link to={'/study/' + study.id} state={{id: study.num}} >
    <div className="move_study_detail">가입하기</div>
    </Link>
    </div>
  )
}

<<<<<<< HEAD
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
=======
export default StudyInfo;
>>>>>>> develop
  