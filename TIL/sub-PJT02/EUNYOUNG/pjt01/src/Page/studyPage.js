import StudyInfo from "../components/bobtudy/StudyInfo";
import StudyJoined from "../components/bobtudy/StudyJoined";
import data from '../components/bobtudy/Study.data'
import Toggle from '../components/Toggle.component'
import SearchBar from '../components/SearchBar'
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import create_img from '../img/create_study.png'
import "./css/studyPage.css"
import axios from "axios";

function StudyPage() {
  const [studys, setstudys] = useState(data);
  const join_data = data.slice(0,3);
  const history = useHistory();
  const [nofullstudys] = useState([]);
  const [checked, setChecked] = useState(false)
  const [checklivestate,setchecklivestate] = useState(false)
  useEffect(() => {
    const url="https://i8b304.p.ssafy.io/api/studies";
    axios.get(url,{
      params : {
        user_id: ""
      }
    })
      .then(function(response) {
        setstudys(response.data);
        console.log("성공");
    })
      .catch(function(error) {
          console.log("실패");
    })

  }, [])
  

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
              return <StudyJoined study={study} key={index} checklivestate={checklivestate} />
            })
          }
        </div>
        {/* 그 아래 부분 */}
        <div className="study_main">
        <SearchBar type="text" id='allergy_search_input'
            placeholder={"방이름을 검색하세요."}
            data = {data}
            setData = {setstudys} />
         
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