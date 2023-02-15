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
                  props.onstatechange(props.item);
                }else{
                  props.changeitemToNormal(props.item.ingredient_name);
                  props.onstatechange(props.item);
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


  