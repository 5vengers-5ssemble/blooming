import { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

import axiosTemp from '@api/apiControllerTemp';
import axios from '@api/apiController';

import { ReactComponent as SuccessSvg } from '@assets/icons/success-check.svg';
import { ReactComponent as ErrorSvg } from '@assets/icons/error-check.svg';
import { ReactComponent as ArrowSvg } from '@assets/icons/angle-right.svg';
import { getCookie, setNickname } from '@hooks/useAuth';
import { ROLE_ID } from '@components/common/constant';

const NicknameModal = ({
  isOpen,
  closeModal,
}: {
  isOpen: boolean;
  closeModal: () => void;
}) => {
  const navigate = useNavigate();
  const [keyword, setKeyword] = useState('');
  const [validCheck, setValidCheck] = useState(false);

  const errorCheck = (value: string) => {
    if (value.length < 2 || value.length > 10) {
      return false;
    }
    return true;
  };

  const handleRegister = () => {
    // axiosTemp.post('/nickname-check', keyword).then((res) => {
    //   console.log(res.data);
    //   navigate(`/mypage`);
    // });
    axios
      .put(`/members/${getCookie(ROLE_ID)}`, { nickname: keyword })
      .then((res) => {
        console.log(res.data);
        setNickname(keyword);
        closeModal();
        window.location.reload();
      });
  };

  const handleErrorCheck = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;

    setKeyword(value);
    const isValid = errorCheck(value);
    setValidCheck(isValid);
  };

  if (!isOpen) return null;

  return (
    <ModalOverlay>
      <ModalWrapper>
        <ModalHeader>
          <h2>닉네임 수정</h2>
          <button onClick={closeModal}>닫기</button>
        </ModalHeader>
        <ModalContent>
          <div className="form">
            <EachFormForText>
              <ContentTitle>
                닉네임은 2글자 이상 10글자 이하로 입력해주세요
              </ContentTitle>
              <FormWithValid>
                <FormBox
                  placeholder={'닉네임을 입력해주세요'}
                  value={keyword}
                  onChange={handleErrorCheck}
                ></FormBox>
                {validCheck ? (
                  <CorrectCheck>
                    <SuccessSvg />
                    확인되었습니다
                  </CorrectCheck>
                ) : (
                  <ErrorCheck>
                    <ErrorSvg />
                    입력을 확인해주세요
                  </ErrorCheck>
                )}
              </FormWithValid>
            </EachFormForText>
          </div>
          <div className="register">
            <ButtonForRegist onClick={handleRegister}>
              수정하기 <ArrowSvg />
            </ButtonForRegist>
          </div>
        </ModalContent>
      </ModalWrapper>
    </ModalOverlay>
  );
};

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

const ModalWrapper = styled.div`
  background: white;
  border-radius: 4px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  max-width: 600px;
  width: fit-content;
`;

const ModalHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 40px 30px 0px 40px;

  h2 {
    margin: 0;
  }

  button {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 16px;
    color: var(--main1-color);
    margin-top: -10px;
    font-weight: 600;
  }
`;

const ModalContent = styled.div`
  padding: 0 50px 30px 40px;

  .register {
    display: flex;
    justify-content: flex-end;
  }

  .form {
    margin-top: 40px;
  }
`;

const ButtonForRegist = styled.div`
  cursor: pointer;
  margin: 10px -20px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  width: max-content;
  text-align: center;
  color: var(--main1-color);
`;

const EachFormForText = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

const ContentTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
`;

const FormWithValid = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 10px;
`;
const FormBox = styled.input`
  padding: 10px 5px 10px;
  border: none;
  width: 300px;
  background: none;
  border-bottom: 1px solid var(--main2-color);
  &::placeholder {
    color: var(--gray-color);
    font-weight: 300;
  }

  &:focus {
    outline: none !important;
  }
`;
const CorrectCheck = styled.div`
  display: flex;
  align-items: center;
  color: var(--success-color);
  gap: 5px;
  font-size: 13px;
  padding: 10px 5px 10px;
  border: none;
  width: fit-content;
`;
const ErrorCheck = styled.div`
  display: flex;
  align-items: center;
  color: var(--error-color);
  gap: 5px;
  font-size: 13px;
  padding: 10px 5px 10px;
  border: none;
  width: fit-content;
`;
export default NicknameModal;
