import StudyInfo from "../components/bobtudy/StudyInfo";
import StudyJoined from "../components/bobtudy/StudyJoined";
import data from '../components/bobtudy/Study.data'
import Toggle from '../components/Toggle.component'
import { useState } from "react";
import { useHistory } from "react-router-dom";
import create_img from '../img/create_study.png'
import SearchBar from '../components/SearchBar.js'
import "./css/studyPage.css"


function StudyPage() {
  const [studys, setStudys] = useState(data);
  const join_data = data.slice(0,3);
  const history = useHistory();
  const [nofullstudys, setnofullStudy] = useState(data);
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
          <SearchBar className='study_search_input'
            placeholder={"스터디 이름을 검색하세요."}
            data = { data }
            setData = { setStudys }/>
        
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
        </div>
          {/* 스터디 리스트 */}
          {checked ? <ComponentA /> : <ComponentB />}
          {/* <div className="study_info_box">
            {
              studys.map((study, index) => {
                return <StudyInfo study={study} key={index} /> 
              })
            }                
          </div> */}
        <div className="create_study_btn" onClick={()=>history.push('/studycreate')}><img src={create_img} alt="" className="create_study_img" /></div>
      </div>
    );
  }
  
  export default StudyPage;