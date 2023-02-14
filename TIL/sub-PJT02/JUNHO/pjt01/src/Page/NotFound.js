import { useHistory } from "react-router-dom";
import not_found from '../img/not_found.png'
import './css/NotFound.css'

function NotFound(params) {
  const history = useHistory();

  const tomain = (e) =>{
    history.push("/refridgerator");
  };

  return(
    <div className="not_found">
      <img src={not_found} alt="" />
      <div className="contents">
        <div className="code">404</div>
        <div className="message">페이지를 찾을 수 없습니다.</div>
        <div className="to_main" onClick={()=>{tomain()}}>메인으로</div>
      </div>
    
    </div>
  );

}

export default NotFound;