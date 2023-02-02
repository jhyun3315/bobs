import React from 'react';
import {Link} from 'react-router-dom';
import CreateCommunity from './components/community/CreateCommunity.js';
import ListCommunity from './components/community/ListCommunity.js';

function communityPage() {
    return (
      <div>
        커뮤니티 페이지
        <Link to="/community">community</Link>
        <CreateCommunity></CreateCommunity>
        <ListCommunity></ListCommunity>
      </div>
    );
  }
  
  export default communityPage;