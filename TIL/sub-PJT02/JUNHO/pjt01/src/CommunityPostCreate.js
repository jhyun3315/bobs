import './css/CommunityPostCreate.css';
import{ useState,useRef } from "react";
import defaultimg from "./img/defaultimg.png";
function CommunityPostCreate(props) {

  const [fileImage, setFileImage] = useState(defaultimg);
  const imageInput = useRef();
  
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


  return(
    <div className="community_post_create">
      <div className='post_img_view' onClick={uploadimg}>
        {fileImage && (<img alt="img" src={fileImage} />)}
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
          placeholder='내용을 입력하세요'
        />
      </div>
      <div className='post-button'>
            <div>
              취소
            </div>
            <div>
              등록
            </div>
      </div>
    </div>
  );
}

export default CommunityPostCreate;