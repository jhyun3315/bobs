import { useState } from "react";
import "./css/StudyInfo.css"
import { Link, useHistory } from "react-router-dom";
import user_img from '../../img/Users.png'
import StudyMember from "./StudyMember";
import axios from "axios";


function StudyInfo(props) {
  const [modal, setModal] = useState(props?.modal);
  const cnt_mem = 0;
  const data = props?.study;
  console.log(props);

  return (
    <div className="study_info">
      { /* 이름과 인원 아이콘 */}
      <div className="study_top">
        <div className="study_name">{ data?.study_title }</div>
        <div className='study_member'><img src={user_img} alt="user" className="study_member_img"/>{ cnt_mem }/4</div>
      </div>
      {/* 스터디 설명 간략히 */}
      <div className="study_short">{data?.study_content}</div> 
      {/* 스터디 시간 */}
      <div className="study_time"># { data?.study_time }</div>
      <div className="modal_btn" onClick={()=>setModal(true)}>자세히</div>
      { modal === true ? <Modal data={data} setModal={setModal} /> : null }
    </div>
  );
}

function Modal(data) {
  const study = data?.data
  const history = useHistory()
  const toStudyDetail = () => {
    history.push(`/study/${study.study_id}`)
  }
  const joinStudy = () => {
    const url = "http://localhost:8080/studymembers"
    let data = {
      "user_id" : "5",
      "study_id" : study.study_id
    }
    axios.post(url, data).then(() => toStudyDetail()).catch((e) => console.log(e))
  }

  return (
    <div className="study_modal">
      <div className="modal_top">
        <div className="modal_name">{ study?.study_title }</div>
        <div className="modal_close_btn" onClick={()=>data.setModal(false)}>X</div>
      </div>
      <div className="modal_short">{ study?.study_content }</div> 
      <div className="modal_time"># { study?.study_time }</div>
      <div className="modal_bottom">
        <div className='modal_member'><div className="study_joined_mem">참여자</div><img src={user_img} alt="user" className="modal_img"/></div>
      </div>
      <div className="modal_person">
        <StudyMember member={study.user_id} image={user_img} className="modal_king" />
        {
          study.member?.map((member, index) => {
            return <StudyMember member={member} image={user_img} className="modal_person_item" key={index} />
          })
        }
      </div>
      <div className="move_study_detail" onClick={joinStudy}>가입하기</div>
    </div>
  )
}

export default StudyInfo;
  