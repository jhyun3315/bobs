import "./css/StudyJoined.css"
import { useHistory } from "react-router-dom";
import image from '../../img/Users.png'

function StudyJoined(props) {

  const history = useHistory();
  const cnt_mem = props.study.member.length

  return (
    
    <div className="study_joined">
      <div className="joined_top" onClick={() => {history.push({pathname: "/study/" + props.study.id, state: {id: props.study.id}})}}>
      <div className="joined_name">{ props.study.name }</div>
      <div className="joined_time"># { props.study.time }시</div>
      <div className="joined_member"><img src={image} alt="user" className="joined_image"/>{ cnt_mem + 1 }/{ cnt_mem + 1 }</div>
      </div>
      {props.checklivestate ?
        <div className="no_rtc">Live off</div>
        :
        <div className="go_rtc" onClick={() => {history.push({pathname: "/videoroom/" + props.study.id, state: {room: props.study.id}})}}>Live ON</div>
      }
    
    </div>      
    );
  }
  
  export default StudyJoined;
  