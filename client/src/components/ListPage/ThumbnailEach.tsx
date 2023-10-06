import React from 'react';
import styled from 'styled-components';
import { LiveInfo, NFTListInfo, ProcessInfo } from '@type/ProcessInfo';
import { calculateDateDifference } from './EachRankBox';
import ProgressBarFrame from '@components/Button/ProgressBar';
import { ReactComponent as LiveSvg } from '@assets/icons/broadcast.svg';

import { useNavigate } from 'react-router-dom';
import { ACTIVE, ARTIST, CONCERT } from '@components/common/constant';
import { ImageData } from '@components/common/ImageData';

interface Props {
  data: ProcessInfo;
  nowStat: string;
}

const ThumbnailEachForNFT = ({
  data,
  nowStat,
}: {
  data: NFTListInfo;
  nowStat: string;
}) => {
  const navigate = useNavigate();

  const handleNavigateDetail = () => {
    if (nowStat === ARTIST) {
      navigate(`/nft-detail/${data.id}`);
    } else if (nowStat === ACTIVE) {
      navigate(`/activity-detail/${data.id}`);
    } else if (nowStat === CONCERT) {
      navigate(`/concert-detail/${data.id}`);
    }
  };
  const leftDate = calculateDateDifference(
    new Date().toString(),
    data.purchaseEnd,
  );
  return (
    <EachFrame onClick={handleNavigateDetail}>
      <img
        src={data.thumbnailUri ? data.thumbnailUri : ImageData.noPicture}
      ></img>
      <Info>
        <div className="txtInfo">
          <div className="name">{data.title}</div>
          <div className="leftDate">
            {Math.abs(leftDate)} 일{leftDate >= 0 ? ' 남음' : ' 지남'}
          </div>
        </div>
        <ProgressBarFrame
          score={data.nftSale.soldNftCount}
          total={data.nftSale.totalNftCount}
          background="var(--main1-color)"
          height={'6px'}
        />
      </Info>
    </EachFrame>
  );
};

const ThumbnailEach: React.FC<Props> = ({ data, nowStat }) => {
  const navigate = useNavigate();

  const handleNavigateDetail = () => {
    if (nowStat === ARTIST) {
      navigate(`/nft-detail/${data.id}`);
    } else if (nowStat === ACTIVE) {
      navigate(`/activity-detail/${data.id}`);
    } else if (nowStat === CONCERT) {
      navigate(`/concert-detail/${data.id}`);
    }
  };
  const leftDate = calculateDateDifference(new Date().toString(), data.endDate);
  return (
    <EachFrame onClick={handleNavigateDetail}>
      <img src={data.profileImg ? data.profileImg : ImageData.noPicture}></img>
      <Info>
        <div className="txtInfo">
          <div className="name">{data.title}</div>
          <div className="leftDate">
            {Math.abs(leftDate)} 일{leftDate >= 0 ? ' 남음' : ' 지남'}
          </div>
        </div>
        <ProgressBarFrame
          score={data.nowProcess}
          total={data.totalProcess}
          background="var(--main1-color)"
          height={'6px'}
        />
      </Info>
    </EachFrame>
  );
};
const ThumbnailEachLive = ({ data }: { data: LiveInfo }) => {
  const navigate = useNavigate();
  const handleJoinLive = () => {
    navigate(`/meeting/${data.id}`);
  };

  return (
    <EachFrame onClick={handleJoinLive}>
      <img
        src={
          data.artist.profileImageUrl
            ? data.artist.profileImageUrl
            : ImageData.noPicture
        }
      ></img>
      <Info>
        <div className="txtInfo">
          <div className="name">
            <LiveSvg />
            <span className="liveinfo">
              <div className="title">{data.title}</div>
              {/* <DebugDiv>liveId : {data.id}</DebugDiv> */}
              <div className="artist"> @ {data.artist.stageName}</div>
            </span>
          </div>
        </div>
      </Info>
    </EachFrame>
  );
};

const EachFrame = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 60px;
  cursor: pointer;
  img {
    width: 350px;
    height: 230px;
    border-radius: 3px;
    object-fit: cover;
  }
`;
const Info = styled.div`
  margin-top: 12px;

  .txtInfo {
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 16px;
    font-weight: 700;
    line-height: 25px; /* 125% */

    .leftDate {
      color: var(--main1-color);
      font-size: 14px;
      font-weight: 600;
      line-height: 25px; /* 178.571% */
    }

    .name {
      display: flex;
      align-items: center;
      gap: 5px;
    }

    .liveinfo {
      display: flex;
      align-items: center;
      justify-content: space-between;
      width: 320px;

      svg {
        width: 15px;
        fill: var(--main4-color);
      }

      .title {
        color: var(--main4-color);
        font-weight: 700;
        font-size: 15px;
      }

      .artist {
        color: var(--main4-color);
        font-weight: 500;
        font-size: 14px;
      }
    }
  }
`;
export { ThumbnailEach, ThumbnailEachLive, ThumbnailEachForNFT };
