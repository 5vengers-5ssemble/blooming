import styled from 'styled-components';
import { LiveInfo, NFTListInfo, ProcessInfo } from '@type/ProcessInfo';
import {
  ThumbnailEach,
  ThumbnailEachForNFT,
  ThumbnailEachLive,
} from './ThumbnailEach';

interface Props {
  datas: ProcessInfo[];
  nowStat: string;
}
export const ResultList: React.FC<Props> = ({ datas, nowStat }) => {
  console.log(datas);
  return (
    <ResultFrame>
      <BoxFrame $isLive={false}>
        {datas.map((data, idx) => (
          <ThumbnailEach key={idx} data={data} nowStat={nowStat} />
        ))}
      </BoxFrame>
    </ResultFrame>
  );
};
export const NFTResultList = ({
  datas,
  nowStat,
}: {
  datas: NFTListInfo[];
  nowStat: string;
}) => {
  console.log('nftdata', datas);
  return (
    <ResultFrame>
      <BoxFrame $isLive={false}>
        {datas.map((data, idx) => (
          <ThumbnailEachForNFT key={idx} data={data} nowStat={nowStat} />
        ))}
      </BoxFrame>
    </ResultFrame>
  );
};

export const LiveResultList = ({ datas }: { datas: LiveInfo[] }) => {
  console.log(datas);
  return (
    <ResultFrame>
      <BoxFrame $isLive={false}>
        {datas.map((data, idx) => (
          <ThumbnailEachLive key={idx} data={data} />
        ))}
      </BoxFrame>
    </ResultFrame>
  );
};

interface StyleProps {
  $isLive: boolean;
}

const ResultFrame = styled.div`
  display: flex;
  flex-direction: column;
`;

const BoxFrame = styled.div<StyleProps>`
  margin-top: 48px;
  display: flex;
  justify-content: flex-start;
  gap: ${(props) => (props.$isLive ? '35px' : '29px')};
  /* justify-content: space-between; */
  flex-wrap: wrap;
`;
