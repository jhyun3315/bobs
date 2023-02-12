import { useRouteMatch } from "react-router-dom";
import { useState, useRef } from "react";
import StudyDetailChat from "../components/bobtudy/StudyDetailChat";
import StudyDetail from "../components/bobtudy/StudyDetail"
import Toggle from "../components/Toggle.component"
import data from '../components/bobtudy/Study.data'
import "./css/studyDetail.css"

function StudyDetailPage() {
  const [study] = useState(data);
  const [checked, setChecked] = useState(true);
  const [locked, setLocked] = useState(false)

  const onBtn = useRef(null);
  const offBtn = useRef(null);

  const match = useRouteMatch();
  const item = study.filter(i => i.id === Number(match.params.id))

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
  const [edit, setEdit] = useState(false);
  const [name, setName] = useState(item[0].name)

  return (
    <div className="study_detail">
      {
        edit === false ? 
        <div className="study_detail_name">{ name }</div>:
        <input className="study_detail_name_input" type="text" value={name} onChange={(e)=>setName(e.target.value)} maxLength={15}/>
      }
      <div className="study_detail_top">              
        <div className='study_detail_is_btn'>
          <button className='study_onrecom study_is_checked' ref={onBtn} onClick={onRecom} >스터디 정보</button>          
          <button className='study_offrecom' ref={offBtn} onClick={offRecom} >대화방</button>
        </div>
        <Toggle
          checked = {locked}
          onChange = {() => {
            setLocked(!locked)
          }}
          offstyle="off"
          onstyle="on"
          text="잠금"
        />
      </div>

      <div className="study_detail_main">
      {
        checked === true ? <StudyDetail study={item} edit={edit} setEdit={setEdit}/> : <StudyDetailChat /> 
      }
      </div>
    </div>
  );
}

export default StudyDetailPage;
