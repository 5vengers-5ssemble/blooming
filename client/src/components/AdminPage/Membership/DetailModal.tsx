// Modal.tsx
import { MembershipAdmit } from '@type/AdminAdmit';
import React from 'react';
import styled from 'styled-components';

import { ReactComponent as CloseSvg } from '@assets/icons/cancel.svg';

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  nftData: MembershipAdmit;
  onApprove: () => void;
  onReject: () => void;
}

const DetailModal: React.FC<ModalProps> = ({
  isOpen,
  onClose,
  nftData: data,
  onApprove,
  onReject,
}) => {
  if (!isOpen) return null;

  return (
    <ModalBackground onClick={onClose}>
      <ModalContainer>
        <ModalHeader>
          <ModalTitle>{data.title}</ModalTitle>
          <CloseButton onClick={onClose}>
            <CloseSvg />
          </CloseButton>
        </ModalHeader>
        <ModalBody>
          <ThumbnailImg src={data.thumbnailUrl} alt={data.title} />
          <InfoContainer>
            <InfoItem>
              <InfoLabel>시즌 기간 : </InfoLabel>
              <InfoValue>
                {data.seasonStart.split('T')[0]} -{' '}
                {data.seasonEnd.split('T')[0]}
              </InfoValue>
            </InfoItem>
            <InfoItem>
              <InfoLabel>구매 가능 기간 : </InfoLabel>
              <InfoValue>
                {data.purchaseStart.split('T')[0]} -{' '}
                {data.purchaseEnd.split('T')[0]}
              </InfoValue>
            </InfoItem>
            <InfoItem>
              <InfoLabel>아티스트 명 : </InfoLabel>
              <InfoValue>{data.artist.name}</InfoValue>
            </InfoItem>
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
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
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
  align-items: center;
`;

const ThumbnailImg = styled.img`
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
  margin-right: 16px;
`;

const InfoContainer = styled.div``;

const InfoItem = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 8px;
`;

const InfoLabel = styled.div`
  font-weight: bold;
  margin-right: 4px;
`;

const InfoValue = styled.div``;

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
