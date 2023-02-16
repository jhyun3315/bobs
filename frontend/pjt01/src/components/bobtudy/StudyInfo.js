import { useEffect, useState } from "react";
import "./css/StudyInfo.css"
import { Link, useHistory } from "react-router-dom";
import user_img from '../../img/Users.png'
import x_btn from '../../img/x.png'
import StudyMember from "./StudyMember";
import axios from "axios";


function StudyInfo(props) {
  const url ="https://i8b304.p.ssafy.io/api"
  const id= localStorage.getItem("id")
  // const id = "5"
  const [studyData,setStudyData] =useState("")
  const [modal, setModal] = useState(props.modal);
  // console.log(props.study)
  const data = props.study;
  useEffect(() => {
    var config = {
      method: 'post',
      url: url+"/studymembers/info",
      headers: { 
        'Content-Type': 'application/json'
      },
      data : {
        "user_id" : id,
        "study_id" : props.study.study_id 
      }
    };
    
    axios(config)
    .then((res) => {
      setStudyData(res.data.data)
    })
  

  }, [])
 

  return (
    <div className="study_info">
      { /* 이름과 인원 아이콘 */}
      <div className="study_top">
        <div className="study_name">{ props.study.study_title }</div>
        <div className='study_member'><img src={user_img} alt="user" className="study_member_img"/>{props.study.member_count}/4</div>
      </div>
      {/* 스터디 설명 간략히 */}
      <div className="study_short">{data?.study_content}</div> 
      {/* 스터디 시간 */}
      <div className="study_time"># { data?.study_time }</div>
      <div className="modal_btn" onClick={()=>setModal(true)}>자세히</div>
      { modal === true ? <Modal data={data} id={id} setModal={setModal} studyData={studyData} /> : null }
    </div>
  );
}

function Modal(data) {
  const study = data.data
  const studyData = data.studyData
  const [joincheck, setjoincheck] =useState(false)
  const [getjoined, setgetjoined] =useState([]);
  const history = useHistory()
  const url ="https://i8b304.p.ssafy.io/api"
  const iddata = localStorage.getItem("id");
  useEffect(() => {
    
    var config = {
      method: 'post',
      url: url+"/studies/user",
      headers: { 
        'Content-Type': 'application/json'
      },
      data : {
        "user_id": data.id
      }
      
    };
    axios(config)  
    .then(function (response) {
      const join =response.data.data;

      setgetjoined(join)
      for (let index = 0; index < join?.length; index++) {
        if(join[index].study_id===study.study_id){
          setjoincheck(true);
        }
      }
    
    })
    .catch(function (error) {
      console.log(error);
    });

  }, [])
  console.log(studyData)
  const toStudyDetail = () => {
    history.push(`/study/${study.study_id}`)
  }

  const joinStudy = () => {
    const url = "https://i8b304.p.ssafy.io/api/studymembers"
    let data = {
      "user_id" : iddata,
      "study_id" : studyData.study_id
    }
    axios.post(url, data).then(() => toStudyDetail()).catch((e) => console.log(e))
  }
  return (
    <div className="study_modal">
      <div className="modal_top">
        <div className="modal_name">{ study?.study_title }</div>
        <div className="modal_close_btn" onClick={()=>data.setModal(false)}>
          <img src={x_btn} alt="x" />
        </div>
      </div>
      <div className="modal_short">{ study?.study_content }</div> 
      <div className="modal_time"># { study?.study_time }</div>
      <div className="modal_bottom">
        <div className='modal_member'><div className="study_joined_mem">참여자</div><img src={user_img} alt="user" className="modal_img"/>{study?.member_count}/4</div>
      </div>
      <div className="modal_person">
        {/* <StudyMember member={studyData.member_list} image={user_img} className="modal_king" /> */}
        {
          studyData?.member_list?.map((member, index) => {
            return <StudyMember member={member.user_name} image={member.user_profile} className="modal_person_item" key={index} />
          })
        }
      </div>
        {joincheck ? 
          <div className="move_study_detail" onClick={toStudyDetail}>이동하기</div>
          :      
          <div className="move_study_detail" onClick={joinStudy}>가입하기</div>
        }
     
    </div>
  )
}

export default StudyInfo;
  