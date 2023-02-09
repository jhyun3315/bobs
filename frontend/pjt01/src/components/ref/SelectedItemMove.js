import React from 'react';
import "./css/SelectedItem.css";


function SelectedItemMove(props) {
  return(
      <div>
        <div className='itemlistbox'>
            <div  key={props.index} className='item_text'  
              onClick={() => {
                if(!props.check){
                  props.changeitemToPriority(props.item.itemid);
                }else{
                  props.changeitemToNormal(props.item.itemid);
                }
                
            }}
            >
            <p className="itemText">{props.item.itemid}</p>
          </div>
        </div>
      </div>

  );
}

export default SelectedItemMove;


  