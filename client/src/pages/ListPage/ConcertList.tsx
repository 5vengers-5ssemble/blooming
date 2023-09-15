import styled from 'styled-components';
import SearchBar from '@components/Search/SearchBar';
import { MainTitle } from '@style/common';
import TopRankList from '@components/ListPage/TopRankList';
import ResultList from '@components/ListPage/ResultList';
import { ProcessInfo } from '@type/ProcessInfo';
import { CONCERT } from '@components/common/constant';

interface Props {
  results: ProcessInfo[];
}
const dummyData: Props = {
  results: [
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/cyr-concert.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 300,
    },
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/seventeens.jpeg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 224,
      totalProcess: 300,
    },
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/newjeans.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 200,
    },
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/cyr-concert.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 200,
    },
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/cyr-concert.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 200,
    },
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/cyr-concert.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 200,
    },
    {
      name: '아이유 (IU)',
      desc: `대한민국의 가수이자 배우이다. 배우로 활동할 때도 예명을
                사용한다. '아이유(IU)'라는 예명은 'I'와 'You'를 합친 합성어로
                '너와 내가 음악으로 하나가 된다'라는 의미이다.`,
      profileImg: 'src/assets/images/cyr-concert.jpg',
      startDate: '2023-09-01',
      endDate: '2023-10-12',
      nowProcess: 124,
      totalProcess: 200,
    },
  ],
};

const ConcertList = () => {
  return (
    <div>
      <TopFrame>
        <MainTitle>
          콘서트<div className="dot"></div>
        </MainTitle>
        <SearchBar nowStat={CONCERT} />
      </TopFrame>
      <TopRankList nowStat={CONCERT} />
      <ResultList datas={dummyData.results} nowStat={CONCERT} />
    </div>
  );
};

const TopFrame = styled.div`
  margin-top: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export default ConcertList;
