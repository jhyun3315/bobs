import React, { useEffect, useState, useRef } from 'react';
import './css/firstPage.css'
import { useHistory, useLocation,useParams  } from "react-router-dom";
import axios from 'axios';

function FirstPage() {

  // 프런트엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:3000/oauth/callback/kakao";

  // 백엔드 리다이랙트 URI 예시
  // const REDIRECT_URI =  "http://localhost:5000/kakao/code";
  const history = useHistory();
  const CLIENT_ID = "6d5b3488701905eecd07dfc7034e45ec";
  // const REDIRECT_URI =  "https://i8b304.p.ssafy.io/oauth2/authorization/kakao";
  const REDIRECT_URI =  "http://localhost:8080/oauth2/authorization/kakao";
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  const [check, setCheck] = useState(false)
  const [logincheck,setlogincheck] =useState(false);
  const fadeout = useRef();
  const location = useLocation();

  useEffect(() => {
    const params=new URLSearchParams(location.search);
    var token=params.get('atk')
    if(token!==null){
      sessionStorage.setItem("login", token.substring(6));
    }
    const getlogin= sessionStorage.getItem("login");
    console.log("login")
    if(getlogin){
      console.log("login")
      setlogincheck(true);
      fadeout.current.id="complete"
    }
    const access_token=token.substring(6)
    console.log(access_token)
    axios.post('https://kapi.kakao.com/v2/user/me'
    ,{},{
      headers: {
        "Authorization": "Bearer "+access_token
      }
    }).then((res) => {
      console.log(res);
      console.log(res.data.kakao_account.profile.nickname)
      history.push("/");
    })
    
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
