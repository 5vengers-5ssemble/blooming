import { ArtistAdmit } from '@type/AdminAdmit';
import React from 'react';
import styled from 'styled-components';

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  artistData: ArtistAdmit;
  onApprove: () => void;
  onReject: () => void;
}

const DetailModal: React.FC<ModalProps> = ({
  isOpen,
  onClose,
  artistData: data,
  onApprove,
  onReject,
}) => {
  if (!isOpen) return null;

  return (
    <ModalBackground onClick={onClose}>
      <ModalContainer>
        <ModalHeader></ModalHeader>
        <ModalBody>
          <ThumbnailImg src={data.profileImageUrl} alt={data.stageName} />
          <InfoContainer>
            <InfoItem>
              <InfoLabel>아티스트 명 : </InfoLabel>
              <InfoValue>{data.stageName}</InfoValue>
            </InfoItem>
            <InfoItem>
              <InfoLabel>소속사 : </InfoLabel>
              <InfoValue>{data.agency}</InfoValue>
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
