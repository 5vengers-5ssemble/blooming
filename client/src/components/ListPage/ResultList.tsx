import styled from 'styled-components';
import { ProcessInfo } from '@type/ProcessInfo';
import ThumbnailEach from './ThumbnailEach';

interface Props {
  datas: ProcessInfo[];
  nowStat: string;
}
const ResultList: React.FC<Props> = ({ datas, nowStat }) => {
  console.log(datas);
  return (
    <ResultFrame>
      <BoxFrame>
        {datas.map((data, idx) => (
          <ThumbnailEach key={idx} data={data} />
        ))}
      </BoxFrame>
    </ResultFrame>
  );
};

const ResultFrame = styled.div`
  display: flex;
  flex-direction: column;
`;

const BoxFrame = styled.div`
  margin-top: 48px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
`;
export default ResultList;
