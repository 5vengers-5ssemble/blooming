import React from 'react';
import styled from 'styled-components';
import { onairLive } from '@type/BestLiveData';

interface Props {
  bestStreamData: onairLive;
}

const PopStreamCard: React.FC<Props> = ({ bestStreamData }) => {
  return (
    <StreamCard>
      <img
        // src="../../src/assets/images/stream_thumbnail.png"
        src={bestStreamData.thumbnailUrl}
        alt=""
        className="stream_img"
      />
      <StreamInfo>
        <img
          // src="../../src/assets/images/imase_profile_img.png"
          src={bestStreamData.artist.profileImageUrl}
          alt=""
          className="stream_artist_img"
        />
        <StreamText>
          <div className="stream_title">
            {/* 팬 여러분 안녕하세요 오랜만의 미니 팬미팅입니다. */}
            {bestStreamData.title}
          </div>
          <div className="stream_artist">
            {/* 이마세 Imase */}
            {bestStreamData.artist.stageName}
          </div>
        </StreamText>
      </StreamInfo>
    </StreamCard>
  );
};

const StreamText = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;

  .stream_title {
    font-size: 15px;
    font-weight: 700;
    line-height: 40px; /* 100% */
  }

  .stream_artist {
    color: var(--Charcoal, #666);
    font-size: 14px;
    font-weight: 500;
    line-height: 17px; /* 121.429% */
  }
`;
const StreamInfo = styled.div`
  display: flex;
  margin-top: 10px;
  margin-left: 10px;

  .stream_artist_img {
    width: 40px;
    height: 40px;
    border-radius: 999px;
    margin-right: 24px;
  }
`;

const StreamCard = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  /* border: 0.5px solid rgba(0, 0, 0, 0.15);
  background: #fff; */

  height: 340px;
  width: 450px;

  .stream_img {
    object-fit: cover;
    height: 300px;
    width: 100%;
    border-radius: 6px;
  }
`;

export default PopStreamCard;
