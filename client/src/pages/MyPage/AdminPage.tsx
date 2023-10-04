import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';

import Navbar from '@components/common/NavBar';
import MembershipInterface from '@components/AdminPage/Membership/MembershipInterface';
import FundingInterface from '@components/AdminPage/Funding/FundingInterface';

import { ReactComponent as ApplySvg } from '@assets/icons/diploma-certificate.svg';
import ArtistInterface from '@components/AdminPage/Artist/ArtistInterface';

const AdminPage = () => {
  const navigate = useNavigate();
  const { tab } = useParams();

  const [nowTab, setNowTab] = useState<number>(tab ? Number(tab) : 0);

  return (
    <>
      <Navbar />
      <MyPageFrame>
        <LeftSection>
          <img
            src="https://blooming-image.s3.ap-northeast-2.amazonaws.com/uploads/alert.png"
            alt="admin-img"
          />
          <Tabs>
            <TabItem $active={nowTab === 0} onClick={() => setNowTab(0)}>
              <ApplySvg />
              멤버쉽 승인
            </TabItem>
            <TabItem $active={nowTab === 1} onClick={() => setNowTab(1)}>
              <ApplySvg />
              펀딩 승인
            </TabItem>
            <TabItem $active={nowTab === 2} onClick={() => setNowTab(2)}>
              <ApplySvg />
              아티스트 승인
            </TabItem>
          </Tabs>
        </LeftSection>
        <RightSection>
          {nowTab === 0 && <MembershipInterface />}
          {nowTab === 1 && <FundingInterface />}
          {nowTab === 2 && <ArtistInterface />}
        </RightSection>
      </MyPageFrame>
    </>
  );
};

const MyPageFrame = styled.div`
  margin: 1px -280px -100px;
  display: flex;
`;

const LeftSection = styled.div`
  width: 20%;
  background-color: var(--background2-color);
  display: flex;
  flex-direction: column;
  img {
    width: 130px;
    height: 140px;
    margin: 30px auto 0px;
  }
`;

const RightSection = styled.div`
  height: 100dvh;
  width: 80%;
  background-color: var(--white-color);
  height: 100dvh;
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
    background-color: var(--white-color);
  }
`;

const Tabs = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 45px;
  align-items: center;
  justify-content: center;
  gap: 16px;
`;

interface TabItemProps {
  $active: boolean;
}

const TabItem = styled.div<TabItemProps>`
  cursor: pointer;
  font-size: 15px;
  width: 75%;
  border-radius: 5px;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px;
  margin-left: 10px;
  font-weight: 600;
  ${({ $active }) =>
    $active &&
    `
    background-color : var(--main1-color);
    color: var(--white-color);
    font-weight: 500;
    svg {
      color : var(--white-color);
      fill : var(--white-color);
    }
  `};
  svg {
    width: 20px;
    height: 20px;
  }
`;

export default AdminPage;
