import React from 'react';
import ListRecipe from '../components/recipe/ListRecipe';
import "./css/recipePage.css"

function RecipePage() {
    return (
      <div className="recipe_page">
        <ListRecipe></ListRecipe>
      </div>
    );
  }
  
  export default RecipePage;