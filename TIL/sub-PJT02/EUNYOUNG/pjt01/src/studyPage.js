import React from 'react';
import CreateBobtudy from './components/bobtudy/CreateBobtudy';
import ListBobtudy from './components/bobtudy/ListBobtudy';
import ListMystudy from './components/bobtudy/ListMystudy';


function studyPage() {
    return (
      <div>
        <h1>bobtudy</h1>
        <CreateBobtudy></CreateBobtudy>
        <ListMystudy></ListMystudy>
        <ListBobtudy></ListBobtudy>
      </div>
    );
  }
  
  export default studyPage;