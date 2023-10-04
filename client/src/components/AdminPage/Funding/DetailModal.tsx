// Modal.tsx
import { MembershipAdmit } from '@type/AdminAdmit';
import React from 'react';
import styled from 'styled-components';

import { ReactComponent as CloseSvg } from '@assets/icons/cancel.svg';
import { FundAddInfo } from '@type/ProcessInfo';
import { CONCERT } from '@components/common/constant';
import { calculateComma } from '@utils/calculateComma';

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  fundData: FundAddInfo;
  onApprove: () => void;
  onReject: () => void;
}

const DetailModal: React.FC<ModalProps> = ({
  isOpen,
  onClose,
  fundData: data,
  onApprove,
  onReject,
}) => {
  if (!isOpen) return null;

  return (
    <ModalBackground onClick={onClose}>
      <ModalContainer>
        <ModalHeader>
          <ModalTitle>{data.basicInfo.title}</ModalTitle>
          <CloseButton onClick={onClose}>
            <CloseSvg />
          </CloseButton>
        </ModalHeader>
        <ModalBody>
          <InfoContainer>
            <div>
              <h2>✅ 프로젝트 정보</h2>
              <p>
                <span>카테고리:</span>{' '}
                {data.projectInfo.category === CONCERT ? '콘서트' : '활동'}
              </p>
              <p>
                <span>라이선스 번호: </span>{' '}
                {data.projectInfo.makerInfo.licenseNumber}
              </p>
              <p>
                <span>회사명:</span> {data.projectInfo.makerInfo.companyName}
              </p>
              <p>
                <span>목표 금액:</span>{' '}
                {calculateComma(data.projectInfo.targetAmount)} 원
              </p>

              <h2>✅ 기본 정보</h2>
              <p>
                <span>시작일:</span> {data.basicInfo.startDate.split(' ')[0]}
              </p>
              <p>
                <span>종료일:</span> {data.basicInfo.endDate.split(' ')[0]}
              </p>
              <p>
                <span>제목:</span> {data.basicInfo.title}
              </p>

              <h2>✅ 스토리 정보</h2>
              <p>
                <span>소개: </span>
                {data.storyInfo.introduction}
              </p>
              <p>
                <span>티저:</span>{' '}
                <a href={data.storyInfo.teaser} target="_blank">
                  {data.storyInfo.teaser}
                </a>
              </p>
              <p>
                <span>예산:</span> {calculateComma(data.storyInfo.budget)} 원
              </p>
              <p>
                <span>추가 정보:</span> {data.storyInfo.moreInfo.description}
              </p>
              <p>
                <span>추가 정보 리스트 다운로드:</span>{' '}
                <a href={data.storyInfo.moreInfo.listImage} target="_blank">
                  {data.storyInfo.moreInfo.listImage}
                </a>
              </p>
              <p>
                <span>추가 정보 구성 다운로드:</span>{' '}
                <a
                  href={data.storyInfo.moreInfo.compositionImage}
                  target="_blank"
                >
                  {data.storyInfo.moreInfo.compositionImage}
                </a>
              </p>

              <h2>✅ 정산 정보</h2>
              <p>
                <span>대표자:</span>
                {data.settlementInfo.representative}
              </p>
              <p>
                <span>이메일: </span>
                {data.settlementInfo.email}
              </p>
              <p>
                <span>계좌번호:</span> {data.settlementInfo.accountNumber}
              </p>
              <p>
                <span>통장 사본:</span>{' '}
                <a href={data.settlementInfo.bankbookImage} target="_blank">
                  {data.settlementInfo.bankbookImage}
                </a>
              </p>
            </div>
          </InfoContainer>
        </ModalBody>
        <ButtonContainer>
          <Button className="approve" onClick={onApprove}>
            승인
          </Button>
          <Button className="deny" onClick={onReject}>
            거절
          </Button>
        </ButtonContainer>
      </ModalContainer>
    </ModalBackground>
  );
};

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ModalContainer = styled.div`
  background-color: white;
  width: 500px;
  padding: 25px;
  border-radius: 8px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  height: 80dvh;
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 10px;
    height: 100vh;
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 3px;
    height: 15px;
    background-color: var(--main2-color);
  }
  &::-webkit-scrollbar-track {
    background-color: var(--main3-color);
  }
`;

const ModalHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
`;

const ModalTitle = styled.h2`
  font-size: 20px;
`;

const CloseButton = styled.button`
  background: none;
  border: none;
  cursor: pointer;
  font-size: 18px;
`;

const ModalBody = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const InfoContainer = styled.div`
  margin-left: -120px;
  margin-top: -60px;

  h2 {
    margin-left: -10px;
    margin-top: 50px;
    font-size: 18px;
    color: var(--main1-color);
    padding-bottom: 5px;
    border-bottom: 1px solid var(--main2-color);
  }

  span {
    font-weight: 600;
    color: var(--main4-color);
    margin-right: 4px;
  }
`;

const ButtonContainer = styled.div`
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;

  .deny {
    background-color: var(--error-color);
  }
  .approve {
    background-color: var(--success-color);
  }
`;

const Button = styled.button`
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
`;

export default DetailModal;
