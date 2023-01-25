import React from 'react';
import Allergy from './components/main/Allergy';
import FirstMain from './components/main/FirstMain';
import Main from './components/main/Main';
import {Link} from 'react-router-dom';

function MainView() {
  return (
    <div>
      <div>MainView</div>
        <Link to="/login"> Login</Link>
        <Allergy></Allergy>
        <FirstMain></FirstMain>
        <Main></Main>
    </div>
  );
}

export default MainView;
