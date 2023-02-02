import "./css/StudyJoined.css"


function StudyJoined(props) {
  const time = props.study.time

  return (
    <div className="study_joined">
      <div>{ props.study.name }</div>
      <div>
        {
          time.map((time, index) => {
            return <span className="join_study_time" key={index}>#{time}시</span>
          })
        }
      </div>
      <button type="button" className="btn btn-primary">Live ON</button>
    </div>
      
    );
  }
  
  export default StudyJoined;
  