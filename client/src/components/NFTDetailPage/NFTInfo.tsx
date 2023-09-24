import React, { useState } from 'react';
import styled from 'styled-components';
import ProgressBarFrame from '@components/Button/ProgressBar';

const NFTInfo = () => {
  // 숫자 증감 관련
  const [countState, setCount] = useState(0);

  const handleIncrement = () => {
    setCount(countState + 1);
  };

  const handleDecrement = () => {
    if (countState > 0) {
      setCount(countState - 1);
    }
  };
  return (
    <NFT>
      <div className="nft_title">The Golden Hour</div>
      <NFTInfoBox>
        <NFTImgBox>
          <img src="src/assets/images/NFT_img.png" alt="" className="nft_img" />
        </NFTImgBox>
        <NFTDescBox>
          <NFTDesc>
            <div className="title">컬렉션 정보</div>
            <div className="content">
              29살인 아이유가 20대를 마무리하면서 지금까지 자신을 지켜봐 준 모든
              사람들에게 감사 인사를 전하는 앨범. 4년 만에 선보이는 정규앨범
              [LILAC]은 스무 살의 솔직하고 풋풋한 감성을 담아 발표했던 20대의 첫
              앨범 [스무 살의 봄]과는 달리, 지금껏 지나온 20대를 10개의 트랙에
              다채로운 시각으로 풀어내 그동안의 성숙해진 감성을 오롯이 담았다.
            </div>
          </NFTDesc>
          <ArtistDesc>
            <div className="title">크리에이터 정보</div>
            <div className="content">아이유 (IU)</div>
          </ArtistDesc>
          <NFTProgress>
            <ProgressTitleBox>
              <div className="title">발행률</div>
              <div className="progress">32 / 200</div>
            </ProgressTitleBox>
            <ProgressBarFrame
              score={32}
              total={200}
              background="var(--main1-color)"
              height={'11px'}
            />
          </NFTProgress>
          <BuyNFT>
            <PriceBox>
              <div className="price_eth">0.989 ETH</div>
              <div className="price_dollar">$ 1,620.74</div>
            </PriceBox>
            <BtnBox>
              <CounterBtnBox>
                <button
                  id="decrement"
                  className="counter_btn"
                  onClick={handleDecrement}
                >
                  -
                </button>
                <span id="count">{countState}</span>
                <button
                  id="increment"
                  className="counter_btn"
                  onClick={handleIncrement}
                >
                  +
                </button>
              </CounterBtnBox>
              <PublishBtnBox>
                <button className="publish_btn">발행하기</button>
              </PublishBtnBox>
            </BtnBox>
          </BuyNFT>
        </NFTDescBox>
      </NFTInfoBox>
    </NFT>
  );
};

const PublishBtnBox = styled.div`
  height: 46px;
  width: 75%;
  background: var(--Main, #3061b9);
  border-radius: 6px;
  cursor: pointer;
  text-align: center;

  .publish_btn {
    background: none;
    border: none;
    padding: 0;
    cursor: pointer;

    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
  }
`;
const CounterBtnBox = styled.div`
  margin-right: 15px;

  height: 46px;
  width: 25%;
  background: #c7c7c7;
  border-radius: 6px;

  text-align: center;

  #count {
    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
    margin: 0 40px;
  }

  .counter_btn {
    background: none;
    border: none;
    padding: 0;

    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
    cursor: pointer;
  }
`;
const BtnBox = styled.div`
  display: flex;
`;
const PriceBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 24px;

  .price_eth {
    color: #3061b9;
    font-size: 25px;
    font-weight: 700;
    line-height: 17px;
  }

  .price_dollar {
    font-size: 14px;
    font-weight: 500;
    line-height: 17px;
  }
`;
const BuyNFT = styled.div`
  background: var(--White, #fdfdfd);
  box-shadow: 0px 0px 7px 0px rgba(48, 97, 185, 0.1);
  padding: 30px;
  margin-top: 40px;
  border-radius: 6px;
`;
const ProgressTitleBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  .progress {
    font-size: 14px;
    font-weight: 500;
    line-height: 17px;
  }
`;
const NFTProgress = styled.div``;
const ArtistDesc = styled.div``;
const NFTDesc = styled.div``;
const NFTDescBox = styled.div`
  .title {
    font-size: 25px;
    font-weight: 700;
    line-height: 25px;
    margin-bottom: 20px;
  }

  .content {
    font-size: 14px;
    font-weight: 500;
    line-height: 20px; /* 142.857% */
    margin-bottom: 35px;
  }
`;
const NFTImgBox = styled.div`
  .nft_img {
    height: 500px;
    width: 100%;
    object-fit: cover;
    border-radius: 6px;
  }

  margin-right: 50px;
  height: 500px;
  width: 100%;
  object-fit: cover;
`;
const NFTInfoBox = styled.div`
  display: flex;
`;

const NFT = styled.div`
  .nft_title {
    font-size: 30px;
    font-style: normal;
    font-weight: 700;

    margin: 90px 0 30px;
  }
`;

export default NFTInfo;
