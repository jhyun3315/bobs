import "./nav.css" ;
import { useHistory } from "react-router-dom";
<<<<<<< HEAD

function NavBar(e) {
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
            <input type="radio" id="one" name="buttons" onClick={toRefridgerator} className="nav_input" defaultChecked />
                <label htmlFor="one" className="icons home"><span className="glyphicon glyphicon-home"></span></label>
            <input type="radio" id="two" name="buttons" onClick={toStudy} className="nav_input"/>
                <label htmlFor="two" className="icons search"><span className="glyphicon glyphicon-search"></span></label>
            <input type="radio" id="three" name="buttons" onClick={toRecipe} className="nav_input"/>
                <label htmlFor="three" className="icons heart"><span className="glyphicon glyphicon-heart"></span></label>
            <input type="radio" id="four" name="buttons" onClick={toCommunity} className="nav_input"/>
                <label htmlFor="four" className="icons bell"><span className="glyphicon glyphicon-bell"></span></label>
            <div id="box">
            </div>
            <div id="body"></div>

            <span className="title home"></span>
            <span className="title search"></span>
            <span className="title heart"></span>
            <span className="title bell"></span>

            <div className="border"></div>
            <div className="effect"></div>
      </div>
    );
  }
=======
import ref_img from "../img/ref.png"
import bob_img from "../img/bobtudy.png"
import reci_img from "../img/receipe.png"
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
>>>>>>> develop
  
  export default NavBar;