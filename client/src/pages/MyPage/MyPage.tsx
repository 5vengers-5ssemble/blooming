import ArtistRegistModal from '@components/MyPage/ArtistRegistModal';
import React, { useState } from 'react';
import styled from 'styled-components';

const MyPage = () => {
  const [isModalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <div>
      <ArtistRegist>
        <span>혹시 아티스트 이신가요?</span>
        <ArtistRegistButton onClick={openModal}>
          아티스트 등록하기
        </ArtistRegistButton>
      </ArtistRegist>

      <ArtistRegistModal isOpen={isModalOpen} closeModal={closeModal} />
    </div>
  );
};

const ArtistRegist = styled.div`
  display: flex;
  align-items: center;
  gap: 20px;
  margin-top: 50px;
`;
const ArtistRegistButton = styled.div`
  display: flex;
  cursor: pointer;
  font-weight: 600;
  color: var(--main1-color);
`;

export default MyPage;
