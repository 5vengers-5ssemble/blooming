import React from 'react';
import styled from 'styled-components';

import ArtistDetail from '@components/artistDetail/ArtistDetail';
import ArtistDetailInfo from '@components/artistDetail/ArtistDetailInfo';
import ArtistGraph from '@components/artistDetail/ArtistGraph';
import ArtistNFT from '@components/artistDetail/ArtistNFT';

const ArtistDetailPage = () => {
  return (
    <>
      <ArtistDetailBox1>
        <ArtistDetailInfo></ArtistDetailInfo>
        <ArtistDetail></ArtistDetail>
      </ArtistDetailBox1>
      <ArtistDetailBox2>
        <br />
        <br />
        <ArtistGraph></ArtistGraph>
        <br />
        <br />
        <br />
        <br />
        <br />
        <ArtistNFT></ArtistNFT>
      </ArtistDetailBox2>
    </>
  );
};

const ArtistDetailBox2 = styled.div`
  float: right;
  width: 20%;
`;

const ArtistDetailBox1 = styled.div`
  float: left;
  width: 75%;
`;

export default ArtistDetailPage;
