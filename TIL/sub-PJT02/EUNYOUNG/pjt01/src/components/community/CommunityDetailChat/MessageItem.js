function MessageItem(props) {
	/* message position formatting - right if I'm the author */
	let messagePosition = (( props.owner === props.sender ) ? 'chatApp__convMessageItem--right' : 'chatApp__convMessageItem--left');
	return (
		// <div className="community_chat">
			<div className={"chatApp__convMessageItem " + messagePosition + " clearfix"}>
				<img src={props.senderAvatar} alt={props.sender} className="chatApp__convMessageAvatar" />
				{
					props.owner === props.sender ? <div className="chatApp__convMessageOwner">{props.owner}.</div> :
					<div className="chatApp__convMessageSender">{props.sender}.</div>
				}
				<br /><br />
				<div className="chatApp__convMessageValue" dangerouslySetInnerHTML={{__html: props.message}}></div>
			</div>
		// </div>
	);
}

export default MessageItem;