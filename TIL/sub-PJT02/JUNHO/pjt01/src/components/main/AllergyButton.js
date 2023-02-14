import x_btn from "../../img/x_btn.png"


function AllergyButton(props) {

  return(
      <div>
         < div key={props.index} className='allergyitem'  >
            <p className="itemText">{props.item.itemid}</p>
            <img src={x_btn} className="x_btn" alt="x"  onClick={() => {
            props.deleteItem(props.item.itemid);
        }}/>
        </div>
      </div>


  );
}

export default AllergyButton;