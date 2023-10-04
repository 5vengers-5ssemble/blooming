import styled from 'styled-components';
import { ProcessInfo } from '@type/ProcessInfo';
import MainSwiper from './MainSwiper';

interface Props {
  bestData: ProcessInfo[];
  nowStat: string;
}

const TopRankList: React.FC<Props> = ({ bestData, nowStat }) => {
  return (
    <RankBox>
      <Subtitle>NOW BEST 🏆</Subtitle>
      <BoxFrame>
        <MainSwiper nowRank={bestData} nowStat={nowStat} />
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
