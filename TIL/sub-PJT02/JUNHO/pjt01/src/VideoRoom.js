import VideoRoomComponent from '../videocom/components/VideoRoomComponent';
import { useRouteMatch } from 'react-router-dom';
import "./css/VideoRoom.css"

function VideoRoom() {

  const match = useRouteMatch()
  console.log(match.params)

  return(
    <div className='video-room'>
      <VideoRoomComponent></VideoRoomComponent>
    </div>
  );
}

export default VideoRoom;