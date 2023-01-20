import React from 'react';
import Allergy from './components/main/Allergy';
import FirstMain from './components/main/FirstMain';
import Main from './components/main/Main';

function mainView() {
  return (
    <div>
        <Allergy></Allergy>
        <FirstMain>
        </FirstMain>
        <Main></Main>
    </div>
  );
}

export default mainView;
