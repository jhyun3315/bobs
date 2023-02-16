import './css/StudyMember.css'

function StudyMember(props) {
  
  return (
    <div className="study_member">
      <div className="member_img"><img src={ props.image } alt="member" className="memimg" /></div>
      <div className="member_name">{props.member}</div>
    </div>
  )
}
export default StudyMember