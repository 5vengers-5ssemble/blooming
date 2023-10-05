import AWS from 'aws-sdk';

// AWS 설정
const awsConfig = {
  credentials: new AWS.CognitoIdentityCredentials({
    IdentityPoolId: import.meta.env.VITE_IDENTITY_POOLID,
  }),
  region: import.meta.env.VITE_BUCKET_REGION, // 예: 'us-east-1'
};

AWS.config.update(awsConfig);

// S3 객체 생성
const s3 = new AWS.S3();

// 파일 업로드 함수
const uploadFile = async (file: File, key: string) => {
  const params = {
    Bucket: import.meta.env.VITE_BUCKET_NAME,
    Key: 'uploads/client/' + key, // S3 버킷 내 경로와 파일명
    Body: file, // 업로드할 파일
    ACL: 'public-read', // 업로드한 파일에 대한 퍼블릭 액세스 권한
  };

  try {
    const data = await s3.upload(params).promise();
    console.log('파일 업로드 완료:', data.Location);
    return data.Location; // 업로드된 파일의 URL 반환
  } catch (error) {
    console.error('파일 업로드 오류:', error);
    throw error;
  }
};

export default uploadFile;
