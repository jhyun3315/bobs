import React, { Component } from "react"
import './css/StudyComment.css'

class CommunityComment extends Component{
  render() {
    return (
      <div className="study_chat">
        { this.props.children }
      </div>
    )
  }
}
export default CommunityComment