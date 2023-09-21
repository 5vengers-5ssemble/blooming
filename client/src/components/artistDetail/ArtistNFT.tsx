import React from 'react';
import styled from 'styled-components';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { ReactComponent as LiveIcon } from '../../assets/icons/LiveIcon.svg';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Navigation, Pagination } from 'swiper/modules';

const ArtistNFT = () => {
  return (
    <NFTInfoBox>
      <NFTInfo>
        <div className="now_nft">NOW NFT</div>
        <img src="src/assets/images/NFT_img.png" alt="" className="nft_img" />
        <div className="nft_name">The Golden Hour: Under the Or..</div>
      </NFTInfo>
      <BuyNFT>
        <div className="buying_number">125명 구매 중</div>
        <BuyNFTBtn>구매</BuyNFTBtn>
      </BuyNFT>
    </NFTInfoBox>
  );
};
const BuyNFTBtn = styled.button`
  /* width: 127px; */
  width: 60%;
  height: 38px;

  border-radius: 6px;
  border: none;
  background: var(--Main, #3061b9);

  flex-shrink: 0;

  color: var(--White, #fdfdfd);
  text-align: center;
  font-size: 14px;
  font-weight: 800;
  line-height: 38px;
  letter-spacing: 1.6px;
`;
const BuyNFT = styled.div`
  display: flex;
  flex-direction: column;
  float: right;
  align-items: flex-end;
  /* width: 285px; */
  width: 50%;
  .buying_number {
    color: var(--Black, var(--black-color, #000));
    font-size: 13px;
    font-weight: 600;
    line-height: 25px;

    margin-bottom: 4px;
  }
`;
const NFTInfo = styled.div`
  .nft_img {
    margin-bottom: 6px;
    width: 100%;
  }
  .nft_name {
    color: var(--Black, var(--black-color, #000));
    font-size: 14px;
    font-weight: 600;
    line-height: 25px;

    margin-bottom: 30px;
    /* width: 285px; */
    width: 100%;
    text-align: center;
  }
  .now_nft {
    /* width: 127px;*/
    width: 50%;
    height: 30px;
    flex-shrink: 0;
    margin-bottom: 17px;

    border-radius: 6px;
    border: 2px solid var(--Main, #3061b9);

    color: var(--Main, #3061b9);
    text-align: center;
    font-size: 14px;
    font-weight: 800;
    line-height: 30px;
  }
`;
const NFTInfoBox = styled.div`
  height: 380px;
  padding: 20px 15px;
  box-shadow: 0px 4px 10px 0px rgba(48, 97, 185, 0.1);
`;
export default ArtistNFT;
