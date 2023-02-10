import { useHistory } from "react-router-dom"
import { useState } from "react";
import './css/studyCreatePage.css'

function StrudyCreatePage() {

  const history = useHistory();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [time , setTime] = useState("");

  function create() {
    if(time === "") alert("시간 입력은 필수 입니다.")
    else if(title === "") alert("제목을 입력해 주세요")
    else if(content === "") alert("규칙 및 공지사항을 입력해주세요.")
    else {
      history.push("/study")
    }
    console.log(time)
  }


  return(
    <div className="study_create">
      <div className="study_crate_title">
        <input type="text"
          value={title} 
          onChange={(e)=>setTitle(e.target.value)} 
          className="study_create_input"
          placeholder="스터디 이름 (최대 15자 공백포함)" />
      </div>
      <div className="study_create_content">
        <textarea 
          value={content} 
          onChange={(e)=>setContent(e.target.value)} 
          className="study_create_area" 
          placeholder="스터디 규칙 및 공지 (최대 200자 공백포함)" />
      </div>
      <div className="study_create_time">
        <input type="time" value={time}        
         onChange={(e) => {
            setTime(e.target.value);
          }} 
         className="time_input" required/>
      </div>

      <div className="create_study_complete" >
        <div className="cancel_btn" onClick={()=>history.push('/study')}>취소하기</div>
        <div className="complete_btn" onClick={create}>완료하기</div>
      </div>
    <div className="click">touch</div>


    </div>
)} 
    
export default StrudyCreatePage