import React from 'react';
import ArtistInfo from '@components/fundingDetail/ArtistInfo';
import Funding from '@components/fundingDetail/Funding';
import FundingDetail from '@components/fundingDetail/FundingDetail';

const MainPage = () => {
  return (
    <>
      <br />
      <br />
      <ArtistInfo></ArtistInfo>
      <br />
      <br />
      <Funding></Funding>
      <br />
      <br />
      <FundingDetail></FundingDetail>
      <br />
      <br />
    </>
  );
};

export default MainPage;
