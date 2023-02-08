import React from 'react';
import "./css/SelectedItem.css";
import { useState } from "react";


function SelectedItem(props) {
  const [colorstate, setcolorstate] = useState("");
  const styleclick = {
    background : "grey"
  }
  const styleclick2 = {
    background : "white"
  }
  return(
      <div>
        {colorstate ? (
        <div className='itemlistbox'>
            < div style={styleclick} key={props.index} className='item_text'  
              onClick={() => {
                setcolorstate(null);
                props.deleteItem(props.item.itemid);
            }}
            >
            <p className="itemText">{props.item.itemid}</p>
          </div>
        </div>
        ) : (
        <div className='itemlistbox'>
        <div style={styleclick2} key={props.index} className='item_text'
          onClick={() => {
            setcolorstate(1);
            props.addItem(props.item.itemid);
            
        }}>
          <p className="itemText">{props.item.itemid}</p>
        </div >  
      </div>

      )}
      </div>


  );
}

export default SelectedItem;


  