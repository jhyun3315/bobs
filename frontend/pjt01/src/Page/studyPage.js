import StudyInfo from "../components/bobtudy/StudyInfo";
import StudyJoined from "../components/bobtudy/StudyJoined";
import data from '../components/bobtudy/Study.data'
import Toggle from '../components/Toggle.component'
import { useEffect, useState, useCallback } from "react";
import { useHistory } from "react-router-dom";
import create_img from '../img/create_study.png'
import "./css/studyPage.css"
import axios from "axios";
import { useInView } from "react-intersection-observer"
import delete_icon from '../img/x.png'
import search_icon from '../img/search_item.png'

function StudyPage() {
  const [studies, setstudies] = useState([]);
  const join_data = data.slice(0,3);
  const history = useHistory();
  const [nofullstudies] = useState([]);
  const [checked, setChecked] = useState(false)
  const [checklivestate,setchecklivestate] = useState(false)

// 무한 스크롤
  const [ref, inView] = useInView()
  const [page, setPage] = useState(1)
  const [loading, setLoading] = useState(false)
  const [lastPage, setLagePage] = useState(true)
  const getItems = useCallback(async () => {
    setLoading(true)
    if (lastPage) {
      await axios.get(`https://i8b304.p.ssafy.io/api/studies?page=${page}`)
      .then((res) => {
        if(res.data.total_page === res.data.current_page) {
          setLagePage(false)
        } else {
          setLagePage(true)
        }
        setstudies([...studies, ...res.data.data])
      })
      .catch(() => {
        console.log('데이터 가져오기 실패')
        setLagePage(false)
      })
    setLoading(false)
    }
  }, [page])
  // `getItems` 가 바뀔 때 마다 함수 실행
  useEffect(() => {
    getItems()
  }, [getItems])
  useEffect(() => {
    // 사용자가 마지막 요소를 보고 있고, 로딩 중이 아니라면
    if (inView && !loading ) {
      setPage(prevState => prevState + 1)
    }
  }, [inView, loading])

// 검색 기능
  const [search, setSearch] = useState("")
  const [searchData, setSearchData] = useState([])
  const [modal, setModal] = useState(false)
  const onSearch = (e) => {
    e.preventDefault()
    if (search.trim() !== '') {
      axios.get(`https://i8b304.p.ssafy.io/api/studies/${search}`)
      .then((res) => {
        setSearchData(res.data.data)
        setModal(true)
      })
      .catch((err) => alert('없는 방 입니다'))
    } 
  }
  return (
    <div className="my_study_page">
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
        <div className='search_input'>
          <div className='img_icon'><img src={search_icon} alt="search" className="search_item" /></div>
          <form onSubmit={onSearch}>
            <input type="text" value={search} id='search_input'
              onChange={(e) => {
                setSearch(e.target.value)
                setModal(false)
              }}
              placeholder="방 번호를 입력해주세요" />
          </form>
          <div className='img_icon'><img src={delete_icon} alt="delete" className="delete_item" onClick={() => setSearch("")} /></div>
        </div>
        {/* 풀방 보기 토글 */}
        <div className='study_toggle'>
          <Toggle
            checked={checked}
            onChange={(e) => {
              setChecked(e.target.checked)
            }}
            offstyle="off"
            onstyle="on"
            text="풀방포함"
          />
        </div>
        {/* 스터디 리스트 */}
        {checked ? 
          <div className="study_page">
            {
              studies?.map((study, idx) => {
                return <StudyInfo study={study} key={idx} modal={false}/>
              })
            }
            <div className="scroll_target"></div>
          </div> :
          <div className="study_page">
            {
              studies?.map((study, idx) => {
                return <StudyInfo study={study} key={idx} modal={false}/>
              })
            }
            <div className="scroll_target" ref={ref}></div>
          </div>
        }
        <div className="create_study_btn" onClick={() => history.push('/studycreate')}><img src={create_img} alt="" className="create_study_img" /></div>
        { modal ?
          <StudyInfo study={searchData} key={search} modal={true} />:
          null
        }
      </div>

    </div>
  );
}


export default StudyPage;