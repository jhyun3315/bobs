import './css/FirstLoginPage.css'

function FirstLoginPage() {
  return (
    <div className="first_login_page">
      <div className="alert">
        <div className="info">잠시만요!</div>
        <div className="suggest">알레르기 있으신가요?</div>
        <div className="content">
          알레르기 유발 재료를 등록하시면<br/> 
          해당 재료를 포함하는<br/>
          레시피는 추천하지 않아요!!
        </div>
        <div className="suggest">등록하시겠어요?</div>
        <div className="btn_box">
          <div className="no_btn">아니요</div>
          <div className="yes_btn">네</div>
        </div>
      </div>
    </div>
  )
}
export default FirstLoginPage