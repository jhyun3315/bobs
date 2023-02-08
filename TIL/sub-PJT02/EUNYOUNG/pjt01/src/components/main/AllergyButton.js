import { useState } from "react";
import x_btn from "../../img/x_btn.png"


function AllergyButton(props) {
  const [colorstate, setcolorstate] = useState("");
  const styleclick = {
    background : "white"
  }
  const styleclick2 = {
    background : "grey"
  }
  return(
      <div>
        {colorstate ? (
        < div style={styleclick} key={props.index} className='allergyitem'  
          onClick={() => {
            setcolorstate(null);
        }}
        >
            <p className="itemText">{props.item.itemid}</p>
            <img src={x_btn} className="x_btn" alt="x"/>
        </div>
        ) : (
        <div style={styleclick2} key={props.index} className='allergyitem'
          onClick={() => {
            setcolorstate(1);
        }}>
          <p className="itemText">{props.item.itemid}</p>
          <img src={x_btn} className="x_btn" alt="x"/>
      </div >  )}
      </div>


  );
}

export default AllergyButton;