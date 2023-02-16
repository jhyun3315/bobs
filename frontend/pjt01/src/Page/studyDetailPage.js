import { useHistory, useRouteMatch } from "react-router-dom";
import { useState, useRef } from "react";
import Comment from '../components/bobtudy/StudyComment'
import CommentForm from '../components/bobtudy/StudyCommentForm'
import CommentList from '../components/bobtudy/StudyCommentList'
import StudyDetail from "../components/bobtudy/StudyDetail"
import Toggle from "../components/Toggle.component"
import "./css/studyDetail.css"
import axios from "axios";
import { useEffect } from "react";

function StudyDetailPage() {
  const [study, setStudy] = useState([]);
  const [checked, setChecked] = useState(true);
  const [locked, setLocked] = useState(false);
  const [cmt, setCmt] = useState([]);
  const [mastercheck, setmastercheck] = useState(false);
  const [edit, setEdit] = useState(false);
  const [name, setName] = useState(null);
  const local_id= localStorage.getItem("id");
  // const local_id = "5";
  const onBtn = useRef(null);
  const offBtn = useRef(null);
  const history = useHistory()
  const match = useRouteMatch();
  const id = match.params.id

  useEffect(() => {

    const url_mem = "https://i8b304.p.ssafy.io/api/studymembers/info"
    const url_com = "https://i8b304.p.ssafy.io/api/study/comment/?value="
    // const url_mem = "http://localhost:8080/studymembers/info"
    // const url_comment = "http://localhost:8080/study/comment/?value="
    let data = {
      "user_id" : local_id,
      "study_id" : id
    }

    axios
    .all([axios.post(url_mem, data),
      axios.post(url_com, data)])
      .then(
        axios.spread((res1, res2) => {
          setStudy(res1.data.data)
          setName(res1.data.data.study_title)
          setmastercheck(res1.data.data.check_writer)
          setCmt(res2.data.data)
        })
      ).catch((e) => console.log(e))

  }, [])

  const onRecom = () => {
    onBtn.current.className += " study_is_checked"
    offBtn.current.className = "study_offrecom"
    setChecked(true)
  }
  const offRecom = () => {
    offBtn.current.className += " study_is_checked"
    onBtn.current.className = "study_onrecom"
    setChecked(false)
  }

  function golock(locked){
    if(locked){
      const url = "https://i8b304.p.ssafy.io/api/studies/lock"
      axios.put(url,
        {
          "study_id" : match.params.id,
          "user_id" : local_id,
        }
      ).then((res)=>{
        console.log(res)
      })
    }else{

    }
    setLocked(!locked)
  }


  const addList = (content) => {

    let data =  {
      "user_id" : local_id,
      "study_id" : Number(id),
      "study_comment_content" : content
    }
    const config = {"Content-Type": 'application/json'};
    
    const url = "https://i8b304.p.ssafy.io/api/study/comment"
    // const url = "http://localhost:8080/study/comment"
    axios.post(url,data, config)
    .then((res) => { if(cmt !== []) setCmt([...cmt, res.data.data]); else setCmt(res.data.data)})
    .catch((err) => console.log(err))
  }

  const updateList = list => {   
    setCmt(list)
  }


  return (
    <div className="study_detail">
      {
        edit === false ? 
        <div className="study_detail_name">{ study.study_title }</div>:
        <input className="study_detail_name_input" type="text" value={name} onChange={(e)=>setName(e.target.value)} maxLength={15}/>
      }
      <div className="study_detail_top">              
        <div className='study_detail_is_btn'>
          <button className='study_onrecom study_is_checked' ref={onBtn} onClick={onRecom} >스터디 정보</button>          
          <button className='study_offrecom' ref={offBtn} onClick={offRecom} >대화방</button>
        </div>
        { study.check_writer ? 
        <Toggle
          checked = {locked}
          onChange = {() => {
            golock(!locked)
          }}
          offstyle="off"
          onstyle="on"
          text="잠금"
        />        
        :
          null
        }
        
      </div>

      <div className="study_detail_main">
      {
        checked === true ? <StudyDetail study={study} edit={edit} name={name} setEdit={setEdit} id={id}/> :
        cmt !== [] ? 
          <Comment>
            <CommentList list={cmt} updateList ={updateList} />
            <CommentForm addList = {addList}
            />
          </Comment>
        : null
      }
      </div>
    </div>
  );
}

export default StudyDetailPage;
