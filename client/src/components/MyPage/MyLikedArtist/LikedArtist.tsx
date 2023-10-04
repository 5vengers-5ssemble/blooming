import axios from '@api/apiController';
import { useEffect, useState } from 'react';
import Loading from '@components/Animation/Loading';
import { MainTitle } from '@style/common';
import styled from 'styled-components';
import { Frame } from '../MyMembershipInfo/MembershipInterface';
import { ArtistInfo } from '@type/FundingInfo';
import { LikedList } from './LikedList';
import NoSearchResults from '@components/Search/NoSearchResults';

const LikedArtist = () => {
  const [likedata, setLikeData] = useState<ArtistInfo[]>();

  useEffect(() => {
    axios.get('/members/me/scrap-artists').then((res) => {
      setLikeData(res.data.results);
    });
  }, []);

  if (!likedata) {
    return (
      <>
        <Loading />
      </>
    );
  }

  return (
    <LiveFrame>
      <LiveTitle>
        내가 찜한 아티스트<div className="dot"></div>
      </LiveTitle>
      {likedata.length > 0 ? (
        <LikedList datas={likedata} />
      ) : (
        <NoSearchResults />
      )}
    </LiveFrame>
  );
};

const LiveFrame = styled(Frame)`
  padding: 70px 40px 0 60px;
`;
const LiveTitle = styled(MainTitle)`
  font-size: 30px;
`;
export default LikedArtist;
