import './css/CommunityCreate.css';
import{ useState,useRef } from "react";
import AWS from 'aws-sdk';
import defaultimg from "../img/defaultimg.png";
import { useHistory } from "react-router-dom";
import x from '../img/x.png'


function CommunityPostCreate() {

  const s3 = new AWS.S3();
  const [fileImage, setFileImage] = useState();
  const [file, setFile] = useState(null);
  const imageInput = useRef();
  const ACCESS_KEY = process.env.ACCESS_KEY;
  const SECRET_ACCESS_KEY = process.env.SECRET_ACCESS_KEY;
  const REGION = "ap-northeast-2";
  const S3_BUCKET = 'bobsimg';

  AWS.config.update({
    accessKeyId: ACCESS_KEY,
    secretAccessKey: SECRET_ACCESS_KEY,
    region : REGION
  });

  console.log(imageInput)
  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    setFile(e.target.files[0]);
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

  const uploadToS3 = async () => {
    var maxSize = 5 * 1024 * 1024;
    if (!file) {
      return;
    }
    if(file.size>maxSize){
      alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
			return;
    }
    console.log(file)
    const params = { 
      ACL: 'public-read',
      Body: file,
      Bucket: S3_BUCKET,
      Key: file.name,
      ContentType:'image/jpeg'
    };
    const { Location } = await s3.upload(params).promise();
    console.log('uploading to s3', Location);
  }

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
      <div className='title'>게시글 등록하기</div>
      <div className='post_img' style={{backgroundImage: `url(${defaultimg})`}}>
        <div className='post_img_view' onClick={uploadimg}>
          {fileImage && (<img alt="img" src={fileImage} />)}
        </div>
        {fileImage && <div className='x_btn' onClick={deleteimg}>
          <img src={x} alt="X" />
        </div>}
      </div>
      <input
        name="imgUpload"
        type="file"
        accept="image/*"
        ref={imageInput}
        onChange={saveFileImage}
      />
      <div className='title'>
        <input 
          type="text"
          placeholder='제목 (최대 15자)'
        />
      </div>
      <div className='content'>
        <textarea
          type="text"
          placeholder='내용을 입력하세요. (최대 200자)'
        />
      </div>
      <div className='post_btn'>
            <div className='cancle_btn' onClick={toCommunity}>
              취소
            </div>
            {/* 등록 아직 미구현 임둥 */}
             <div className='enroll_btn' onClick={uploadToS3}>
              등록
            </div>
      </div>
    </div>
  );
}

export default CommunityPostCreate;