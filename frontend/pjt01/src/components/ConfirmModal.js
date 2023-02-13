import React from 'react'
import './ConfirmModal.css'

function ConfirmModal(props) {
  const confirmYes = () => {
    props.setconfirmModal(false)
    props.studyDelete()
  }
  return (
    <div className='confirm_modal'>
      <div className='alert'>
        <div className='title'>{props.title}</div>
        <div className='content'>{props.content}</div>
        <div className="btn_box">
          <div className='yes_btn' onClick={confirmYes}>네</div>
          <div className='no_btn' onClick={() => props.setconfirmModal(false)}>아니요</div>
        </div>
      </div>
    </div>
  )
}

export default ConfirmModal