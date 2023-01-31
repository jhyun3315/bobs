import StudyInfo from "./components/bobtudy/StudyInfo";
import StudyJoined from "./components/bobtudy/StudyJoined";
import "./css/studyPage.css"


function studyPage() {
    return (
      <div className="study_page">
        <div className="study_joined_box">
          <StudyJoined>

          </StudyJoined>
          <StudyJoined>

          </StudyJoined>
          <StudyJoined>

          </StudyJoined>
        </div>
        

        <div className="study_info_box">
            <StudyInfo>
            
            </StudyInfo>
            <StudyInfo>
            
            </StudyInfo>
            <StudyInfo>
            
            </StudyInfo>
        </div>
        
      </div>
    );
  }
  
  export default studyPage;