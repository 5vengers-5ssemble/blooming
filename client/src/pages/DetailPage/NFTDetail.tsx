import React, { useEffect, useState } from 'react';
import axios from '@api/apiController';
import { MainTitle } from '@style/common';
import NFTInfo from '@components/NFTDetailPage/NFTInfo';
import NFTSales from '@components/NFTDetailPage/NFTSales';

const NFTDetail = () => {
  return (
    <>
      <MainTitle>
        NFT 구입<div className="dot"></div>
      </MainTitle>
      <NFTInfo></NFTInfo>

      <NFTSales></NFTSales>
    </>
  );
};

export default NFTDetail;
