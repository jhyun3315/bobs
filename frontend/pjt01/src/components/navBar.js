import "./nav.css" ;
import { useHistory } from "react-router-dom";
import ref_img from "../img/ref.png"
import bob_img from "../img/bobtudy.png"
import reci_img from "../img/recipe.png"
import com_img from "../img/community.png"
import { useLocation } from "react-router-dom";

function NavBar() {
  const history = useHistory();
  const { pathname } = useLocation();
  if (pathname.substring(0,10) === "/videoroom") return null;
 
  return (
    <div className="Nav">
      <input className="nav_input" type="radio" id="one" name="buttons" />
        <label htmlFor="one" className="icons ref" onClick={() => history.push("/refridgerator")}><img src={ref_img} id="ref_img" alt="ref"/></label>
      <input className="nav_input" type="radio" id="two" name="buttons" />
        <label htmlFor="two" className="icons bob"  onClick={() => history.push("/study")}><img src={bob_img} id="bob_img" alt="bob"/></label>
      <input className="nav_input" type="radio" id="three" name="buttons" />
        <label htmlFor="three" className="icons rec" onClick={() => history.push("/recipe")}><img src={reci_img} id="rec_img" alt="rec"/></label>
      <input className="nav_input" type="radio" id="four" name="buttons" />
        <label htmlFor="four" className="icons com" onClick={() => history.push("/community")}><img src={com_img} id="com_img" alt="com"/></label>
      <div id="box"> </div>  
      <div id="body"></div>
    </div>
  );
}
  
  export default NavBar;





  // <input className="nav_input" type="radio" id="one" name="buttons" />
  //       <label htmlFor="one" className="icons ref" onClick={() => history.push("/refridgerator")}><div>냉장고</div></label>
  //     <input className="nav_input" type="radio" id="two" name="buttons" />
  //       <label htmlFor="two" className="icons bob"  onClick={() => history.push("/study")}><div>밥터디</div></label>
  //     <input className="nav_input" type="radio" id="three" name="buttons" />
  //       <label htmlFor="three" className="icons rec" onClick={() => history.push("/recipe")}><div>레시피</div></label>
  //     <input className="nav_input" type="radio" id="four" name="buttons" />
  //       <label htmlFor="four" className="icons com" onClick={() => history.push("/community")}><div>게시판</div></label>