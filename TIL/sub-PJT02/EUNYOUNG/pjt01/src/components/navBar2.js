import "./navBar2.css" ;
import { useHistory } from "react-router-dom";
import ref_img from "../img/ref.png"
import bob_img from "../img/bobtudy.png"
import reci_img from "../img/recipe.png"
import com_img from "../img/community.png"

function NavBar() {
  const history = useHistory();
 
  return (
    <div className="nav">
      <input className="nav_input" type="radio" id="one" name="buttons" defaultchecked />
        <label for="one" className="icons ref" onClick={() => history.push("/refridgerator")}><img src={ref_img} id="ref_img" alt="ref"/></label>
      <input className="nav_input" type="radio" id="two" name="buttons" />
        <label for="two" className="icons bob"  onClick={() => history.push("/study")}><img src={bob_img} id="bob_img" alt="bob"/></label>
      <input className="nav_input" type="radio" id="three" name="buttons" />
        <label for="three" className="icons rec" onClick={() => history.push("/recipe")}><img src={reci_img} id="rec_img" alt="rec"/></label>
      <input className="nav_input" type="radio" id="four" name="buttons" />
        <label for="four" className="icons com" onClick={() => history.push("/community")}><img src={com_img} id="com_img" alt="com"/></label>
      <div id="box"> </div>  
      <div id="body"></div>
      <div class="border"></div>
      <div class="effect"></div>    
    </div>
  );
}
  
  export default NavBar;