import React, { useEffect, useState } from 'react';
import axios from '@api/apiController';
import styled from 'styled-components';

import ArtistDetail from '@components/artistDetail/ArtistDetail';
import ArtistDetailInfo from '@components/artistDetail/ArtistDetailInfo';
import ArtistGraph from '@components/artistDetail/ArtistGraph';
import ArtistNFT from '@components/artistDetail/ArtistNFT';
import { ArtistRequestInfo } from '@type/ArtistRequest';

const initData: ArtistRequestInfo = {
  stageName: '',
  agency: '',
  description: '',
  profileImageUrl: '',
  youtubeUrl: '',
  fanCafeUrl: '',
  snsUrl: '',
};

const ArtistDetailPage = () => {
  const [data, setData] = useState<ArtistRequestInfo>(initData);

  useEffect(() => {
    axios
      .get('/artists', {})
      .then((response) => {
        console.log('요청 성공:', response);
        setData(response.data);
      })
      .catch((error) => {
        console.error('요청 실패:', error);
      });
  }, []);

  return (
    <>
      <ArtistDetailBox1>
        <ArtistDetailInfo></ArtistDetailInfo>
        <ArtistGraph></ArtistGraph>
        <ArtistDetail></ArtistDetail>
      </ArtistDetailBox1>
      <ArtistDetailBox2>
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
