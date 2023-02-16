import { useRouteMatch, useHistory } from 'react-router-dom'
import { useState } from 'react'
import user_img from '../../img/Users.png'
import getout_img from '../../img/getout.png'
import edit_img from '../../img/edit.png'
import './css/StudyDetail.css'
import x from '../../img/x.png'
import axios from 'axios'
import { useEffect } from 'react'
import ConfirmModal from '../ConfirmModal'
import StudyMemberDetail from './StudyMemberDetail'

function StudyDetail(props) {
  const match = useRouteMatch();
  const history = useHistory();
  const [content, setContent] = useState();
  const [time, setTime] = useState();
  const local_id= localStorage.getItem("id");
  // const local_id = "5";
  const [master, setMaster] = useState(local_id)
  const [getout, setGetout] = useState(false);
  const edit = props.edit
  const [member,setmember] =useState([])
  const [mastercheck, setmastercheck]=useState(false);
  const [study, setStudy] = useState([]);
  useEffect(() => {
    const url = "https://i8b304.p.ssafy.io/api/studymembers/info"
    // const url = "http://localhost:8080/studymembers/info"
   
    let data = {
      "user_id" : local_id,
      "study_id" : match.params.id
    }
    const config = {"Content-Type" : "application/json"}
    axios.post(url, data)
    .then(function(res) {
      setStudy(res.data.data)
      setContent(res.data.data.study_content)
      setmember(res.data.data.member_list)
      // setTime(res.data.data.study_time)
      if(res.data.data.check_write){
        setmastercheck(true);      
      }
    })
    .catch(function(error) {
      // history.push("/study")
    })
 
  },[])
// 스터디 삭제
  const [confirmModal, setconfirmModal] = useState(false)
  const id = match.params.id
  const studyDelete = () => {
    const url = 'https://i8b304.p.ssafy.io/api/studies'
    axios.delete(url, {
      params: {
        "value": id,
      }
    })
    .then((res) => {
      console.log(res.data)
      history.push('/study')
    }).catch((e) => console.log(e))
  }
    

  return (
    <div className="detail_study">
      <div className='detail_study_top'>
        { 
          edit === false ?
          <div className='detail_study_time'>#{ time }시</div>:
          <input className='detail_study_time_edit' type="text" value={time} onChange={(e)=>setTime(e.target.value)} />
        }
        {/* 방장일때 조건 추가 */}
        { mastercheck ?  
        <div>
          {
            edit === false ?
            <div className='detail_study_edit'  onClick={()=>{props.setEdit(!edit)}}><div className='detail_study_rewrite'>수정하기</div><img src={edit_img} alt="" className='editimg'/></div> :
            <div className='detail_study_save' onClick={()=>{setContent(content); props.setEdit(!edit)}} >저장하기</div>
          }
        </div>
        : null }
      </div>
      { mastercheck ?  
      <div>
        {
          edit === false ?  
          <div className="detail_study_content">{study.study_content}</div> :
          <textarea type="text-area" value={content} onChange={(e)=>setContent(e.target.value)} className="detail_study_input"  maxLength={200}></textarea>
        }
      </div>
        : 
        <div className="detail_study_content">{content}</div>
        }
      <div className="detail_member_top">
        <div className='detail_study_member'><div className="detail_study_mem">참여자</div><img src={user_img} alt="user" className="detail_study_img"/>{member?.length}/4</div>
        { mastercheck ?  
        <div className='detail_study_getout' onClick={() => setGetout(true)}>
          <div className='detail_getout_text'>추방</div>
          <img src={getout_img} alt="" className="detail_getout_img" />
        </div>
         : null }
      </div>
      <div className="detail_study_person">
        {
          member?.map((member, index) => {
            return <StudyMemberDetail member={member} key={index} />
          })
        }
      </div>

      <div className='detail_study_btn'>
        {/* 탈퇴하기, 방 폭파 로직 구현해야 함 */}
        { mastercheck ? 
          <>
            <div className='study_exit_btn'  onClick={() => {setconfirmModal(true)}}>방 폭파</div>
            <div className='meeting_join_btn' onClick={() => {history.push({pathname: "/videoroom/" + match.params.id, state: {room: match.params.id}})}}>미팅시작</div>
          </>:
          <>
            <div className='study_exit_btn' onClick={() => {history.push('/study')}}>탈퇴하기</div>
            <div className='meeting_join_btn' onClick={() => {history.push({pathname: "/videoroom/" + match.params.id, state: {room: match.params.id}})}}>미팅참여</div>
          </>
        }
      </div>
      { confirmModal ? 
        <ConfirmModal 
          setconfirmModal={setconfirmModal} 
          studyDelete={studyDelete}
          title = {"잠시만요!"} 
          content = {"정말로 \n 스터디를 삭제하시겠어요? \n 관련된 정보는 \n 복구할 수 없어요!"}/> : 
        null
      }
      { getout === true ? <Getout data={study[0].member} setGetout={setGetout} /> : null }
    </div>    
  );
}

function Getout(props) {
  const memberlist = props.data
  // 이것은 추방할 유저 리스트
  const [getoutlist, setGetOutList] = useState([])
  // 이것은 추방할 유저 리스트에 추가 및 삭제
  const addGetout = (params) => {
    if (getoutlist.includes(params)){
      const newGetoutlist = getoutlist?.filter((member) => member !== params)
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

  return (
    <div className='get_out'>
      <div className='title'>추방하기</div>
      <div className='selected_getout_list'>
        {
          getoutlist?.map((member) => 
            <div className='selected_member' key={member} onClick={(e) => addGetout(member)}>
              <img className='profile' src={user_img} alt="" />
              <div className='x_btn'>
                <img src={x} alt="x" />
              </div>
              <div>{member}</div>
            </div>
          )
        }
      </div>
      <img src={x} alt="x" onClick={() => props.setGetout(false)} />
      <div className='members'>
        {
          memberlist?.map((member) => 
            <div className='member' key={member} onClick={() => addGetout(member)}>
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
            getoutlist?.map((member) => 
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