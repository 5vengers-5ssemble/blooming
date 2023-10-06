import styled from 'styled-components';
import { NFTListInfo, ProcessInfo } from '@type/ProcessInfo';
import {
  ACTIVE,
  ARTIST,
  CONCERT,
  FUNDING_PHRASES,
  NFT_PHRASES,
} from '@components/common/constant';
import ProgressBarFrame from '@components/Button/ProgressBar';
import { useNavigate } from 'react-router-dom';

interface Props {
  nowRank: NFTListInfo;
  nowStat: string;
}

// 남은 일자 계산
export const calculateDateDifference = (
  startDate: string,
  endDate: string,
): number => {
  const start = new Date(startDate);
  const end = new Date(endDate);

  const oneDayInMilliseconds = 24 * 60 * 60 * 1000;

  const differenceInMilliseconds = end.getTime() - start.getTime();

  const differenceInDays = Math.round(
    differenceInMilliseconds / oneDayInMilliseconds,
  );

  return differenceInDays;
};

//남은 상품 % 계산
export const calculateCntDifference = (now: number, end: number): string => {
  return ((now / end) * 100).toFixed(1);
};

const NowRankForNFT = ({ nowRank, nowStat }: Props) => {
  const navigate = useNavigate();

  const handleNavigate = () => {
    switch (nowStat) {
      case ARTIST:
        navigate(`/nft-detail/${nowRank.id}`);
        break;
      case ACTIVE:
        navigate(`/activity-detail/${nowRank.id}`);
        break;
      case CONCERT:
        navigate(`/concert-detail/${nowRank.id}`);
        break;
    }
  };
  return (
    <RankCard background={nowRank.thumbnailUri} onClick={handleNavigate}>
      <div className="wrapper">
        <div className="header">
          <div className="date">
            <span className="day">{nowRank.purchaseEnd.split('T')[0]}</span>
          </div>
          <ul className="top-right-content">
            <li>
              <a href="#" className="fa fa-heart-o">
                <span>{nowRank.nftSale.soldNftCount}개가 판매되었습니다</span>
              </a>
            </li>
          </ul>
        </div>
        <div className="data">
          <div className="content">
            <h1 className="title">
              <a href="">{nowRank.title.slice(0, 20)}</a>
            </h1>
            <p className="content-text">
              {nowRank.description.length > 100
                ? nowRank.description.slice(0, 100) + '...'
                : nowRank.description}
            </p>
            <RateBox>
              <ProgressBox>
                <div className="title">
                  <div className="subtitle">발행일</div>
                  <div className="remainDate">
                    {calculateDateDifference(
                      new Date().toString(),
                      nowRank.purchaseEnd,
                    )}{' '}
                    일 남음
                  </div>
                </div>
                <ProgressBarFrame
                  score={calculateDateDifference(
                    nowRank.purchaseStart,
                    new Date().toString(),
                  )}
                  total={calculateDateDifference(
                    nowRank.purchaseStart,
                    nowRank.purchaseEnd,
                  )}
                  background="var(--main3-color)"
                />
              </ProgressBox>
              <ProgressBox>
                <div className="title">
                  <div className="subtitle">
                    {nowStat === ARTIST
                      ? NFT_PHRASES.rate
                      : FUNDING_PHRASES.rate}
                  </div>
                  <div className="remainDate">
                    {calculateCntDifference(
                      nowRank.nftSale.soldNftCount,
                      nowRank.nftSale.totalNftCount,
                    )}{' '}
                    % 달성
                  </div>
                </div>
                <ProgressBarFrame
                  score={nowRank.nftSale.soldNftCount}
                  total={nowRank.nftSale.totalNftCount}
                  background="var(--main3-color)"
                />
              </ProgressBox>
            </RateBox>
            <a href="" className="button" onClick={handleNavigate}>
              자세히 보기
            </a>
          </div>
        </div>
      </div>
    </RankCard>
  );
};

const Card = styled.div`
  .top-right-content {
    margin: 0;
    padding: 0;
    list-style-type: none;

    &::before,
    &::after {
      content: '';
      display: table;
    }

    &::after {
      clear: both;
    }

    li {
      display: inline-block;
    }

    a {
      color: var(--white-color);
      text-decoration-line: none !important;
    }

    span {
      position: absolute;
      left: 50%;
      top: 0;
      font-size: 10px;
      font-weight: 700;
      transform: translate(-50%, 0);
    }
  }

  .wrapper {
    background-color: var(--white-color);
    min-height: 600px;
    position: relative;
    overflow: hidden;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.3), 0 1px 1px rgba(0, 0, 0, 0.2);

    &:hover {
      .data {
        transform: translateY(-100px);
      }
    }
  }

  .data {
    position: absolute;
    bottom: 0;
    width: 100%;
    transform: translateY(calc(70px + 1em));
    transition: transform 0.3s;

    .content {
      padding: 1em;
      position: relative;
      z-index: 1;
      margin-bottom: -100px;
      background-color: #fff8f873;
    }
  }

  .author {
    font-size: 14px;
  }

  > .title {
    margin-top: 30px;

    a {
      text-decoration-line: none;
    }
  }

  .content-text {
    height: 30px;
    margin-bottom: 30px;
    line-height: 20px;
    font-size: 14px;
  }

  input[type='checkbox'] {
    display: none;
  }

  input[type='checkbox']:checked + .top-right-content {
    transform: translateY(-80px);
  }
`;

const RankCard = styled(Card)<{ background: string }>`
  width: 360px;
  .wrapper {
    background: url(${(props) => props.background}) center/cover no-repeat;

    &:hover {
      .top-right-content span {
        transform: translate(-50%, -10px);
        opacity: 1;
      }

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(
          0,
          0,
          0,
          0.6
        ); /* 검은 불투명 필터 색상 및 투명도 조절 */
        z-index: 0.4; /* 필터 위로 컨텐츠가 나타나도록 조절 */
      }
    }

    &::before {
      content: '';
      position: absolute;
      top: 50%;
      left: 0;
      width: 100%;
      height: 50%;
      background: linear-gradient(
        to bottom,
        rgba(0, 0, 0, 0),
        rgba(0, 0, 0, 0.8)
      ); /* 아래로 그라데이션하는 검은색 필터 */
      z-index: 0.7; /* 필터 위로 컨텐츠가 나타나도록 조절 */
      pointer-events: none; /* 필터를 클릭 가능한 영역으로 인식하지 않도록 설정 */
    }
  }

  //이미지 상단 header
  .header {
    display: flex;
    justify-content: space-between;
    color: var(--white-color);
    padding: 1em;

    .date {
      float: left;
      font-size: 12px;
    }
  }

  .top-right-content {
    float: right;
    width: fit-content;
    li {
      margin: 10px;
      position: absolute;
      right: 70px;
    }

    span {
      width: max-content;
      font-weight: 500;
      font-size: 12px;
      transition: all 0.3s;
      opacity: 0;
    }
  }

  .data {
    color: var(--white-color);
    transform: translateY(calc(70px +10em));
  }

  .title a {
    color: var(--white-color);
    text-decoration-line: none;
  }

  //더보기 버튼
  .button {
    display: block;
    width: 100px;
    margin: 2em auto 1em;
    text-align: center;
    font-size: 15px;
    color: var(--white-color);
    line-height: 1;
    position: relative;
    font-weight: 700;
    text-decoration-line: none;
    &::after {
      content: '→';
      opacity: 0;
      position: absolute;
      right: 0;
      top: 50%;
      transform: translate(0, -50%);
      transition: all 0.3s;
    }

    &:hover {
      &::after {
        transform: translate(5px, -50%);
        opacity: 1;
      }
    }
  }
`;

const RateBox = styled.div`
  margin-top: 50px;
  display: flex;
  flex-direction: column;
  gap: 25px;
`;

const ProgressBox = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    font-size: 15px;
    font-weight: 600;
    line-height: 17px; /* 121.429% */
    display: flex;
    justify-content: space-between;
  }
`;

export default NowRankForNFT;
