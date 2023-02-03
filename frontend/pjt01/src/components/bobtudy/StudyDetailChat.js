import React from "react";
import "./css/StudyDetailChat.css"
import sendicon from "../../img/send.png";
import TypingIndicator from "./StudyDetailChat/TypingIndicator";
import MessageList from "./StudyDetailChat/MessageList";


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
			messages: [{
				id: 1,
				sender: 'Shun',
				senderAvatar: 'https://i.pravatar.cc/150?img=32',
				message: 'Hello 👋'
			},
			{
				id: 2,
				sender: 'Gabe',
				senderAvatar: 'https://i.pravatar.cc/150?img=56',
				message: 'Hey!'
			},
			{
				id: 3,
				sender: 'Gabe',
				senderAvatar: 'https://i.pravatar.cc/150?img=56',
				message: 'How are you?'
			},
			{
				id: 4,
				sender: 'Shun',
				senderAvatar: 'https://i.pravatar.cc/150?img=32',
				message: 'Great! It\'s been a while... 🙃'
			},
			{
				id: 5,
				sender: 'Gabe',
				senderAvatar: 'https://i.pravatar.cc/150?img=56',
				message: 'Indeed.... We\'re gonna have to fix that. 🌮🍻'
			}
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
		users[0] = { name: 'Shun', avatar: 'https://i.pravatar.cc/150?img=32' };
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