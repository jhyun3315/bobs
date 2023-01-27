import React from 'react';
import kakaoimage from './kakao_login_large_wide.png';
function Login() {
  const CLIENT_ID = "6d5b3488701905eecd07dfc7034e45ec";
  const REDIRECT_URI =  "http://localhost:3000/oauth/callback/kakao";

  // 프런트엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:3000/oauth/callback/kakao";

  // 백엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:5000/kakao/code";

  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  return (
      <div>
        Login
        <a href={KAKAO_AUTH_URL}>
            <img src={kakaoimage} alt="kakaoimg"  style={{width: "300px", position: "absolute", top:"40%", left:"10%"}}/>
        </a>
      </div>

      
    );
    
  }
  
  

  export default Login;
  