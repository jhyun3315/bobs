import { useHistory } from "react-router-dom"
import { useState } from "react";
import './css/studyCreatePage.css'
import axios from "axios";

function StrudyCreatePage() {

  const history = useHistory();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [study_time, setStudytime] = useState("")

  const local_id= localStorage.getItem("id");
  // const local_id = "5"

  function create() {
    if(title == "") alert("제목 입력은 필수입니다.")
    if(content == "") alert("내용 입력은 필수 입니다.")
    if(study_time == "") alert("시간 선택은 필수 입니다.")

    if(title && content && study_time){

      const url="https://i8b304.p.ssafy.io/api/studies/write"
      // const url="http://localhost:8080/studies";
      let data = {
        "study_content" : content,
        "study_time" : study_time,
        "study_title" : title,
        "user_id" : local_id
      }
      const config = {
        "Content-Type": "application/json",
    }
      axios.post(url, data, config)
        .then(function(response) {
          console.log(response.data);
          history.goBack()
      })
        .catch(function(e) {
            console.log(e);
      })
  
      // history.push("/study")
    }
  }

  return(
    <div className="study_create">
      <div className="study_crate_title">
        <input type="text"
          value={title} 
          onChange={(e)=>{
            e.target.value.length > 15 ? setTitle(e.target.value.slice(0, 15)) :
            setTitle(e.target.value)}} 
          className="study_create_input"
          placeholder="스터디 이름 (최대 15자 공백포함)" 
          maxLength={15}
          required
        />
      </div>
      <div className="study_create_content">
        <textarea 
          value={content} 
          onChange={(e)=> {
            e.target.value.length > 200 ? setContent(e.target.value.slice(0, 200)) :
            setContent(e.target.value)}} 
          className="study_create_area" 
          placeholder="스터디 규칙 및 공지 (최대 200자 공백포함)" 
          maxLength={200}
          required
        />
      </div>
      <div className="study_create_time">
        <input type="time" value={study_time} onChange={(e) => setStudytime(e.target.value)} className="study_time_input" />
      </div>

      <div className="create_study_complete" >
        <div className="cancel_btn" onClick={()=>history.goBack()}>취소하기</div>
        <div className="complete_btn" onClick={create}>완료하기</div>
      </div>
    <div className="click">touch</div>


    </div>
)} 
    
export default StrudyCreatePage
