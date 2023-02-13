import React, { Component } from "react"
import './css/CommunityComment.css'

class CommunityComment extends Component{
  render() {
    return (
      <div className="cmt_comment">
        { this.props.children }
      </div>
    )
  }
}
export default CommunityComment