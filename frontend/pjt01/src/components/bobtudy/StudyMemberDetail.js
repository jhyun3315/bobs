import './css/StudyMember.css'

function StudyMemberDetail(props) {
  
  console.log(props)
  return (
    <div className="study_member">
      <div className="member_img"><img src={props.member.user_profile} alt="member" className="memimg" /></div>
      <div className="member_name">{props.member.user_name}</div>
    </div>
  )
}
export default StudyMemberDetail