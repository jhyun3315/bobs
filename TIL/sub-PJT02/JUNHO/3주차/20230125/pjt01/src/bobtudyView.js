import React from 'react'
import ListMystrudy from './components/bobtudy/ListMystudy'
import ListMystrudy from './components/bobtudy/DetailBobtudy'
import {Link} from 'react-router-dom';

function bobtudyView() {
  return (
    <div>
      <ListMystrudy></ListMystrudy>
      <Link to="/bobtudy/create">Bobtudy 만들기</Link>
      <Link to="/bobtudy/id">상세</Link>
    </div>
  )
}

export default bobtudyView