import "./css/StudyJoined.css"
import {useHistory  } from "react-router-dom";


function StudyJoined() {
    const history = useHistory();

    function govideoroom(){
      history.push("/videoroom")
    }
    return (
      <div className="study_joined">
        <div>땡떙 스터디    n명</div>
        <div>n시 n분 밥터디</div>
        <button type="button" className="btn btn-primary" onClick={govideoroom}>Live ON</button>
      </div>
      
    );
  }
  
  export default StudyJoined;
  