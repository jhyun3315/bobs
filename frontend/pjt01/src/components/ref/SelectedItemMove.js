import React from 'react';
import "./css/SelectedItem.css";


function SelectedItemMove(props) {
  return(
      <div>
        <div className='itemlistbox'>
            <div  key={props.index} className='select_item_text'  
              onClick={() => {
                if(!props.check){
                  props.changeitemToPriority(props.item.ingredient_name);
                }else{
                  props.changeitemToNormal(props.item.ingredient_name);
                }
                
            }}
            >
            <div className="seletc_itemText">{props.item.ingredient_name}</div>
          </div>
        </div>
      </div>

  );
}

export default SelectedItemMove;


  