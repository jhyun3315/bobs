import React from 'react';
import ListRecipe from './components/recipe/ListRecipe';
import "./css/recipePage.css"

function recipePage() {
    return (
      <div className="recipe">
        <ListRecipe></ListRecipe>
      </div>
    );
  }
  
  export default recipePage;