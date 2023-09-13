import ToggleButton from '@components/Button/ToggleButton';
import { ProcessInfo } from '@type/ProcessInfo';
import { useState } from 'react';
import styled from 'styled-components';
import ThumbnailEach from './ThumbnailEach';
import {
  ARTIST,
  FUNDING_PHRASES,
  NFT_PHRASES,
  POPULAR,
  RECENTLY,
} from '@components/common/constant';

interface Props {
  datas: ProcessInfo[];
  nowStat: string;
}
const ResultList: React.FC<Props> = ({ datas, nowStat }) => {
  const [isToggled, setIsToggled] = useState(true);
  const [selectedSort, setSelectedSort] = useState<string>(POPULAR);

  const handleToggleChange = (checked: boolean) => {
    setIsToggled(checked);
  };

  const handleSortChange = (sort: string) => {
    setSelectedSort(sort);
  };

  return (
    <ResultFrame>
      <NowToggle>
        <LeftSection>
          <div className="toggleTitle">
            모집중인{' '}
            {nowStat === ARTIST ? NFT_PHRASES.name : FUNDING_PHRASES.name}만
            보기
          </div>
          <ToggleButton
            defaultChecked={isToggled}
            onChange={handleToggleChange}
          />
        </LeftSection>
        <RightSection>
          <SortOption
            onClick={() => handleSortChange(POPULAR)}
            isSelected={selectedSort === POPULAR}
          >
            인기순
          </SortOption>
          |
          <SortOption
            onClick={() => handleSortChange(RECENTLY)}
            isSelected={selectedSort === RECENTLY}
          >
            최신순
          </SortOption>
        </RightSection>
      </NowToggle>
      <BoxFrame>
        {datas.map((data) => (
          <ThumbnailEach data={data} />
        ))}
      </BoxFrame>
    </ResultFrame>
  );
};

const ResultFrame = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 100px;
`;

const LeftSection = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
`;
const RightSection = styled.div`
  display: flex;
  gap: 10px;
  font-size: 16px;
  font-weight: 600;
  line-height: 17px;
  color: var(--gray-color);
`;

const SortOption = styled.div<{ isSelected: boolean }>`
  cursor: pointer;
  color: ${(props) =>
    props.isSelected ? 'var(--main1-color)' : 'var(--gray-color)'};

  &:hover {
    color: var(--main1-color);
  }
`;

const NowToggle = styled.div`
  .toggleTitle {
    font-size: 25px;
    font-weight: 700;
  }
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const BoxFrame = styled.div`
  margin-top: 48px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
`;
export default ResultList;
