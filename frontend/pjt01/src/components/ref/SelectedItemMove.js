import React from 'react';
import "./css/SelectedItem.css";

function SelectedItemMove(props) {

  const topriority = (item) => {
    const tof = props.s_item?.filter(i => i.ingredient_name !== item.ingredient_name)
    props.setf_item([item, ...props.f_item])
    props.sets_item(tof)
  }

  const tonormal = (item) => {
    const tos = props.f_item?.filter(i => i.ingredient_name !== item.ingredient_name)
    props.sets_item([item, ...props.s_item])
    props.setf_item(tos)
  }
  
  return(
      <div>
        <div className='itemlistbox'>
            <div  key={props.index} className='select_item_text'  
              onClick={() => {
                if(!props.check){
                  props.changeitemToPriority(props.item);
                  // props.onstatechange(props.item);
                }else{
                  props.changeitemToNormal(props.item);
                  // props.onstatechange(props.item);
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
