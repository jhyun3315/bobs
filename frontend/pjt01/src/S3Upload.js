import {useState} from "react";
import AWS from 'aws-sdk';




function S3Upload() {
  const s3 = new AWS.S3();
  const [imageUrl, setImageUrl] = useState(null);
 

  const ACCESS_KEY = 'AKIA2A2FFZJ6L242GQNB';
  const SECRET_ACCESS_KEY = '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';
  const REGION = "ap-northeast-2";
  const S3_BUCKET = 'bobsimg';

  AWS.config.update({
    accessKeyId: ACCESS_KEY,
    secretAccessKey: SECRET_ACCESS_KEY,
    region : REGION
  });



  const handleFileInput = (e) => {
    setFile(e.target.files[0]);
  }
  const uploadToS3 = async () => {
    if (!file) {
      return;
    }
    const params = { 
      ACL: 'public-read',
      Body: file,
      Bucket: S3_BUCKET,
      Key: file.name,
      ContentType:'image/jpeg'
    };
    const { Location } = await s3.upload(params).promise();
    setImageUrl(Location);
    console.log('uploading to s3', Location);
  }

  return (
    <div className="App2">

      <div style={{ marginTop: '150px' }}>
            <h1>Test Image Upload</h1>
            <input type="file" onChange={handleFileInput} />
            {file && (
              <div style={{ marginTop: '10px' }}>
                <button onClick={uploadToS3}>Upload</button>
              </div>
            )}
            {imageUrl && (
              <div style={{ marginTop: '10px' }}>
                <img src={imageUrl} alt="uploaded" />
              </div>
            )}
        </div>
    </div>
  );
}

export default S3Upload;