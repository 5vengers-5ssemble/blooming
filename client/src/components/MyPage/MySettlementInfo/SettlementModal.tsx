import React, { useState } from 'react';
import styled from 'styled-components';

import { FundingProcessApplication } from '@type/ApplicationList';
import { calculateComma } from '@utils/calculateComma';

interface SettlementModalProps {
  funding: FundingProcessApplication;
  onClose: () => void;
}

const SettlementModal: React.FC<SettlementModalProps> = ({
  funding,
  onClose,
}) => {
  const [revenue, setRevenue] = useState<string>('');
  const [error, setError] = useState<string>('');

  const handleSettlementSubmit = () => {
    console.log(`정산 정보 입력 - 수익: ${revenue}`);
    onClose();
  };

  const handleErrorCheck = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    const stringWithoutCommas = value.replace(/,/g, '');
    setRevenue(stringWithoutCommas);
    if (isNaN(Number(revenue))) {
      setError('숫자만 입력해주세요');
      setRevenue('');
    } else {
      setError('');
    }
  };

  return (
    <ModalOverlay>
      <ModalWrapper>
        <ModalHeader>
          <h2>정산 정보 입력</h2>
          <button onClick={onClose}>닫기</button>
        </ModalHeader>
        <ModalContent>
          <EachFormForText>
            <ContentTitle>총 순수익을 입력해주세요</ContentTitle>
            <div className="row">
              <FormBox
                placeholder={'순수익을 입력해주세요'}
                value={calculateComma(Number(revenue))}
                onChange={handleErrorCheck}
              ></FormBox>
              <span>원</span>
            </div>

            {error.length > 0 && <ErrorText>{error}</ErrorText>}
          </EachFormForText>
          <SubmitButton onClick={handleSettlementSubmit}>입력</SubmitButton>
        </ModalContent>
      </ModalWrapper>
    </ModalOverlay>
  );
};
const EachFormForText = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

const ContentTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
`;

const ErrorText = styled.div`
  color: var(--error-color);
  font-size: 12px;
  margin-top: 8px;
`;

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
  max-width: 400px;
  width: 100%;
`;

const ModalHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;

  h2 {
    margin: 0;
  }

  button {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 16px;
    color: var(--main1-color);
    font-weight: 600;
  }
`;

const ModalContent = styled.div`
  padding: 16px;
`;

const SubmitButton = styled.button`
  width: 100%;
  margin-top: 30px;
  padding: 8px;
  background-color: var(--main1-color);
  color: var(--white-color);
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 400;
`;

const FormBox = styled.input`
  padding: 10px 5px 10px;
  border: none;
  width: 340px;
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

export default SettlementModal;
