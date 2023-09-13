import { ProcessInfo } from '@type/ProcessInfo';
import React from 'react';
import styled from 'styled-components';
import { calculateDateDifference } from './EachRankBox';
import ProgressBarFrame from '@components/Button/ProgressBar';

interface Props {
  data: ProcessInfo;
}
const ThumbnailEach: React.FC<Props> = ({ data }) => {
  return (
    <EachFrame>
      <img src={data.profile_img}></img>
      <Info>
        <div className="txtInfo">
          <div className="name">{data.name}</div>
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
  }
`;
export default ThumbnailEach;
