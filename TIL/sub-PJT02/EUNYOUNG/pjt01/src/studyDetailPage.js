<<<<<<< HEAD
import { useRouteMatch, useHistory } from "react-router-dom";
import { useState, useRef } from "react";
import StudyDetailChat from "./components/bobtudy/StudyDetailChat";
import data from './components/bobtudy/Study.data'
import "./css/studyDetail.css"

function StudyDetail() {
  const [study] = useState(data);
  const [checked, setChecked] = useState(false);
  const history = useHistory();
=======
import { useRouteMatch } from "react-router-dom";
import { useState, useRef } from "react";
import StudyDetailChat from "./components/bobtudy/StudyDetailChat";
import StudyDetail from "./components/bobtudy/StudyDetail"
import Toggle from "./components/Toggle.component"
import data from './components/bobtudy/Study.data'
import "./css/studyDetail.css"

function StudyDetailPage() {
  const [study] = useState(data);
  const [checked, setChecked] = useState(true);
  const [locked, setLocked] = useState(false)
>>>>>>> develop

  const onBtn = useRef(null);
  const offBtn = useRef(null);

  const match = useRouteMatch();
  const item = study.filter(i => i.id === Number(match.params.id))

  const onRecom = () => {
    onBtn.current.className += " study_is_checked"
    offBtn.current.className = "study_offrecom"
<<<<<<< HEAD
    setChecked(false)
=======
    setChecked(true)
>>>>>>> develop
  }
  const offRecom = () => {
    offBtn.current.className += " study_is_checked"
    onBtn.current.className = "study_onrecom"
<<<<<<< HEAD
    setChecked(true)
  }
=======
    setChecked(false)
  }

>>>>>>> develop
  return (
    <div className="study_detail">
      <div className="study_detail_top">
        <div className="study_detail_name">{ item[0].name }</div>
<<<<<<< HEAD
        <div className="study_detail_gortc" onClick={() => {history.push({pathname: "/study/web/" + match.params.id, state: {room: match.params.id}})}}>Live ON</div>
=======
        <Toggle
          checked = {locked}
          onChange = {() => {
            setLocked(!locked)
          }}
          offstyle="off"
          onstyle="on"
          text="잠금"
        />
>>>>>>> develop
      </div>
              
      <div className='study_detail_is_btn'>
        <button className='study_onrecom study_is_checked' ref={onBtn} onClick={onRecom} >스터디 정보</button>          
        <button className='study_offrecom' ref={offBtn} onClick={offRecom} >대화방</button>
      </div>

      <div className="study_detail_main">
<<<<<<< HEAD

=======
      {
        checked === true ? <StudyDetail study={item} /> : <StudyDetailChat /> 
      }
>>>>>>> develop
      </div>
    </div>
  );
}

<<<<<<< HEAD
export default StudyDetail;
=======
export default StudyDetailPage;
>>>>>>> develop
