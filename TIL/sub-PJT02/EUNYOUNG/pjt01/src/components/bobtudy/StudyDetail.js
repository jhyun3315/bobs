<<<<<<< HEAD
function StudyDetail() {
    return (
        <div>

        </div>
      
    );
  }
=======
import { useRouteMatch, useHistory } from 'react-router-dom'
import { useState } from 'react'
import data from './Study.data'
import StudyMember from './StudyMember'
import user_img from '../../img/Users.png'
import getout_img from '../../img/getout.png'
import edit_img from '../../img/edit.png'
import './css/StudyDetail.css'

function StudyDetail() {

  const match = useRouteMatch();
  const history = useHistory();
  const study = data.filter(i => i.id === Number(match.params.id));
  let con = study[0].summary;
  let [content, setContent] = useState(con);
  const [checked, setChecked] = useState(false);
  const cnt_modal = study[0].member.length  

  return (
    <div className="detail_study">
      <div className='detail_study_top'>
        <div className='detail_study_time'>#{ study[0].time }시</div>
        {
          checked === false ?
          <div className='detail_study_edit'  onClick={()=>{setChecked(!checked)}}><div className='detail_study_rewrite'>수정하기</div><img src={edit_img} alt="" className='editimg'/></div> :
          <div className='detail_study_save' onClick={()=>{setContent(content); setChecked(!checked)}} >저장하기</div>
        }
      </div>
      {
        checked === false ?  
        <div className="detail_study_content">{content}</div> :
        <textarea type="text-area" value={content} onChange={(e)=>setContent(e.target.value)} className="detail_study_input" ></textarea>
      }
      <div className="detail_member_top">
        <div className='detail_study_member'><div className="detail_study_mem">참여자</div><img src={user_img} alt="user" className="detail_study_img"/>{ cnt_modal + 1 }/{ cnt_modal + 1 }</div>
        <div className='detail_study_getout'><div className='detail_getout_text'>추방</div><img src={getout_img} alt="" className="detail_getout_img" /></div>
      </div>
      <div className="detail_study_person">
        <StudyMember member={study[0].king} image={user_img} />
        {
          study[0].member.map((member, index) => {
            return <StudyMember member={member} image={user_img} key={index} />
          })
        }
      </div>

      <div className='detail_study_btn'>
        <div className='study_exit_btn' onClick={() => {history.push('/study')}}>탈퇴하기</div>
        <div className='meeting_join_btn' onClick={() => {history.push({pathname: "/videoroom/" + match.params.id, state: {room: match.params.id}})}}>미팅참여</div>
      </div>
    </div>
    
  );
}
>>>>>>> develop
  
  export default StudyDetail;
  