import React, { useEffect, useState } from 'react';
import './css/firstPage.css'
import logo from '../img/bobs_white.png'

function FirstPage() {

  // 프런트엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:3000/oauth/callback/kakao";

  // 백엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:5000/kakao/code";

  const CLIENT_ID = "6d5b3488701905eecd07dfc7034e45ec";
  const REDIRECT_URI =  "http://localhost:3000/oauth/callback/kakao";
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  const [check, setCheck] = useState(false)

  useEffect(() => {
    const timeout = setTimeout(() => setCheck(true), 1500);
    return () => clearTimeout(timeout)
  }, [check])

  return (
    <div className='login_Page'>      
      <div className='first_logo'><img src={ logo } alt="" className='first_logo_img' /></div>
      {
        check === false ? <></> : <a href={KAKAO_AUTH_URL}><button className="login_btn_block">로그인</button></a>  
      }     
    </div>
  );
}

export default FirstPage;
