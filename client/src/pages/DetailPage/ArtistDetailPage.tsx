import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from '@api/apiController';
import styled from 'styled-components';

import ArtistDetail from '@components/artistDetail/ArtistDetail';
import ArtistDetailInfo from '@components/artistDetail/ArtistDetailInfo';
import ArtistGraph from '@components/artistDetail/ArtistGraph';
import ArtistNFT from '@components/artistDetail/ArtistNFT';
import { ArtistDetailType } from '@type/ArtistDetailType';

const initData: ArtistDetailType = {
  stageName: '',
  agency: '',
  description: '',
  profileImageUrl: '',
  youtubeUrl: '',
  fanCafeUrl: '',
  snsUrl: '',
  artistVideo: [],
};

const ArtistDetailPage = () => {
  const [artistData, setArtistData] = useState<ArtistDetailType>(initData);
  const { artistId } = useParams();

  useEffect(() => {
    axios
      .get(`/artists/${artistId}`)
      .then((response) => {
        console.log('아티스트 상세 요청 성공:', response);
        setArtistData(response.data.results);
      })
      .catch((error) => {
        console.error('아티스트 상세 요청 실패:', error);
      });
  }, []);

  return (
    <>
      <ArtistDetailBox1>
        <ArtistDetailInfo
          artistData={artistData}
          artistId={artistId || ''}
        ></ArtistDetailInfo>
        <ArtistGraph artistId={artistId || ''}></ArtistGraph>
        <ArtistDetail
          artistData={artistData}
          artistId={artistId || ''}
        ></ArtistDetail>
        <br />
        <br />
        <br />
        <br />
        <br />
      </ArtistDetailBox1>
      <ArtistDetailBox2>
        <br />
        <br />
        <br />

        <ArtistNFT></ArtistNFT>
      </ArtistDetailBox2>
    </>
  );
};

const ArtistDetailBox2 = styled.div`
  /* width: 20%; */
  float: right;
  margin-right: 40px;
`;

const ArtistDetailBox1 = styled.div`
  float: left;
  width: 90%;
`;

export default ArtistDetailPage;
