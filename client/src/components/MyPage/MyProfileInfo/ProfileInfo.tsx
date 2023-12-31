import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { ReactComponent as PencilSvg } from '@assets/icons/pencil.svg';
import ArtistRegistModal from './ArtistRegistModal';
import NicknameModal from './NicknameModal';
import ArtistModifModal from './ArtistModifModal';
import { getCookie, setRole, setRoleId } from '@hooks/useAuth';
import axios from '@api/apiController';
import {
  ROLE_ARTIST,
  STATE_APPLY,
  STATE_APPROVAL,
} from '@components/common/constant';
import { ImageData } from '@components/common/ImageData';

interface Props {
  isArtist: boolean;
}
const Profile = ({ isArtist }: Props) => {
  const [isModalOpen, setModalOpen] = useState(false);
  const [isModifModalOpen, setModifModalOpen] = useState(false);
  const [isNicknameModalOpen, seticknameModalOpen] = useState(false);
  const [isRequest, setRequestArtist] = useState(false);
  const [isArtistNow, setArtistNow] = useState(isArtist);

  useEffect(() => {
    axios.get('/artist-applications/me').then((res) => {
      const data = res.data.results;
      if (data.applicationState === STATE_APPLY) {
        setRequestArtist(true);
      } else if (data.applicationState === STATE_APPROVAL) {
        setArtistNow(true);
        axios.get('/artists/me').then((res) => {
          const data = res.data.results;
          setRoleId(data.id);
          setRole(ROLE_ARTIST);
        });
      }
    });
  }, []);

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const openModifModal = () => {
    setModifModalOpen(true);
  };

  const closeModifModal = () => {
    setModifModalOpen(false);
  };

  const openNicknameModal = () => {
    seticknameModalOpen(true);
  };

  const closeNicknameModal = () => {
    seticknameModalOpen(false);
  };

  return (
    <ProfileFrame>
      <ProfileImg>
        <img src={ImageData.defaultAvatar} alt="profile" />
      </ProfileImg>
      <ProfileName>
        {getCookie('Nickname')}
        <PencilSvg onClick={openNicknameModal} />
      </ProfileName>
      <ProfileQualification>
        {isArtistNow ? (
          <>
            <ArtistRegist>
              <span>다양한 유튜브 활동들을 보여주세요!</span>
              <ArtistRegistButton onClick={openModifModal}>
                아티스트 정보 수정
              </ArtistRegistButton>
            </ArtistRegist>
          </>
        ) : isRequest ? (
          <ArtistRegist>
            <ArtistRegistButton>아티스트 신청 대기중</ArtistRegistButton>
          </ArtistRegist>
        ) : (
          <>
            <ArtistRegist>
              <span>혹시 아티스트 이신가요?</span>
              <ArtistRegistButton onClick={openModal}>
                아티스트 등록하기
              </ArtistRegistButton>
            </ArtistRegist>
          </>
        )}
      </ProfileQualification>
      <ArtistModifModal
        isOpen={isModifModalOpen}
        closeModal={closeModifModal}
      />
      <ArtistRegistModal isOpen={isModalOpen} closeModal={closeModal} />
      <NicknameModal
        isOpen={isNicknameModalOpen}
        closeModal={closeNicknameModal}
      />
    </ProfileFrame>
  );
};

const ProfileFrame = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 40px;
  align-items: center;
  justify-content: center;
  color: var(--black-color);
  font-weight: 700;
`;

const ProfileImg = styled.div`
  img {
    border-radius: 50%;
    width: 120px;
    height: 120px;
    object-fit: cover;
    background-color: var(--background-color);
  }
`;

const ProfileName = styled.div`
  display: flex;
  margin-top: 10px;
  gap: 5px;
  cursor: pointer;
  font-size: 16px;
`;

const ProfileQualification = styled.div`
  display: flex;
  flex-direction: column;
`;

const ArtistRegist = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
  margin-top: 20px;
  font-weight: 400;
  font-size: 14px;
`;

const ArtistRegistButton = styled.div`
  display: flex;
  cursor: pointer;
  font-weight: 700;
  font-size: 15px;
  color: var(--main1-color);
`;
export default Profile;
