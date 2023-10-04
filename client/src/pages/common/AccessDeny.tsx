import loginAccess from '@assets/lottie/loginAccess.json';
import styled from 'styled-components';
import Lottie from 'lottie-react';
import { useNavigate } from 'react-router';

const AccessDeny = () => {
  const navigate = useNavigate();
  return (
    <Frame>
      <LottieFrame>
        <Lottie
          className="lottie"
          animationData={loginAccess}
          height={200}
          width={200}
          loop={false}
        />
      </LottieFrame>
      <div className="title">잠시만요! 로그인이 필요해요</div>
      <div className="route" onClick={() => navigate('/')}>
        메인으로 이동하기
      </div>
    </Frame>
  );
};

const Frame = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  .title {
    margin-top: -50px;
    font-size: 20px;
    font-weight: 700;
  }

  .route {
    cursor: pointer;
    margin-top: 50px;
    width: max-content;
    padding: 10px 15px;
    background-color: var(--main4-color);
    color: var(--white-color);
    border-radius: 6px;
  }
`;
const LottieFrame = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;

  .lottie {
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

const Button = styled.div`
  width: fit-content;
  padding: 10px 15px;
`;
export default AccessDeny;
