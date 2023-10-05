import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import axios from '@api/apiController';
import { MainTitle } from '@style/common';
import NFTInfo from '@components/NFTDetailPage/NFTInfo';
import NFTSales from '@components/NFTDetailPage/NFTSales';
import { nftDetailType } from '@type/NftDetailType';
import NavBar from '@components/common/NavBar';
import styled from 'styled-components';

const initData: nftDetailType = {
  id: 0,
  title: '',
  thumbnailUrl: '',
  description: '',
  artist: {
    id: 0,
    profileImg: '',
    name: '',
    desc: '',
    youtubeUrl: '',
    fanCafeUrl: '',
    snsUrl: '',
  },
  saleCount: 0,
  nftSale: {
    totalNftCount: 0,
    soldNftCount: 0,
    totalNftAmount: 0,
    soldNftAmount: 0,
  },
  salePrice: 0,
  purchaseStart: '',
  purchaseEnd: '',
  contractAddress: '',
};

const NFTDetail = () => {
  const [nftDetailData, setNftDetailData] = useState<nftDetailType>(initData);
  const { membershipId } = useParams();

  useEffect(() => {
    //nft 상세 조회
    axios
      .get(`/memberships/${membershipId}`)
      .then((response) => {
        console.log('nft 상세 요청 성공:', response);
        setNftDetailData(response.data.results);
      })
      .catch((error) => {
        console.error('nft 상세요청 실패:', error);
      });
  }, []);

  return (
    <div>
      <NavBar></NavBar>
      <NftDetailBox>
        <br />
        <br />
        <br />
        <br />
        <MainTitle>
          NFT<div className="dot"></div>
        </MainTitle>
        <NFTInfo nftDetailData={nftDetailData}></NFTInfo>

        <NFTSales nftDetailData={nftDetailData}></NFTSales>
      </NftDetailBox>
    </div>
  );
};

const NftDetailBox = styled.div`
  margin: 0 -80px;
`;
export default NFTDetail;
