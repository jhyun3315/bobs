import "./nav.css" ;
import { useHistory } from "react-router-dom";
import ref_img from "../img/ref.png"
import bob_img from "../img/bobtudy.png"
import reci_img from "../img/recipe.png"
import com_img from "../img/community.png"

function NavBar() {
  const history = useHistory();

  const toRefridgerator = (e) =>{
    history.push("/refridgerator");
  };

  const toStudy = (e) =>{
    history.push("/study");
  };

  const toRecipe = (e) =>{
    history.push("/recipe");
  };

  const toCommunity = (e) =>{
    history.push("/community");
  };
  return (
    <div className="Nav">
      <div className="ref" onClick={toRefridgerator}><img src={ref_img} alt="ref" /></div>
      <div className="ref" onClick={toStudy}><img src={bob_img} alt="bob" /></div>
      <div className="ref" onClick={toRecipe}><img src={reci_img} alt="rec" /></div>
      <div className="ref" onClick={toCommunity}><img src={com_img} alt="com" /></div>  
    </div>
  );
}
  
  export default NavBar;