import { useParams } from "react-router-dom";
import StudyDetailChat from "./components/bobtudy/StudyDetailChat";
import StudyDetailInfo from "./components/bobtudy/StudyDetailInfo";
import "./css/studyDetail.css"

function StudyDetail() {
    const {id} = useParams();
  return (
    <div>
        
        <div className="detailName">안뇽 여기는 {id}방</div>
            <StudyDetailChat>
                
            </StudyDetailChat>
            <StudyDetailInfo>

            </StudyDetailInfo>

    </div>
  );
}

export default StudyDetail;
