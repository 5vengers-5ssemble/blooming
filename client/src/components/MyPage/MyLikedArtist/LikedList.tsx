import { ImageData } from '@components/common/ImageData';
import { ArtistInfo } from '@type/FundingInfo';
import styled from 'styled-components';

export const LikedList = ({ datas }: { datas: ArtistInfo[] }) => {
  return (
    <ResultFrame>
      <BoxFrame>
        {datas.map((data, idx) => (
          <EachFrame key={idx}>
            <img
              src={data.artistImg ? data.artistImg : ImageData.noPicture}
            ></img>
            <Info>
              <div className="txtInfo">
                <div className="name">{data.artistName}</div>
              </div>
            </Info>
          </EachFrame>
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
  margin-top: 65px;
  display: flex;
  justify-content: flex-start;
  gap: 60px;
  flex-wrap: wrap;
`;

const Info = styled.div`
  .txtInfo {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 12px;
    font-size: 16px;
    font-weight: 600;
    line-height: 25px; /* 125% */
  }
`;

const EachFrame = styled.div`
  cursor: pointer;
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  img {
    width: 230px;
    height: 230px;
    border-radius: 50%;
    object-fit: cover;
  }
`;
