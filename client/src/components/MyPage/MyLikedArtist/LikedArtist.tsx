import axiosTemp from '@api/apiControllerTemp';
import { useEffect, useState } from 'react';
import Loading from '@components/Animation/Loading';
import { MainTitle } from '@style/common';
import styled from 'styled-components';
import { Frame } from '../MyMembershipInfo/MembershipInterface';
import { ArtistInfo } from '@type/FundingInfo';
import { LikedList } from './LikedList';

const LikedArtist = () => {
  const [likedata, setLikeData] = useState<ArtistInfo[]>();

  useEffect(() => {
    axiosTemp.get('/liked-artist').then((res) => {
      console.log(res.data);
      setLikeData(res.data);
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
      <LikedList datas={likedata} />
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
