import React, { useEffect, useState, useRef } from 'react';
import './css/firstPage.css'
import { useHistory, useLocation,useParams  } from "react-router-dom";
import axios from 'axios';

function FirstPage() {


  const history = useHistory();
  // const url = "http://localhost:8080"

  const url = "https://i8b304.p.ssafy.io"
  const CLIENT_ID = "a170d137da8c6693eacb1d31f30d2d45";
  // const REDIRECT_URI =  "https://i8b304.p.ssafy.io/api/oauth2/authorization/kakao";
  const REDIRECT_URI =  "http://localhost:8080/oauth2/authorization/kakao"
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  const [check, setCheck] = useState(false)
  const [logincheck,setlogincheck] =useState(false);
  const fadeout = useRef();
  const location = useLocation();
  const [accesstoken,setaccesstoken] = useState("");

  useEffect(() => {
    const params=new URLSearchParams(location.search);
    var userdata=null;
    var token=null;
    var uri=params.get("id");
    console.log(uri);
    if(uri!==null){
      console.log(uri.split(' '));
      var userdata=uri.split(' ');
    }
    if(userdata!==null){
      var token=userdata[2];
      var id=userdata[0];
    }
    if(token!==null){
      setaccesstoken(token.substring(7));
      sessionStorage.setItem("login", accesstoken);
    }
    const getlogin= sessionStorage.getItem("login");
    if(getlogin){
      setlogincheck(true);
      fadeout.current.id="complete"
    }
    
    console.log(sessionStorage.getItem("login"));

    axios.get(url+'/api/users/np/'+id
      ,{
      }).then((res) => {
        console.log(res);
        localStorage.setItem("id",res.data.data.user_id)
        localStorage.setItem("name",res.data.data.user_name)
        localStorage.setItem("profile",res.data.data.user_profile)
        console.log(res.data.data.user_id)
        console.log(res.data.data.user_name)
        console.log(res.data.data.user_profile)
      })

    
    const timeout = setTimeout(() => setCheck(true), 2000);    
    if(logincheck){
      history.push('/main')
    }
    return () => clearTimeout(timeout)


  }, [accesstoken,check])

  return (
    <div className='login_Page' ref={fadeout}>      
      {
        check === false ? <></> : <a href={REDIRECT_URI}><button className="login_btn_block">로그인</button></a>  
      }   
      <div className='firset_logo_text'>밥스</div>  
    </div>
  );
}

export default FirstPage;
