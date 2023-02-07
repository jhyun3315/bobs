import './css/StudyMember.css'

function StudyMember(props) {

  const member = props.member

  return (
    <div className="study_member">
      <div className="member_img"><img src={ props.image } alt="member" className="memimg" /></div>
      <div className="member_name">{ member }</div>
    </div>
  )
}
export default StudyMember