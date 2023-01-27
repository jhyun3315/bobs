import "C:/Users/SSAFY/Desktop/key/S08P12B304/TIL/sub-PJT02/EUNYOUNG/pjt01/src/nav.css";

function NavBar() {

    return (
      <div className="Nav">
            <input type="radio" id="one" name="buttons" defaultChecked/>
                <label htmlFor="one" className="icons home"><span className="glyphicon glyphicon-home"></span></label>
            <input type="radio" id="two" name="buttons"/>
                <label htmlFor="two" className="icons search"><span className="glyphicon glyphicon-search"></span></label>
            <input type="radio" id="three" name="buttons"/>
                <label htmlFor="three" className="icons heart"><span className="glyphicon glyphicon-heart"></span></label>
            <input type="radio" id="four" name="buttons"/>
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