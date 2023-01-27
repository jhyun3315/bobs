import React from 'react';
import Allergy from './components/main/Allergy';
import FirstMain from './components/main/FirstMain';
import Main from './components/main/Main';
import {Link} from 'react-router-dom';
import "../src/main.css"
import logo_white from "./img/bobs_white.png"


function MainPage() {
  return (
    <div>
        <img src={logo_white} width="240px" height="160px" />
        <Link to="/login"> Login</Link>
        <Allergy></Allergy>
        <FirstMain></FirstMain>
        <Main></Main>
    </div>
  );
}

export default MainPage;
