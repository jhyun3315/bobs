import React from 'react';
import "./css/SelectedItem.css";

function SelectedItem(props) {
  
    return (
      <div className='itemlistbox'>
        <div className='item_text'>{props.item}</div>       
      </div>
    );
  }
  
  export default SelectedItem;
  