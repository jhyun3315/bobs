import "./css/StudyJoined.css"
import { useHistory } from "react-router-dom";
import image from '../../img/Users.png'
import { useEffect } from "react";
import axios from "axios";
import { useState } from "react";

function StudyJoined(props) {
  const study = props.study
  const history = useHistory();
  const max_member_count = 4
  const [on,seton] =useState(false)
  const url_mem = "https://i8b304.p.ssafy.io/api/studymembers/info"
  useEffect(() => {
    let data = {
      "user_id" : localStorage.getItem("id"),
      "study_id" : props.study.study_id
    }
    axios.post(url_mem, data)
    .then((res)=>
      seton(res.data.data.study_onair)
    )
    // .catch((e) => console.log(e))
  }, [])
  
  // console.log(study)
  return (
    <div className="study_joined">
      <div className="joined_top" onClick={() => {history.push({pathname: "/study/" + study.study_id, state: {id: study.study_id}})}}>
      {/* <div className="joined_top" onClick={() => {history.push({pathname: "/study/" + 1, state: {id: 1}})}}> */}
      <div className="joined_name">{ study.study_title }</div>
      <div className="joined_time"># { study.study_time }시</div>
      <div className="joined_member"><img src={image} alt="user" className="joined_image"/>{ study.member_count}/{max_member_count}</div>
      </div>
      { 
        on ?
        <div className="go_rtc" onClick={() => {history.push({pathname: "/videoroom/" + props.study.id, state: {room: props.study.id}})}}>Live ON</div>
          :
        <div className="no_rtc">Live off</div>
      }
    </div>      
    );
  }
  
  export default StudyJoined;
  