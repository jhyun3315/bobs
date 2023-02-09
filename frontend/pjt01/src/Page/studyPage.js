import StudyInfo from "../components/bobtudy/StudyInfo";
import StudyJoined from "../components/bobtudy/StudyJoined";
import data from '../components/bobtudy/Study.data'
import search_icon from '../img/search_item.png'
import delete_icon from '../img/delete_btn.png'
import Toggle from '../components/Toggle.component'
import { useState } from "react";
import { useHistory } from "react-router-dom";
import create_img from '../img/create_study.png'
import "./css/studyPage.css"


function StudyPage() {
  const [studys] = useState(data);
  const [text, setText] = useState("");
  const join_data = studys.slice(0,3);
  const history = useHistory();
  const [nofullstudys] = useState([]);
  const [checked, setChecked] = useState(false)

  const ComponentA = () => {
    return (
          <div className="study_info_box">
            {
              studys.map((study, index) => {
                return <StudyInfo study={study} key={index} /> 
              })
            }                
          </div>
    );
  };

  const ComponentB = () => {
    return (
          <div className="study_info_box">
            {
              nofullstudys.map((study, index) => {
                return <StudyInfo study={study} key={index} /> 
              })
            }                
          </div>
    );
  };

    return (
      <div className="study_page">
        {/* 내가 가입한 3개의 스터디 방 */}
        <div className="study_joined_box">
          {
            join_data.map((study, index) => {
              return <StudyJoined study={study} key={index} />
            })
          }
        </div>
        {/* 그 아래 부분 */}
        <div className="study_main">
          {/* 검색 창 부분 */} 
          <div className='study_search_input'>
          <div className='study_img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
          <input type="text" value={text} id='study_search_input'
            onChange={(e) => {
              setText(e.target.value);
            }}
            placeholder="검색어를 입력하세요"/>
          <div className='study_img_icon'><img src={delete_icon} alt="delete" className="delete_item" /></div>
        </div>
        {/* 풀방 보기 토글 */}        
        <div className='study_toggle'>
          <Toggle
              checked = {checked}
              onChange = {(e) => {
                setChecked(e.target.checked)
              }}
              offstyle="off"
              onstyle="on"
              text="풀방보기"
            />
          </div>
          {/* 스터디 리스트 */}
          {checked ? <ComponentA /> : <ComponentB />}

        <div className="create_study_btn" onClick={()=>history.push('/studycreate')}><img src={create_img} alt="" className="create_study_img" /></div>
        </div>


      </div>
    );
  }
  
  export default StudyPage;