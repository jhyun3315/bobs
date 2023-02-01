import React from "react";
import "./toggle.css"

function Toggle(props) {
  const {
    checked,
    onChange,
    offstyle = "off",
    onstyle = "on"
  } = props;

  let displayStyle = checked ? onstyle : offstyle;
  return (
    <>
      <label>
        <span className="switch-label">좋아요만</span>
        <span className="switch-wrapper">
          <input
            type="checkbox"
            checked={checked}
            onChange={e => onChange(e)}
          />
          <span className={`${displayStyle} switch`}>
            <span className="switch-handle" />
          </span>
        </span>
      </label>
    </>
  );
}

export default Toggle;