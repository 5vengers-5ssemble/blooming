import React from 'react';
import styled from 'styled-components';

// const dummyData:Props = {
//   nowBest : {
//     {}
//   }
// }
const TopRankList = () => {
  return (
    <RankBox>
      <Subtitle>NOW BEST 🏆</Subtitle>
      <BoxFrame>
        <LeftFrame>
          <img src="src/assets/images/iu-profile.jpg"></img>
          <InfoBox>
            <div className="artist_name">아이유</div>
            <div className="artist_desc">
              대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을 사용한다.
              '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로 '너와 내가
              음악으로 하나가 된다'라는 의미이다.
            </div>
            <RateBox>
              <ProgressBox>
                <div className="title">발행일</div>
                <div className="bar"></div>
              </ProgressBox>
              <ProgressBox>
                <div className="title">NFT 판매량</div>
                <div className="bar"></div>
              </ProgressBox>
            </RateBox>
          </InfoBox>
        </LeftFrame>
        <RightFrame>
          <SecondBox>sdf</SecondBox>
          <SecondBox>sdf</SecondBox>
        </RightFrame>
      </BoxFrame>
    </RankBox>
  );
};

const RateBox = styled.div`
  margin-top: 60px;
  display: flex;
  flex-direction: column;
  gap: 25px;
`;
const ProgressBox = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    font-size: 14px;
    font-weight: 500;
    line-height: 17px; /* 121.429% */
  }

  .bar {
    margin-top: 10px;
    width: 100%;
    background-color: yellow;
    height: 10px;
  }
`;
const InfoBox = styled.div`
  .artist_name {
    font-size: 25px;
    font-weight: 700;
    line-height: 25px; /* 100% */
  }

  .artist_desc {
    margin-top: 30px;
    font-size: 12px;
    font-weight: 500;
    line-height: 17px; /* 141.667% */
  }
`;
const RankBox = styled.div`
  margin-top: 100px;
`;
const BoxFrame = styled.div`
  display: flex;
`;
const LeftFrame = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
`;
const RightFrame = styled.div`
  background-color: blue;
`;
const SecondBox = styled.div``;
const Subtitle = styled.div`
  font-size: 25px;
  font-weight: 700;
`;

export default TopRankList;
