import './css/CommunityPostCreate.css';
import{ useState,useRef } from "react";
import defaultimg from "./img/defaultimg.png";
import { useHistory } from "react-router-dom";
import large_x from './img/large_x.png'


function CommunityPostCreate(props) {

  const [fileImage, setFileImage] = useState();
  const imageInput = useRef();
  console.log(imageInput)
  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
  };

  // const deleteFileImage = () => {
  //   URL.revokeObjectURL(fileImage);
  //   setFileImage("");
  // };
  const uploadimg = () => {
    imageInput.current.click();
  }
  const history = useHistory();
  const toCommunity = (e) => {
    history.push("/community");
  };
  // x버튼 클릭시 input에 들어간 이미지 삭제 
  const deleteimg = () => {
    setFileImage("")
    deleteimgfile()
  }
  // imageInput의 current의 value를 null로 바꾸는 함수
  const deleteimgfile = () => {
    imageInput.current.value = null
  }
  
  return(
    <div className="community_post_create">
      <div className='post_img' style={{backgroundImage: `url(${defaultimg})`}}>
        <div className='post_img_view' onClick={uploadimg}>
          {fileImage && (<img alt="img" src={fileImage} />)}
        </div>
        {fileImage && <div className='x_btn' onClick={deleteimg}>
          <img src={large_x} alt="X" />
        </div>}
      </div>
      <input
        name="imgUpload"
        type="file"
        accept="image/*"
        ref={imageInput}
        onChange={saveFileImage}
      />
      <div>
        {/* <button onClick={() => deleteFileImage()}>
          삭제
        </button> */}
      </div>
      <div className='title'>
        <input 
          type="text"
          placeholder='제목'
        />
      </div>
      <div className='content'>
        <textarea
          type="text"
          placeholder='내용을 입력하세요.'
        />
      </div>
      <div className='post_btn'>
            <div className='cancle_btn' onClick={toCommunity}>
              취소
            </div>
            {/* 등록 아직 미구현 임둥 */}
            <div className='enroll_btn'>
              등록
            </div>
      </div>
    </div>
  );
}

export default CommunityPostCreate;