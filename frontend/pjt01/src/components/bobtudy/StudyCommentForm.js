import React, { Component } from "react"
import './css/CommunityComment.css'

class CommunityCommentForm extends Component {
	state = {
		value : ''
	}

	handleSubmit = e => {
		e.preventDefault()

		this.props.addList(this.state.value)
		this.setState({
			value : ''
		})
	}

	handleChange = e => {
		e.preventDefault()

		const value = e.target.value
		this.setState({
			value,
		})
	}
	render() {
		return (
			<div className="com_cmt_form">
				<form onSubmit={this.handleSubmit}>
					<span className="community_ps_box">
						<input
							type="text"
							className = "com_cmt_input"
							placeholder="댓글을 입력해 주세요"
							onChange={this.handleChange}
							value={this.state.value}/>
					</span>
				</form>
			</div>
		)
	}
}
export default CommunityCommentForm