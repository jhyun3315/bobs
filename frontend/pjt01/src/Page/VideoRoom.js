import VideoRoomComponent from '../videocom/components/VideoRoomComponent';
import {useLocation  } from 'react-router-dom';
import "./css/VideoRoom.css"

function VideoRoom(props) {
  const location = useLocation();
  // const match = useRouteMatch()
  const sessionName="Session"+location.state.room;
  // console.log({sessionName});
  const user=localStorage.getItem("name")
  return(
    <div className='video-room'>
      <VideoRoomComponent sessionName={sessionName} user={user}></VideoRoomComponent>
    </div>
  );
}

export default VideoRoom;