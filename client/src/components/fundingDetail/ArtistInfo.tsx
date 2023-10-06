import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from '@api/apiController';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { artist } from '@type/ConcertDetail';

interface Props {
  artistData: artist;
}

const ArtistInfo: React.FC<Props> = ({ artistData }) => {
  const [isScraped, setIsScraped] = useState<boolean>(true);

  useEffect(() => {
    // 확인 필요################################
    axios
      .get(`/artists/${artistData.id}/scrap`)
      .then((response) => {
        console.log('스크랩 여부 조회 성공', response.data.results.scraped);
        setIsScraped(response.data.results.scraped);
      })
      .catch((error) => {
        console.error('스크랩 여부 조회 실패', error);
      });
  }, [artistData]);

  const scrapOrUnscrap = () => {
    if (isScraped) {
      axios
        .post(`/artists/${artistData.id}/scrap`)
        .then((response) => {
          console.log('스크랩 성공 ->', isScraped);
        })
        .catch((error) => {
          console.error('스크랩 실패', error);
        });
    } else {
      axios
        .post(`/artists/${artistData.id}/unscrap`)
        .then((response) => {
          console.log('스크랩취소 성공->', isScraped);
        })
        .catch((error) => {
          console.error('스크랩취소 실패', error);
        });
    }
  };
  return (
    <ArtistInfoBox>
      <img
        className="profileImg"
        // src="../../src/assets/images/kimjaehwan_profile.jfif"
        src={artistData.profileImg}
      ></img>
      <ArtistInfoText>
        <TextBox>
          <div className="artist_name">{artistData.name}</div>
          {/* <div className="artist_name">김재환</div> */}
          <div className="artist_desc">
            {/* 김재환은 대한민국의 가수로, 대한민국의 남자 아이돌 그룹 워너원의
            멤버였다. 2017년 Mnet 서 방영한 《프로듀스 101 시즌2》 을 통해 그룹
            워너원으로 2017년 8월 7일 데뷔하였다. 2019년 1월 24일부터 1월
            27일까지 고척스카이돔에서 열린 콘서트, 2019 Wanna One Concert
            [Therefore]을 끝으로 워너원의 활동을 마무리 지었다. 이후
            스윙엔터테인먼트와 계약하며 다양한 분야에서 솔로 활동을 이어나가고
            있다. */}
            {artistData.desc}
          </div>
        </TextBox>
        {/* <LikeBtn onClick={handleScrap}>
          <LikeIcon className="likeIcon"></LikeIcon>
          <div>관심 아티스트 등록</div>
        </LikeBtn> */}

        {isScraped ? (
          <LikeBtn className="unscrap_artist" onClick={scrapOrUnscrap}>
            <LikeIcon className="likeIcon"></LikeIcon>
            <div>내 관심 아티스트</div>
          </LikeBtn>
        ) : (
          <LikeBtn className="scrap_artist" onClick={scrapOrUnscrap}>
            <LikeIcon className="likeIcon"></LikeIcon>
            <div>관심 아티스트 등록</div>
          </LikeBtn>
        )}
      </ArtistInfoText>
    </ArtistInfoBox>
  );
};

const LikeBtn = styled.button``;

const ArtistInfoText = styled.div`
  .scrap_artist {
    margin-top: 28px;
    color: #3061b9;
    font-size: 14px;
    font-weight: 700;
    line-height: 25px;

    display: flex;
    flex-direction: row;

    cursor: pointer;
    border: none;
    background: none;

    .likeIcon {
      align-self: center;
      margin-right: 4px;
    }
  }

  .unscrap_artist {
    margin-top: 28px;
    color: #0bab4b;
    font-size: 14px;
    font-weight: 700;
    line-height: 25px;

    display: flex;
    flex-direction: row;

    cursor: pointer;
    border: none;
    background: none;

    .likeIcon {
      align-self: center;
      margin-right: 4px;
    }

    .likeIcon path {
      fill: #0bab4b;
    }
  }
`;

const ArtistInfoBox = styled.div`
  display: flex;
  height: 220px;
  .profileImg {
    width: 200px;
    height: 200px;
    border-radius: 500px;
    margin-right: 54px;
    /* height: 100px; */
  }
`;

const TextBox = styled.div`
  height: 118px;
  width: 860px;

  .artist_name {
    height: 36px;
    font-size: 30px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
  }
  .artist_desc {
    font-size: 14px;
    font-style: normal;
    font-weight: 500;
    line-height: 25px;
    margin-top: 7px;
    margin-left: 2px;
  }
`;

export default ArtistInfo;
