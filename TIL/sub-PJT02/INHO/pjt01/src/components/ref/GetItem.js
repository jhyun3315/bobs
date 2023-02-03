import "./css/item.css"
import nambi from "../../img/nambi.png";

function GetItem() {
    return (
      <div className='item'>
        <img src={nambi} alt="nambi"></img>
        <div className='itemText'>추천받기</div>
      </div>
    );
  }
  
  export default GetItem;
  