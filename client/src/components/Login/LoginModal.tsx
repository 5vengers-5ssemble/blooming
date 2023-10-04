import React, { useRef, useEffect } from 'react';
import styled from 'styled-components';

interface LoginModalProps {
  closeModal: () => void;
}

const KAKAO_AUTH_URL = `${
  import.meta.env.VITE_APP_SERVER
}/oauth2/authorization/kakao`;

const LoginModal: React.FC<LoginModalProps> = ({ closeModal }) => {
  const modalBackgroundRef = useRef<HTMLDivElement | null>(null);

  const handleLogin = (url: string) => {
    window.location.href = url;
    closeModal();
  };

  return (
    <ModalBackground ref={modalBackgroundRef} onClick={closeModal}>
      <ModalContent onClick={(e) => e.stopPropagation()}>
        <div className="logo">
          <img src="src/assets/resourceImg/logofont-color.png" />
        </div>

        <form>
          <div onClick={() => handleLogin(KAKAO_AUTH_URL)}>
            <img src="src/assets/resourceImg/kakao-login.png" />
          </div>
          <div onClick={() => handleLogin('naver')}>
            <img src="src/assets/resourceImg/naver-login.png" />
          </div>
          <div onClick={() => handleLogin('google')}>
            <img src="src/assets/resourceImg/google-login.png" />
          </div>
        </form>
      </ModalContent>
    </ModalBackground>
  );
};

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  cursor: pointer;
`;

const ModalContent = styled.div`
  background-color: var(--white-color);
  padding: 40px 20px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  height: 60dvh;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  .logo {
    width: 300px;
    margin-top: 30px;
    margin-bottom: 80px;
    img {
      object-fit: contain;
      width: 300px;
    }
  }
  form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 30px;
    gap: 10px;

    img {
      object-fit: contain;
      width: 280px;
    }
  }
`;

export default LoginModal;
