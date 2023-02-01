import "./nav.css" ;
import {useNavigate  } from "react-router-dom";

function NavBar() {
    const navigate = useNavigate();

    const toRefridgerator = (e) =>{
        navigate("/refridgerator");
    };

    const toStudy = (e) =>{
        navigate("/study");
    };

    const toRecipe = (e) =>{
        navigate("/recipe");
    };

    const toCommunity = (e) =>{
        navigate("/community");
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
  
  export default NavBar;