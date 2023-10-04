import { useState } from 'react';
import styled from 'styled-components';
import Loading from '@components/Animation/Loading';
import { ProfileInfo } from '@type/MyPage';
import { ReactComponent as PencilSvg } from '@assets/icons/pencil.svg';
import ArtistRegistModal from './ArtistRegistModal';
import NicknameModal from './NicknameModal';
import ArtistModifModal from './ArtistModifModal';
import { getCookie } from '@hooks/useAuth';
import { ROLE, ROLE_ARTIST } from '@components/common/constant';

interface Props {
  isArtist: boolean;
  profileInfo: ProfileInfo | undefined;
}
const Profile = ({ isArtist, profileInfo }: Props) => {
  const [isModalOpen, setModalOpen] = useState(false);
  const [isModifModalOpen, setModifModalOpen] = useState(false);
  const [isNicknameModalOpen, seticknameModalOpen] = useState(false);
  if (!profileInfo) {
    return (
      <>
        <Loading />
      </>
    );
  }

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
        <img src={profileInfo.profileImg} alt="profile" />
      </ProfileImg>
      <ProfileName>
        {getCookie('Nickname')}
        <PencilSvg onClick={openNicknameModal} />
      </ProfileName>
      <ProfileQualification>
        {isArtist ? (
          <>
            <ArtistRegist>
              <span>다양한 유튜브 활동들을 보여주세요!</span>
              <ArtistRegistButton onClick={openModifModal}>
                아티스트 정보 수정
              </ArtistRegistButton>
            </ArtistRegist>
          </>
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
