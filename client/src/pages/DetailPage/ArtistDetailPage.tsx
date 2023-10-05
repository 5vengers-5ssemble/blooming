import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from '@api/apiController';
import styled from 'styled-components';

import ArtistDetail from '@components/artistDetail/ArtistDetail';
import ArtistDetailInfo from '@components/artistDetail/ArtistDetailInfo';
import ArtistGraph from '@components/artistDetail/ArtistGraph';
import ArtistNFT from '@components/artistDetail/ArtistNFT';
import { ArtistDetailType } from '@type/ArtistDetailType';
import { nftDetailType } from '@type/NftDetailType';
import NavBar from '@components/common/NavBar';
import { MainTitle } from '@style/common';

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

const initData2: ArtistDetailType = {
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
  const [artistData, setArtistData] = useState<ArtistDetailType>(initData2);
  const [membershipId, setMembershipId] = useState<string>('');
  const [nftDetailData, setNftDetailData] = useState<nftDetailType>(initData);

  const { artistId } = useParams();

  useEffect(() => {
    //아티스트 상세 요청
    axios
      .get(`/artists/${artistId}`)
      .then((response) => {
        console.log('아티스트 상세 요청 성공:', response);
        setArtistData(response.data.results);
      })
      .catch((error) => {
        console.error('아티스트 상세 요청 실패:', error);
      });
    //아티스트id 로 멤버쉽id 조회
    axios
      .get(`/artists/${artistId}/memberships/ongoing`)
      .then((response) => {
        console.log('멤버쉽id 조회 성공:', response);
        setMembershipId(response.data.results.membershipId);
      })
      .catch((error) => {
        console.error('멤버쉽id 조회 실패:', error);
      });
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
    <>
      <NavBar></NavBar>
      <br />
      <br />
      <br />
      <br />
      <MainTitle>
        아티스트<div className="dot"></div>
      </MainTitle>
      <br />
      <br />
      <br />
      <ArtistDetailBox1>
        <ArtistDetailInfo
          artistData={artistData}
          artistId={artistId || ''}
        ></ArtistDetailInfo>
        <ArtistGraph
          artistId={artistId || ''}
          membershipId={membershipId}
        ></ArtistGraph>
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

        <ArtistNFT nftDetailData={nftDetailData}></ArtistNFT>
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
