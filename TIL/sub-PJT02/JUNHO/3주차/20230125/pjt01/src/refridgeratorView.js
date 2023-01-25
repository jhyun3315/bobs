import React from 'react';
import AddItem from './components/ref/AddItem';
import EditItem from './components/ref/EditItem';
import GetItem from './components/ref/GetItem';
import SearchItem from './components/ref/SearchItem';
import SelectedItem from './components/ref/SelectedItem';

function Info_VIEW() {
  return (
    <div>
      <AddItem></AddItem>

      <EditItem></EditItem>
      <GetItem></GetItem>
      <SearchItem></SearchItem>
      <SelectedItem></SelectedItem>
    </div>
  );
}

export default Info_VIEW;
