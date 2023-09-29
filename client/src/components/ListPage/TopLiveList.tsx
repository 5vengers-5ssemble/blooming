import styled from 'styled-components';
import { LiveInfo } from '@type/ProcessInfo';
import MainLiveSwiper from './MainLiveSwiper';

interface Props {
  bestData: LiveInfo[];
  nowStat: string;
}

const TopLiveList: React.FC<Props> = ({ bestData, nowStat }) => {
  return (
    <RankBox>
      <Subtitle>NOW BEST 🏆</Subtitle>
      <BoxFrame>
        <MainLiveSwiper nowRank={bestData} nowStat={nowStat} />
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

export default TopLiveList;
