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
    <div className="community-post-create">
        <div className='post-img-view' onClick={uploadimg}>
            {fileImage && (
              <img
                alt="img"
                src={fileImage}  
              />
            )}
          </div>
          <input
              name="imgUpload"
              type="file"
              accept="image/*"
              ref={imageInput}
              onChange={saveFileImage}
          >
          </input>
          <div>
            

            {/* <button onClick={() => deleteFileImage()}>
              삭제
            </button> */}
          </div>
          <div className='post-text'>
            <input
                className='post-title'
                type="text"
                placeholder='제목을 입력하세요'
            />

            <div className='post-content'>
              <textarea
                  className='post-content'
                  type="text"
                  placeholder='내용을 입력하세요'
              />
            </div>
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