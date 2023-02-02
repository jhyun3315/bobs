import "./css/item.css"
import minus from "../../img/-button.png";


function EditItem() {
    return (
      <div className='item'>
        <img src={minus} alt="minus"></img>
        <div className='itemText'>삭제하기</div>
      </div>
    );
  }
  
  export default EditItem;
  