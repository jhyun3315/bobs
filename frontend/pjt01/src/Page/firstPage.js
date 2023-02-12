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
  const CLIENT_ID = "a170d137da8c6693eacb1d31f30d2d45";
  // const REDIRECT_URI =  "https://i8b304.p.ssafy.io/oauth2/authorization/kakao";
  const REDIRECT_URI =  "http://localhost:8080/oauth2/authorization/kakao";
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  const [check, setCheck] = useState(false)
  const [logincheck,setlogincheck] =useState(false);
  const fadeout = useRef();
  const location = useLocation();
  const [accesstoken,setaccesstoken] = useState("");

  useEffect(() => {
    const params=new URLSearchParams(location.search);
    var token=params.get('atk')
    if(token!==null){
      setaccesstoken(token.substring(6));
      sessionStorage.setItem("login", accesstoken);
    }
    const getlogin= sessionStorage.getItem("login");
    console.log("login")
    if(getlogin){
      console.log("login")
      setlogincheck(true);
      fadeout.current.id="complete"
    }

    // const url = "https://i8b304.p.ssafy.io"
    // axios.get(url+'/api/users/find/'+accesstoken,
    // {

    // }).then((res) => {
    //   console.log(res);
    //   // console.log(res.data.kakao_account.profile.nickname)
    //   history.push("/");
    // })
    
    const timeout = setTimeout(() => setCheck(true), 2000);    
    if(logincheck){
      history.push('/main')
    }
    return () => clearTimeout(timeout)


  }, [check,accesstoken])

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
