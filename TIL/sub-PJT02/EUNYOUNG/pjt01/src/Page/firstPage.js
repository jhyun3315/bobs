import React, { useEffect, useState, useRef } from 'react';
import './css/firstPage.css'
import { useHistory } from "react-router-dom";

function FirstPage() {

  // 프런트엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:3000/oauth/callback/kakao";

  // 백엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:5000/kakao/code";
  const history = useHistory();
  const CLIENT_ID = "6d5b3488701905eecd07dfc7034e45ec";
  const REDIRECT_URI =  "http://localhost:5000/oauth/callback/kakao";
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  const [check, setCheck] = useState(false)
  const [logincheck,setlogincheck] =useState(false);
  const fadeout = useRef();

  useEffect(() => {
    const getlogin= sessionStorage.getItem("login");
    console.log("login")
    if(getlogin){
      console.log("login")
      setlogincheck(true);
      fadeout.current.id="complete"
    }
    
    const timeout = setTimeout(() => setCheck(true), 2000);    
    if(logincheck){
      history.push('/main')
    }
    return () => clearTimeout(timeout)


  }, [check])

  return (
    <div className='login_Page' ref={fadeout}>      
      {
        check === false ? <></> : <a href={KAKAO_AUTH_URL}><button className="login_btn_block">로그인</button></a>  
      }   
      <div className='firset_logo_text'>Bobs</div>  
    </div>
  );
}

export default FirstPage;