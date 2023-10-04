import {
  MyFundingInfo,
  MyNftInfo,
  MySettleFundingInfo,
  MyUnSettleFundingInfo,
} from '@type/MyPage';
import styled from 'styled-components';

const NFTListElement = ({ nft }: { nft: MyNftInfo }) => {
  return (
    <ListElement>
      <div className="left">
        <img src={nft.profileImg} />
        <div className="info">
          <div className="name">{nft.name}</div>
          <div className="artist">{nft.artistName}</div>
        </div>
      </div>
      <div className="rate">시즌 {nft.seasonNum}</div>
    </ListElement>
  );
};

const FundingSettleListElement = ({
  funding,
}: {
  funding: MySettleFundingInfo;
}) => {
  return (
    <ListElement>
      <div className="left">
        <img src={funding.fundingImg} />
        <div className="info">
          <div className="name">{funding.name}</div>
          <div className="artist">{funding.artistName}</div>
        </div>
      </div>
      <div className="rate">{funding.profitRate} %</div>
    </ListElement>
  );
};
const FundingUnSettleListElement = ({
  funding,
}: {
  funding: MyUnSettleFundingInfo;
}) => {
  return (
    <ListElement>
      <div className="left">
        <img src={funding.fundingImg} />
        <div className="info">
          <div className="name">{funding.name}</div>
          <div className="artist">{funding.artistName}</div>
        </div>
      </div>
      <div className="rate">{funding.achiveRate} %</div>
    </ListElement>
  );
};

const ListElement = styled.div`
  cursor: pointer;
  display: flex;
  gap: 20px;
  justify-content: space-between;

  .rate {
    font-weight: 700;
    color: var(--main1-color);
  }
  .left {
    display: flex;
    gap: 12px;
    .info {
      display: flex;
      flex-direction: column;
      gap: 5px;
      .name {
        font-weight: 600;
        font-size: 16px;
      }
      .artist {
        font-size: 14px;
        color: var(--main2-color);
        font-weight: 600;
      }
    }
  }
  img {
    width: 50px;
    height: 50px;
    object-fit: cover;
    border-radius: 6px;
  }
`;

export { NFTListElement, FundingSettleListElement, FundingUnSettleListElement };
