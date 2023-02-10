import { useState } from "react";
import x_btn from "../../img/x_btn.png"
import './AllergyButton.css'


function AllergyButton(props) {

  return(
    <div className="allergy_btn">
      < div key={props.index} className='allergyitem' >{props.item.name}
        <img src={x_btn} className="x_btn" alt="x"  onClick={() => {
        props.deleteItem(props.item.id);}}/>
      </div>
      </div>
  );
}

export default AllergyButton;