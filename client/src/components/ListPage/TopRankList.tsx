import styled from 'styled-components';
import { ProcessInfo } from '@type/ProcessInfo';
import MainSwiper from './MainSwiper';
import NowRank from './EachRankBox';

interface Props {
  bestData: ProcessInfo[];
  nowStat: string;
}

const TopRankList: React.FC<Props> = ({ bestData, nowStat }) => {
  return (
    <RankBox>
      <Subtitle>NOW BEST 🏆</Subtitle>
      <BoxFrame>
        {bestData.map((eachRank, idx) => (
          <div className="each-slide">
            <NowRank nowRank={eachRank} nowStat={nowStat} />
          </div>
        ))}
      </BoxFrame>
    </RankBox>
  );
};

const RankBox = styled.div`
  margin-top: 70px;
`;

const Subtitle = styled.div`
  font-size: 25px;
  font-weight: 700;
`;

const BoxFrame = styled.div`
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  margin-top: 40px;
`;

export default TopRankList;
