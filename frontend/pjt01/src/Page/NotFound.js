import {useHistory } from "react-router-dom";


function NotFound(params) {
  const history = useHistory();

  const tomain = (e) =>{
    history.push("/refridgerator");
  };

  return(
    <div>
    not found
      <button onClick={()=>{tomain()}}>메인으로</button>
    </div>
  );

}

export default NotFound;