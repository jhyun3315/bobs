// import { useState } from "react";
import "./css/recipeDetailPage.css"
function RecipeDetailPage() {
  const foodimg=""
  // const [foodimg,setfoodimg]=useState("")
  
  return (
      <div className="recipe-detail-page">
        <div className="recipe-detail-page-move">
          <div className="left-page">
            왼쪽
          </div>
          <div className="count-page">
            n/4
          </div>

          <div className="right-page">
            오른쪽
          </div>

        </div>
        <div className="recipe-detail-img">

        </div>
        <img src={foodimg} alt="" />
        <div className="recipe-detail-content">
          안녕안녕
        </div>
      </div>
    );
  }
  
  export default RecipeDetailPage;