import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';

import {
  MyPageInfo,
  ProfileInfo,
  ProfitInfo,
  SettlementInfo,
} from '@type/MyPage';

import Navbar from '@components/common/NavBar';
import Profile from '@components/MyPage/MyProfileInfo/ProfileInfo';
import MyProcess from '@components/MyPage/MyInvestInfo/MyProcess';
import LiveInfo from '@components/MyPage/MyLiveInfo/LiveInfo';
import MembershipInterface from '@components/MyPage/MyMembershipInfo/MembershipInterface';
import FundingInterface from '@components/MyPage/MyFundingInfo/FundingInterface';
import SettlementInterface from '@components/MyPage/MySettlementInfo/SettlementInterface';

import axiosTemp from '@api/apiControllerTemp';

import { ReactComponent as FileSvg } from '@assets/icons/dollar-clipboard-file.svg';
import { ReactComponent as YoutubeSvg } from '@assets/icons/youtube-logo.svg';
import { ReactComponent as ApplySvg } from '@assets/icons/diploma-certificate.svg';
import { ReactComponent as HeartSvg } from '@assets/icons/heart-padlock.svg';
import LikedArtist from '@components/MyPage/MyLikedArtist/LikedArtist';
import OnLive from '@components/MyPage/OnLive';

const MyPage = () => {
  const navigate = useNavigate();
  const { tab } = useParams();

  const [profileInfo, setProfileInfo] = useState<ProfileInfo>();
  const [isArtist, setIsArtist] = useState<boolean>(false);
  const [profitInfo, setProfitInfo] = useState<ProfitInfo>();
  const [settleInfo, setSettleInfo] = useState<SettlementInfo>();
  const [nowTab, setNowTab] = useState<number>(tab ? Number(tab) : 0);

  useEffect(() => {
    axiosTemp.get('/mypage-artist').then((res) => {
      const data: MyPageInfo = res.data;
      setProfileInfo(data.profileInfo);
      setIsArtist(data.profileInfo.isArtist);
      setProfitInfo(data.profitInfo);
      setSettleInfo(data.settlementInfo);
    });
  }, []);

  return (
    <>
      <Navbar />
      <MyPageFrame>
        <LeftSection>
          <Profile isArtist={isArtist} profileInfo={profileInfo} />
          <Tabs>
            <TabItem active={nowTab === 0} onClick={() => setNowTab(0)}>
              <FileSvg />내 투자 보고서
            </TabItem>
            <TabItem active={nowTab === 1} onClick={() => setNowTab(1)}>
              <YoutubeSvg />
              NOW 라이브
            </TabItem>
            <TabItem active={nowTab === 6} onClick={() => setNowTab(6)}>
              <HeartSvg />내 찜 목록
            </TabItem>
            {isArtist && (
              <>
                <TabItem active={nowTab === 2} onClick={() => setNowTab(2)}>
                  <ApplySvg />
                  멤버쉽 신청
                </TabItem>
                <TabItem active={nowTab === 5} onClick={() => setNowTab(5)}>
                  <YoutubeSvg />
                  라이브 ON
                </TabItem>
                <TabItem active={nowTab === 3} onClick={() => setNowTab(3)}>
                  <ApplySvg />
                  펀딩 등록
                </TabItem>
                <TabItem active={nowTab === 4} onClick={() => setNowTab(4)}>
                  <ApplySvg />
                  정산정보 입력
                </TabItem>
              </>
            )}
          </Tabs>
        </LeftSection>
        <RightSection>
          {nowTab === 0 && (
            <MyProcess
              profileInfo={profileInfo}
              profitInfo={profitInfo}
              settleInfo={settleInfo}
            />
          )}
          {nowTab === 1 && <LiveInfo />}
          {nowTab === 2 && <MembershipInterface />}
          {nowTab === 3 && <FundingInterface />}
          {nowTab === 4 && <SettlementInterface />}
          {nowTab === 5 && <OnLive />}
          {nowTab === 6 && <LikedArtist />}
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
  active: boolean;
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
  ${({ active }) =>
    active &&
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

export default MyPage;
