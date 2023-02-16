import React, { useRef } from "react";
import camera_img from '../img/camera.png'

function Scanimage (props)  {

  // const [text,settext]=useState("");
  const inputRef = useRef(null);
  // const [base64String,setbase64String] = useState("null");
  const CallApi = async (text) => {

      const USER_ID = 'clarifai';
      const PAT = '4080d369df934d1d87d5f887f7fc2c3c';
      const APP_ID = 'main';   
      const MODEL_ID = 'food-item-v1-recognition';
      const MODEL_VERSION_ID = "dfebc169854e429086aceb8368662641";
      const raw = JSON.stringify({
          "user_app_id": {
              "user_id": USER_ID,
              "app_id": APP_ID
          },
          "inputs": [
            {
                "data": {
                    "image": {
                        "base64": text
                    }
                }
            }
          ],
          "model": {
              "output_info": {
                  "output_config": {
                      "language": "en"
                  }
              }
          }
      });
  
      const requestOptions = {
          method: 'POST',
          headers: {
              'Accept': 'application/json',
              'Authorization': 'Key ' + PAT
          },
          body: raw
      };
  

      let fromLang = 'en';
      let toLang = 'ko'; 
      
      const API_KEY = "AIzaSyDz0UOFdeLplg7qMgTaVJzlf3uxI57SPWw";
      fetch("https://api.clarifai.com/v2/models/" + MODEL_ID + "/versions/" + MODEL_VERSION_ID + "/outputs", requestOptions)
          .then(response => response.text())
          .then((result) => {
              const topics = JSON.parse(result)
              console.log(topics.outputs[0].data.concepts[0].name)
              
              let url = `https://translation.googleapis.com/language/translate/v2?key=${API_KEY}`;
              url += '&q=' + encodeURI(topics.outputs[0].data.concepts[0].name);
              url += `&source=${fromLang}`;
              url += `&target=${toLang}`;
              fetch(url, { 
                method: 'GET',
                headers: {
                  "Content-Type": "application/json",
                  Accept: "application/json"
                }
              })
              .then(res => res.json())
              .then((response) => {
                const res=response.data.translations[0].translatedText
                // settext(response.data.translations[0].translatedText)
                props.setreftext(res)
              })
              .catch(error => {
                console.log("There was an error with the translation request: ", error);
              })
              }
          )
          .catch(error => console.log('error', error));

  };
  // const files= []
  const convert=(e)=> {
    var reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);

    reader.onload = () => {
      const i=reader.result;
      const k=i.split("base64,")
      console.log(k); //base64encoded string
      CallApi(k[1])
      // setbase64String(k[1]);
      // console.log(k);
    };
    reader.onerror = error => {
      console.log("Error: ", error);
    };
    setTimeout(() => {
      
    }, 100);
    
  }


  const handleClick = () => {
    inputRef.current.click();
 }
  
  return (
    <div className='add_item_complete' onClick={handleClick}>
      
      {/* <button onClick={callApi}>Call API</button> */}
        {/* {text} */}
          <input
              style={{
                display:"none"
              }}
              ref={inputRef}
              accept="image/*"
              type="file"
              onChange={convert}
          />
        <img src={camera_img} alt="사진" />
    </div>
  );
};

export default Scanimage;
