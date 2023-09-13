import styled from 'styled-components';
import { ProcessInfo } from '@type/ProcessInfo';
import MainSwiper from './MainSwiper';

interface Data {
  nowBest: ProcessInfo[];
}

const dummyData: Data = {
  nowBest: [
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profile_img: 'src/assets/images/iu-profile.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 300,
    },
    {
      name: '아이브 (IVE)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profile_img: 'src/assets/images/ive.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 224,
      totalProcess: 300,
    },
    {
      name: '뉴진스 (New Jeans)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profile_img: 'src/assets/images/newjeans.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 200,
    },
  ],
};

interface Props {
  nowStat: string;
}

const TopRankList: React.FC<Props> = ({ nowStat }) => {
  return (
    <RankBox>
      <Subtitle>NOW BEST 🏆</Subtitle>
      <BoxFrame>
        <MainSwiper nowRank={dummyData.nowBest} nowStat={nowStat} />
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
