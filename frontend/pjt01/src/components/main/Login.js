import React from 'react';

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
            <button>로그인</button>
        </a>

        
      </div>

      
    );
    
  }
  
  

  export default Login;
  