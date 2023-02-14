import { useRouteMatch, useHistory } from 'react-router-dom'
import { useState } from 'react'
import data from './Study.data'
import StudyMember from './StudyMember'
import user_img from '../../img/Users.png'
import getout_img from '../../img/getout.png'
import edit_img from '../../img/edit.png'
import './css/StudyDetail.css'
import large_x from '../../img/large_x.png'

function StudyDetail() {

  const match = useRouteMatch();
  const history = useHistory();
  const study = data.filter(i => i.id === Number(match.params.id));
  let con = study[0].summary;
  let [content, setContent] = useState(con);
  const [checked, setChecked] = useState(false);
  const cnt_modal = study[0].member.length  
  const [getout, setGetout] = useState(false);

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
        <div className='detail_study_getout' onClick={() => setGetout(true)}>
          <div className='detail_getout_text'>추방</div>
          <img src={getout_img} alt="" className="detail_getout_img" />
        </div>
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
      { getout === true ? <Getout data={study[0].member} setGetout={setGetout} /> : null }
    </div>    
  );
}

function Getout(props) {
  const memberilst = props.data
  // 이것은 추방할 유저 리스트
  const [getoutlist, setGetOutList] = useState([])
  // 이것은 추방할 유저 리스트에 추가 및 삭제
  const addGetout = (params, e) => {
    console.log(e.target.className)
    if (getoutlist.includes(params)){
      const newGetoutlist = getoutlist.filter((member) => member !== params)
      setGetOutList(newGetoutlist)
    } else {
      const newGetoutlist = [...getoutlist]
      newGetoutlist.push(params)
      setGetOutList(newGetoutlist)
    }
  }
  // 추방확인 모달창
  const [getoutModal, setGetoutModal] = useState(false)
  // 추방하기 버튼 클릭 시
  const getout_click = (e) => {
    if (getoutlist.length > 0) {
      setGetoutModal(true)
    }
  }
  //클릭 시 밑줄 넣기
  const [select, setSelect] = useState()

  return (
    <div className='get_out'>
      <div className='title'>추방하기</div>
      <img src={large_x} alt="x" onClick={() => props.setGetout(false)} />
      <div className='members'>
        {
          memberilst.map((member) => 
            <div className={'member' + ' selected'} value={member} key={member} onClick={(e) => addGetout(member, e)}>
              <img src={user_img} alt="" />
              <div>{member}</div>
            </div>
          )
        }
      </div>
      { 
        getoutlist.length > 0 ? 
        <div className='get_out_btn_on' onClick={getout_click}>추방하기</div> : 
        <div className='get_out_btn_off'>추방하기</div>
      }
      
      { getoutModal === true ? <GetoutModal data={getoutlist} setGetoutModal={setGetoutModal} /> : null }
    </div>
  )
}

function GetoutModal(props) {
  const getoutlist = props.data
  return (
    <div className='getout_modal'>
      <div className='alert'>
        <div className='title'>잠시만요!</div>
        <div className='suggest'>정말로 추방하실건가요?</div>
        <div className='getout_list'>
          {
            getoutlist.map((member) => 
              <div className={'member'} key={member}>
                <img src={user_img} alt="" />
                <div>{member}</div>
              </div>
            )
          }
        </div>
        <div className="btn_box">
          <div className='yes_btn'>네</div>
          <div className='no_btn' onClick={() => props.setGetoutModal(false)}>아니요</div>
        </div>
      </div>
    </div>
  )
} 
export default StudyDetail;
  