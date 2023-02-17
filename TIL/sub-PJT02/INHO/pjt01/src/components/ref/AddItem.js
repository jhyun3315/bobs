import "./css/item.css"
import plus from "../../img/xbutton.png";

function AddItem() {
    return (
      <div className='item'>
        <img src={plus} alt="plus" style={{height:"20px"}}></img>
        <div className='itemText'>추가하기</div>
      </div>
    );
  }
  
  export default AddItem;
  