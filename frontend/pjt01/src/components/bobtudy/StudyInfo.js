import { useState } from "react";
import "./css/StudyInfo.css"
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
      }
    </div>
    <Link to={'/study/' + study.id} state={{id: study.num}} >
    <div className="move_study_detail">가입하기</div>
    </Link>
    </div>
  )
}

export default StudyInfo;
  