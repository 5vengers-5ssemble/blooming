import React from 'react';
import styled from 'styled-components';
import { LiveInfo, ProcessInfo } from '@type/ProcessInfo';
import { calculateDateDifference } from './EachRankBox';
import ProgressBarFrame from '@components/Button/ProgressBar';
import { ReactComponent as LiveSvg } from '@assets/icons/broadcast.svg';

interface Props {
  data: ProcessInfo;
}
const ThumbnailEach: React.FC<Props> = ({ data }) => {
  return (
    <EachFrame>
      <img src={data.profileImg}></img>
      <Info>
        <div className="txtInfo">
          <div className="name">{data.title}</div>
          <div className="leftDate">
            {calculateDateDifference(new Date().toString(), data.endDate)} 일
            남음
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
  return (
    <EachFrame>
      <img
        src={data.artist.profileImageUrl ? data.artist.profileImageUrl : ''}
      ></img>
      <Info>
        <div className="txtInfo">
          <div className="name">
            <LiveSvg />
            <span className="liveinfo">
              <div className="title">{data.title}</div>
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
    width: 300px;
    height: 200px;
    border-radius: 3px;
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
      width: 270px;

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
export { ThumbnailEach, ThumbnailEachLive };
