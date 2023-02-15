import { useHistory } from "react-router-dom"
import { useState } from "react";
import './css/studyCreatePage.css'
import axios from "axios";

function StrudyCreatePage() {

  const history = useHistory();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [time , setTime] = useState(0);
  const [minute, setMinute] = useState(0);
  const local_id= localStorage.getItem("id");
  const [study_time, setStudytime] = useState("")

  function create() {
    if(0 <= time <= 23 && 0 <= minute <= 59) setStudytime(`${time}시 ${minute}분`)
    else alert("올바른 시간을 입력해 주세요.") 
    if(title.trim() === "") alert("제목을 입력해 주세요")
    if(content.trim() === "") alert("규칙 및 공지사항을 입력해주세요.")
    else {
      // const url="https://i8b304.p.ssafy.io/api/studies"
      const url="http://localhost:8080/api/studies";
      axios.post(url,{
          "study_content" : content,
          "study_time" : study_time,
          "study_title" : title,
          "user_id" : local_id
        },
        {
          headers : {
            "Content-Type": "application/json",
        },
        
      })
        .then(function(response) {
          console.log(response.data);
      })
        .catch(function(error) {
            console.log("실패");
      })

      history.push("/study")
    }
    console.log(study_time)
  }

  const err = () => {
    if (time > 24){ alert("시간은 최대 23 까지 입력 가능합니다. "); setTime(0) }
    else if (time < 0) {alert("시간은 0 부터 입력이 가능합니다."); setTime(0)}
    if (minute > 59) {alert("분은 최대 59 까지 입력 가능합니다. "); setMinute(0)}
    else if (minute < 0) {alert("분은 0 부터 입력이 가능합니다."); setMinute(0)}
  }


  return(
    <div className="study_create">
      <div className="study_crate_title">
        <input type="text"
          value={title} 
          onChange={(e)=>setTitle(e.target.value)} 
          className="study_create_input"
          placeholder="스터디 이름 (최대 15자 공백포함)" 
          maxLength={15}
        />
      </div>
      <div className="study_create_content">
        <textarea 
          value={content} 
          onChange={(e)=>setContent(e.target.value)} 
          className="study_create_area" 
          placeholder="스터디 규칙 및 공지 (최대 200자 공백포함)" 
          maxLength={200}
        />
      </div>
      <div className="study_create_time">
        <input type="number" value={time}        
          onChange={(e) => {
            setTime(e.target.value);
            err()
          }}
          min = {0}
          max = {24}
          maxLength= {2} 
          className="time_input" required/>
         시
         <input type="number" value={minute}        
            onChange={(e) => {
            setMinute(e.target.value);
            err()
          }}
          className="time_input" required/>
         분
      </div>

      <div className="create_study_complete" >
        <div className="cancel_btn" onClick={()=>history.goBack()}>취소하기</div>
        <div className="complete_btn" onClick={create}>완료하기</div>
      </div>
    {/* <div className="click">touch</div> */}


    </div>
)} 
    
export default StrudyCreatePage
