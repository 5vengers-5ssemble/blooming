import { ACTIVE, ARTIST, CONCERT } from '@components/common/constant';
import React, { useEffect, useState } from 'react';
import axiosTemp from '@api/apiControllerTemp';
import {
  MyNftInfo,
  MySettleFundingInfo,
  MyUnSettleFundingInfo,
  ProfileInfo,
  ProfitInfo,
  SettlementInfo,
} from '@type/MyPage';
import styled from 'styled-components';
import MoneyInfo from './MoneyInfo';

import { ReactComponent as MusicAlbumSvg } from '@assets/icons/music-album.svg';
import { ReactComponent as ConcertSvg } from '@assets/icons/microphone-stage.svg';
import { ReactComponent as CoinSvg } from '@assets/icons/coins.svg';
import MyFundingList from './MyFundingList';
import MyNFTList from './MyNFTList';
import { getCookie } from '@hooks/useAuth';
import { myfund, mynft } from './ProcessInfo';

interface Props {
  profitInfo: ProfitInfo | undefined;
  settleInfo: SettlementInfo | undefined;
}

const MyProcess = ({ profitInfo, settleInfo }: Props) => {
  const [nowProcessTab, setNowProcessTab] = useState<string>(ACTIVE);
  const [nowNftInfo, setNowNftInfo] = useState<MyNftInfo[]>();
  const [doneNftInfo, setDoneNftInfo] = useState<MyNftInfo[]>();
  const [nowSettleFundingInfo, setNowSettleFundingInfo] =
    useState<MySettleFundingInfo[]>();
  const [nowUnSettleFundingInfo, setNowUnSettleFundingInfo] =
    useState<MyUnSettleFundingInfo[]>();

  useEffect(() => {
    setNowUnSettleFundingInfo(myfund.ing);
    setNowSettleFundingInfo(myfund.done);
    // axiosTemp.get('/my-fund').then((res) => {
    //   const data = res.data;
    //   setNowUnSettleFundingInfo(data.ing);
    //   setNowSettleFundingInfo(data.done);
    // });
  }, []);

  useEffect(() => {
    switch (nowProcessTab) {
      case ACTIVE:
        setNowNftInfo(mynft.ing);
        setDoneNftInfo(mynft.done);
        // axiosTemp.get('/my-nft').then((res) => {
        //   const data = res.data;
        //   setNowNftInfo(data.ing);
        //   setDoneNftInfo(data.done);
        // });
        break;
      case CONCERT:
        setNowUnSettleFundingInfo(myfund.ing);
        setNowSettleFundingInfo(myfund.done);
        // axiosTemp.get('/my-fund').then((res) => {
        //   const data = res.data;
        //   setNowUnSettleFundingInfo(data.ing);
        //   setNowSettleFundingInfo(data.done);
        // });
        break;
      case ARTIST:
        setNowUnSettleFundingInfo(myfund.ing);
        setNowSettleFundingInfo(myfund.done);
      // axiosTemp.get('/my-fund').then((res) => {
      //   const data = res.data;
      //   setNowUnSettleFundingInfo(data.ing);
      //   setNowSettleFundingInfo(data.done);
      // });
      // break;
    }
  }, [nowProcessTab]);

  return (
    <ProcessFrame>
      <OpenMent>
        <div className="title">
          <span>{getCookie('Nickname')}</span> 님 안녕하세요 🙋‍♂️
        </div>
      </OpenMent>
      <Subtitle>
        오늘의 투자 보고서<div className="dot"></div>
      </Subtitle>
      <MoneyInfo profitInfo={profitInfo} settleInfo={settleInfo} />

      <Subtitle>
        펀딩 목록<div className="dot"></div>
      </Subtitle>
      <Tabs>
        <TabItem
          $active={nowProcessTab === ACTIVE}
          onClick={() => setNowProcessTab(ACTIVE)}
        >
          <MusicAlbumSvg />
          활동 펀딩
        </TabItem>
        <TabItem
          $active={nowProcessTab === CONCERT}
          onClick={() => setNowProcessTab(CONCERT)}
        >
          <ConcertSvg />
          콘서트 펀딩
        </TabItem>
        <TabItem
          $active={nowProcessTab === ARTIST}
          onClick={() => setNowProcessTab(ARTIST)}
        >
          <CoinSvg />
          NFT
        </TabItem>
      </Tabs>
      <ResultFrame>
        {(nowProcessTab === ACTIVE || nowProcessTab === CONCERT) && (
          <MyFundingList
            nowSettleFundingInfo={nowSettleFundingInfo}
            nowUnSettleFundingInfo={nowUnSettleFundingInfo}
          />
        )}
        {nowProcessTab === ARTIST && (
          <MyNFTList nowNftInfo={nowNftInfo} doneNftInfo={doneNftInfo} />
        )}
      </ResultFrame>
    </ProcessFrame>
  );
};

const ProcessFrame = styled.div`
  margin: 70px 60px;
`;

const OpenMent = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: -20px;
  .title {
    font-size: 30px;
    font-weight: 600;
    > span {
      color: var(--main1-color);
    }
  }
`;

const Subtitle = styled.div`
  margin-top: 70px;
  font-weight: 700;
  font-size: 22px;
  display: flex;
  align-items: flex-end;
  gap: 4px;
  .dot {
    width: 6px;
    height: 6px;
    border-radius: 80%;
    background-color: var(--main1-color);
  }
`;

const Tabs = styled.div`
  display: flex;
  gap: 20px;
  align-items: center;
  margin-top: 20px;
`;

interface TabItemProps {
  $active: boolean;
}

const TabItem = styled.div<TabItemProps>`
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 70px;
  padding: 15px;
  border-radius: 50%;
  background-color: var(--background2-color);
  font-weight: 600;
  font-size: 14px;
  gap: 10px;

  svg {
    width: 30px;
    height: 30px;
  }
  ${({ $active }) =>
    $active &&
    `
    background-color : var(--main1-color);
    color: var(--white-color);
    font-weight: 500;
    svg {
      fill : white;
      color : white;
    }
  `};
`;

const ResultFrame = styled.div`
  display: flex;
  margin-top: 40px;
  gap: 100px;
  width: 90%;
`;

export default MyProcess;
