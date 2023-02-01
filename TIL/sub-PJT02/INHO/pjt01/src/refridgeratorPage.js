import AddItem from './components/ref/AddItem';
import EditItem from './components/ref/EditItem';
import GetItem from './components/ref/GetItem';
import SelectedItem from './components/ref/SelectedItem';

import "./css/refridgeratorPage.css"

function refridgeratorPage() {
  return (
    <div className='refridgerator'>
      <div className="main">나의 냉장고</div>
      <div className="itembox">
        <AddItem></AddItem>
        <EditItem></EditItem>
        <GetItem></GetItem>
      </div>

      <div className="priority">
        <div className='text'>
          우선소비
        </div>
        
        <SelectedItem></SelectedItem>
      </div>

      
      <div className='last'>
        <div className='text'>
          일반
        </div>
        <SelectedItem></SelectedItem>
      </div>
      
    </div>
  );
}

export default refridgeratorPage;
