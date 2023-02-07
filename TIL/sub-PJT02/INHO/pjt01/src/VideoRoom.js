import VideoRoomComponent from './videocom/components/VideoRoomComponent';
import "./css/VideoRoom.css"


function VideoRoom(params) {
  return(
    <div className='video-room'>
      <VideoRoomComponent></VideoRoomComponent>
    </div>
  );
}

export default VideoRoom;