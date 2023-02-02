import "./css/StudyInfo.css"
import { Link } from 'react-router-dom'


function StudyInfo(props) {

  return (
    <div className="study_info">
    
    <div className="study_name">{ props.study.name }</div>
    <div className="study_short">{ props.study.summary.slice(0,25) }...</div> 
    <div className="study_time">
      {
        props.study.time.map((time, index) => {
          return <span className="study_time_item" key={index}>#{time}시</span>
        })
      }
    </div>  

    <div className="accordion" id="accordionExample">
    <div className="accordion-item">
      <h2 className="accordion-header" id="headingOne">
      
      </h2>
      
      {/* <div id="collapse1" className="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
        <div className="accordion-body">
          <div className="study_member">{ props.study.king }</div>
          <div className="study_detail">{ props.study.summary }</div>
          <Link to={'/study/'+ props.num} state={{id: props.num}} >
          <button type="button" className="btn btn-primary">가입하기</button></Link>;
        </div>
      </div>
      <button className="accordion-button" type="button" data-hover="dd" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1" onClick={changeBtnName}>
        자세히 보기
      </button>
      </div> */}
      </div>
      </div>
    </div>
  );
}
  
  export default StudyInfo;
  