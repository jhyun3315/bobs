import React, { useState } from "react";
import "./css/StudyDetailChat.css"
import sendicon from "../../img/send.png";
import user_data from "../user.data"
import chat from './chat.data'
import user_img from '../../img/logo.png'

function StudyDetailChat() {

	const [text, setText] = useState("");
	const users = user_data;
	const chats = chat;
	const now_id = 4;

	return (
		<div className="study_chat">
			<div className="chat_box">
				{
					chats.map((chat, index) => {
						const user = users.filter(i => i.id === chat.user_id)
						if (user[0].id === now_id){
							return (
								<div className="current_user" key={index}>
									<div className="current_user_info">
										<div className="current_name">{user[0].nick}</div>
										<div className="other_icon"><img src={user_img} alt="img" className="oter_img"/></div>
									</div>
									<div className="current_content">{chat.content}</div>
								</div>
							)
						}
						else {
							return (
								<div className="other_user" key={index}>
									<div className="other_user_info">
										<div className="other_icon"><img src={user_img} alt="img" className="oter_img"/></div>
										<div className="other_name">{user[0].nick}</div>
									</div>
									<div className="other_content">{chat.content}</div>
								</div>
							)
						}
					})
				}
			</div>
			<div className="send_input">
				<input type="text" value={text} className="send_inputbox" onChange={(e)=>{setText(e.target.value)}} />
				<div className="send_icon"><img src={sendicon} alt="send" className="send_img"/></div>
			</div>
		</div>
	)

}

export default StudyDetailChat;