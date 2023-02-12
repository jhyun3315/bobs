import './css/CommunityPostCreate.css';
import{ useState, useRef, useEffect } from "react";
import AWS from 'aws-sdk';
import defaultimg from "../img/defaultimg.png";
import { useHistory, useLocation } from "react-router-dom";
import large_x from '../img/large_x.png';
import axios from 'axios';

function CommunityPostCreate() {

  const location = useLocation();
  const data_title = location?.state?.title;
  const data_img = location?.state?.img;
  const data_id = location?.state?.id;
  const [fileImage, setFileImage] = useState(location?.state?.img);
  const [file, setFile] = useState(location?.state?.img);
  const [title, setTitle] = useState(location?.state?.title);
  const [content, setContent] = useState(location?.state?.content);
  const imageInput = useRef();

  // console.log(imageInput)
  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    setFile(e.target.files[0]);
  };

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
    else {
      onData()
    }
  }

  const onData = async () => {
    let formData = new FormData();

    if(!data_id) formData.append("user_id", 2)
    else formData.append("community_id", data_id)
    formData.append("community_title", title)
    formData.append("community_content", content)
    formData.append('community_img', file);

    for(let key of formData.keys()) console.log(key)
    for(let value of formData.values()) console.log(value)

    const config = {
      Headers: {"Content-Type" : "multipart/form-data"}
    }

    if(!data_id){
      try{
        const postData = await axios.post(
          // 'https://i8b304.p.ssafy.io/api/communities',
          'http://localhost:8080/api/communities',
          formData,
          config
        ).then((res) => {
          history.push('/community/' + res.data.community.community_id)
        }) 
        console.log(postData)
      }catch(e){console.log(e)}
    }
    else {
      try{
        const postData = await axios.put(
          // 'https://i8b304.p.ssafy.io/api/communities',
          'http://localhost:8080/api/communities',
          formData,
          config
        ).then((res) => {
          console.log(res)
        }) 
        console.log(postData)
      }catch(e){console.log(e)}
    }
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
      <div className='post_img' style={{ backgroundImage: `url(${defaultimg})` }}>
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
          value={title} 
          onChange={(e)=>setTitle(e.target.value)} 
          placeholder="제목 (최대 15자 공백포함)" />
      </div>
      <div className='content'>
        <textarea
          type="text"
          value={content}
          onChange={(e)=>setContent(e.target.value)}
          placeholder='내용을 입력하세요. (최대 200자)'
        />
      </div>
      <div className='post_btn'>
        <div className='cancle_btn' onClick={toCommunity}>
          취소
        </div>
        {/* 등록 아직 미구현 임둥 */}
        {
          data_title ? <div className="enroll_btn" onClick={uploadToS3}>수정</div> : <div className='enroll_btn' onClick={uploadToS3}>등록</div>
        }          
      </div>
    </div>
  );
}

export default CommunityPostCreate;