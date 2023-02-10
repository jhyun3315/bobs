import React from "react";
import "./css/StudyDetailChat.css"
import sendicon from "../../img/send.png";
import TypingIndicator from "../community/CommunityDetailChat/TypingIndicator";
import MessageList from "../community/CommunityDetailChat/MessageList";

function detectURL(message) {
	var urlRegex = /(((https?:\/\/)|(www\.))[^\s]+)/g;
	return message.replace(urlRegex, function(urlMatch) {
		return '<a href="' + urlMatch + '">' + urlMatch + '</a>';
	})
}
class InputMessage extends React.Component {
	constructor(props, context) {
		super(props, context);
		this.handleSendMessage = this.handleSendMessage.bind(this);
		this.handleTyping = this.handleTyping.bind(this);
	}
	handleSendMessage(event) {
		event.preventDefault();
		/* Disable sendMessage if the message is empty */
		if( this.messageInput.value.length > 0 ) {
			this.props.sendMessageLoading(this.ownerInput.value, this.ownerAvatarInput.value, this.messageInput.value);
			/* Reset input after send*/
			this.messageInput.value = '';
		}
	}
	handleTyping(event) {
		if( this.messageInput.value.length > 0 ) {
			this.props.typing(this.ownerInput.value);
		}
		else {
			this.props.resetTyping(this.ownerInput.value);
		}
	}
	render() {
		/* If the chatbox state is loading, loading class for display */
		var loadingClass = this.props.isLoading ? 'chatApp__convButton--loading' : '';
		return (
			<form className="mes-form" onSubmit={this.handleSendMessage} > 
				<input
					type="hidden"
					ref={owner => (this.ownerInput = owner)}
					value={this.props.owner}
				/>
				<input
					type="hidden"
					ref={ownerAvatar => (this.ownerAvatarInput = ownerAvatar)}
					value={this.props.ownerAvatar}
				/>
				<input
					type="text"
					ref={message => (this.messageInput = message)}
					className={"chatApp__convInput"}
					placeholder="Text message"
					onKeyDown={this.handleTyping}
					onKeyUp={this.handleTyping}
					tabIndex="0"
				/>
				<div className={'chatApp__convButton ' + loadingClass} onClick={this.handleSendMessage}>
					<img id="sendicon" src={sendicon} alt=""/>
				</div>
			</form>
		);
	}
}
class ChatBox extends React.Component {
	constructor(props, context) {
		super(props, context);
		this.state = {
			isLoading: false
		};
		this.sendMessageLoading = this.sendMessageLoading.bind(this);
	}
	/* catch the sendMessage signal and update the loading state then continues the sending instruction */
	sendMessageLoading(sender, senderAvatar, message) {
		this.setState({ isLoading: true });
		this.props.sendMessage(sender, senderAvatar, message);
		setTimeout(() => {
			this.setState({ isLoading: false });
		}, 400);
	}
	render() {
		return (
			<div className={"chatApp__conv"}>
				
				<MessageList
					owner={this.props.owner}
					messages={this.props.messages}
				/>
				<div className={"chatApp__convSendMessage clearfix"}>
					<TypingIndicator
						owner={this.props.owner}
						isTyping={this.props.isTyping}
					/>
					<InputMessage
						isLoading={this.state.isLoading}
						owner={this.props.owner}
						ownerAvatar={this.props.ownerAvatar}
						sendMessage={this.props.sendMessage}
						sendMessageLoading={this.sendMessageLoading}
						typing={this.props.typing}
						resetTyping={this.props.resetTyping}
					/>
				</div>
			</div>
		);
	}
}

class StudyDetailChat extends React.Component {
	constructor(props, context) {
		super(props, context);
		this.state = {
			messages: [
				{
					id: 1,
					sender: '김싸피',
					senderAvatar: 'https://i.pravatar.cc/150?img=33',
					message: 'ㅁ나어라ㅣㅓㅏㅣㅈ더라ㅣㅓ나밍러ㅣㅓㄴㅇ리ㅓㅏㅣㅁㄴ어라ㅣ넝라ㅣㄴㅁ어라ㅏㅓㅁ닝ㄹ마인러ㅣㅓㅏ'
				},
				{
					id: 2,
					sender: '이싸피',
					senderAvatar: 'https://i.pravatar.cc/150?img=56',
					message: '안녕!'
				},
				{
					id: 3,
					sender: '최싸피',
					senderAvatar: 'https://i.pravatar.cc/150?img=53',
					message: '안녕안녕?'
				},
				{
					id: 4,
					sender: '김싸피',
					senderAvatar: 'https://i.pravatar.cc/150?img=33',
					message: '오늘은 무엇을 먹을까... 🙃'
				},
				{
					id: 5,
					sender: '이싸피',
					senderAvatar: 'https://i.pravatar.cc/150?img=56',
					message: ' 🌮🍻'
				},
			],
			isTyping: [],
		};
		this.sendMessage = this.sendMessage.bind(this);
		this.typing = this.typing.bind(this);
		this.resetTyping = this.resetTyping.bind(this);
	}
	/* adds a new message to the chatroom */
	sendMessage(sender, senderAvatar, message) {
		setTimeout(() => {
			let messageFormat = detectURL(message);
			let newMessageItem = {
				id: this.state.messages.length + 1,
				sender: sender,
				senderAvatar: senderAvatar,
				message: messageFormat
			};
			this.setState({ messages: [...this.state.messages, newMessageItem] });
			this.resetTyping(sender);
		}, 400);
	}
	/* updates the writing indicator if not already displayed */
	typing(writer) {
		if( !this.state.isTyping[writer] ) {
			let stateTyping = this.state.isTyping;
			stateTyping[writer] = true;
			this.setState({ isTyping: stateTyping });
		}
	}
	/* hide the writing indicator */
	resetTyping(writer) {
		let stateTyping = this.state.isTyping;
		stateTyping[writer] = false;
		this.setState({ isTyping: stateTyping });
	}
	render() {
		let users = {};
		let chatBoxes = [];
		let messages = this.state.messages;
		let isTyping = this.state.isTyping;
		let sendMessage = this.sendMessage;
		let typing = this.typing;
		let resetTyping = this.resetTyping;

		/* user details - can add as many users as desired */
		users[0] = { name: '김싸피', avatar: 'https://i.pravatar.cc/150?img=32' };
		// users[1] = { name: 'Gabe', avatar: 'https://i.pravatar.cc/150?img=56' };
		/* test with two other users :)
		users[2] = { name: 'Kate', avatar: 'https://i.pravatar.cc/150?img=47' };
		users[3] = { name: 'Patrick', avatar: 'https://i.pravatar.cc/150?img=14' };
		*/
		
		Object.keys(users).map(function(key) {
			var user = users[key];
			chatBoxes.push(
				<ChatBox
					key={key}
					owner={user.name}
					ownerAvatar={user.avatar}
					sendMessage={sendMessage}
					typing={typing}
					resetTyping={resetTyping}
					messages={messages}
					isTyping={isTyping}
				/>
			);
            return null;
		});
		return (
			<div className={"chatApp__room"}>
				{chatBoxes}
			</div>
		);
	}
}

export default StudyDetailChat;
// import React, { useState } from "react";
// import "./css/StudyDetailChat.css"
// import sendicon from "../../img/send.png";
// import user_data from "../user.data"
// import chat from './chat.data'
// import user_img from '../../img/profile_default.png'

// function StudyDetailChat() {

// 	const [text, setText] = useState("");
// 	const users = user_data;
// 	const chats = chat;
// 	const now_id = 4;

// 	return (
// 		<div className="study_chat">
// 			<div className="chat_box">
// 				{
// 					chats.map((chat, index) => {
// 						const user = users.filter(i => i.id === chat.user_id)
// 						if (user[0].id === now_id){
// 							return (
// 								<div className="current_user" key={index}>
// 									<div className="current_user_info">
// 										<div className="current_name">{user[0].nick}</div>
// 										<div className="other_icon"><img src={user_img} alt="img" className="oter_img"/></div>
// 									</div>
// 									<div className="current_content">{chat.content}</div>
// 								</div>
// 							)
// 						}
// 						else {
// 							return (
// 								<div className="other_user" key={index}>
// 									<div className="other_user_info">
// 										<div className="other_icon"><img src={user_img} alt="img" className="oter_img"/></div>
// 										<div className="other_name">{user[0].nick}</div>
// 									</div>
// 									<div className="other_content">{chat.content}</div>
// 								</div>
// 							)
// 						}
// 					})
// 				}
// 			</div>
// 			<div className="send_input">
// 				<input type="text" value={text} className="send_inputbox" onChange={(e)=>{setText(e.target.value)}} />
// 				<div className="send_icon"><img src={sendicon} alt="send" className="send_img"/></div>
// 			</div>
// 		</div>
// 	)

// }

// export default StudyDetailChat;