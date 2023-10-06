import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '@api/apiController';
import styled from 'styled-components';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { ReactComponent as LiveIcon } from '../../assets/icons/LiveIcon.svg';
import { Swiper, SwiperSlide } from 'swiper/react';
import { EffectCoverflow, Pagination } from 'swiper/modules';
import { ArtistDetailType } from '@type/ArtistDetailType';
import { pastFunding } from '@type/PastFundingLists';

interface Props {
  artistData: ArtistDetailType;
  artistId: string;
}

const ArtistDetailInfo: React.FC<Props> = ({ artistData, artistId }) => {
  const [isOnair, setIsOnair] = useState<boolean>(false);
  const [liveId, setLiveId] = useState<number>(0);
  const [pastFundingData, setPastFundingData] = useState<pastFunding[]>();
  const [isScraped, setIsScraped] = useState<boolean>(false);
  const navigate = useNavigate();

  // const videoSlides = artistData.artistVideo.map((video, index) => (
  //   <SwiperSlide key={index}>
  //     <img src={video.videoUrl} alt={`서브 앨범 이미지 ${index + 1}`} className="album_list_img" />
  //   </SwiperSlide>
  // ));
  // console.log('아티스트아이디', artistId);
  // console.log('생방중', isOnair);

  useEffect(() => {
    axios
      .get('/lives/check/active', {
        params: {
          artistId: artistId,
        },
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
        },
      })
      .then((response) => {
        console.log('라이브 여부 조회 성공', response.data.results);
        if (response.data.results.activeLiveId === -1) {
          setIsOnair(false);
        } else {
          setIsOnair(true);
          setLiveId(response.data.results.activeLiveId);
        }
      })
      .catch((error) => {
        console.error('라이브 여부 조회 실패', error);
      });

    axios
      .get(`/artists/${artistId}/projects`)
      .then((response) => {
        console.log('지난 펀딩 목록 5개 조회 성공', response.data.results);
        setPastFundingData(response.data.results);
      })
      .catch((error) => {
        console.error('지난 펀딩 목록 5개 조회 실패', error);
      });
    // 확인 필요################################
    axios
      .get(`/artists/${artistId}/scrap`)
      .then((response) => {
        console.log('스크랩 여부 조회 성공', response.data.results.scraped);
        setIsScraped(response.data.results.scraped);
      })
      .catch((error) => {
        console.error('스크랩 여부 조회 실패', error);
      });
  }, [artistId]);

  const goFundingDetailPage = (type: string, id: number) => {
    navigate(`/${type}-detail/${id}`);
  };

  const scrapOrUnscrap = () => {
    if (isScraped) {
      axios
        .post(`/artists/${artistId}/scrap`)
        .then((response) => {
          console.log('스크랩 성공 ->', isScraped);
        })
        .catch((error) => {
          console.error('스크랩 실패', error);
        });
    } else {
      axios
        .post(`/artists/${artistId}/unscrap`)
        .then((response) => {
          console.log('스크랩취소 성공->', isScraped);
        })
        .catch((error) => {
          console.error('스크랩취소 실패', error);
        });
    }
  };

  const goLivePage = () => {
    // 확인 필요################################################################
    navigate(`/meeting/${liveId}`);
  };

  // const videoSlides = artistData.artistVideo.map((video, index) => (
  //   <SwiperSlide key={index}>
  //     <IframeBox>
  //       <iframe
  //         width="100%"
  //         height="100%"
  //         src={`https://www.youtube.com/embed/${video.videoUrl.split('v=')[1]}`}
  //         title="YouTube video player"
  //         frameBorder="0"
  //         allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
  //         allowFullScreen
  //         className="video_img"
  //       ></iframe>
  //     </IframeBox>
  //   </SwiperSlide>
  // ));

  const pastFundingSlides = pastFundingData?.map((funding, index) => (
    <SwiperSlide key={index}>
      <img
        src={funding.thumbnail}
        alt="서브 앨범 이미지 1"
        className="album_list_img"
        // 확인필요################################
        onClick={() => goFundingDetailPage(funding.type, funding.id)}
      />
    </SwiperSlide>
  ));
  return (
    <ArtistDetailInfoBox>
      {isOnair && (
        <LiveInfoBox onClick={goLivePage}>
          <LiveIcon></LiveIcon>
          <div className="live_info">현재 LIVE 중입니다</div>
        </LiveInfoBox>
      )}

      <ArtistInfoBox>
        <ImgBox>
          <img
            className="profile_img"
            // src="../../src/assets/images/IU_profile_img.png"
            src={artistData.profileImageUrl}
            alt="프로필 이미지"
          ></img>
        </ImgBox>
        <ArtistInfo>
          <TextBox>
            <ArtistName>
              <div className="artist_name">
                {/* 아이유 */}
                {artistData.stageName}
              </div>
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
            </ArtistName>
            <div className="artist_desc">
              아이유는 대한민국의 가수이다. 2008년 EP [Lost And Found]로
              데뷔하여 활동을 시작한 그는 이후 '마쉬멜로우', 임슬옹과 호흡을
              맞춘 '잔소리' 등으로 인기를 얻기 시작했고, 2010년에 발표한 세 번째
              EP [Real]의 타이틀곡 '좋은날'이 종전의 히트를 기록하며 하나의
              신드롬을 형성하기도 했다.
              {/* {artistData.description} */}
            </div>
          </TextBox>

          <ActiveListBox>
            <div className="recent_actions">최근 활동 내역</div>
            <AlbumListBox>
              <Swiper
                effect={'coverflow'}
                grabCursor={true}
                // centeredSlides={true}
                slidesPerView={5}
                spaceBetween={15}
                coverflowEffect={{
                  rotate: 50,
                  stretch: 0,
                  depth: 100,
                  modifier: 1,
                  slideShadows: true,
                }}
                pagination={true}
                modules={[Pagination]}
                className="swiper"
              >
                {/* <SwiperSlide>
                  <img
                    src="../../src/assets/images/sub_album_img1.png"
                    alt="서브 앨범 이미지 1"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide>
                  <img
                    src="../../src/assets/images/sub_album_img2.png"
                    alt="서브 앨범 이미지 2"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide>
                  <img
                    src="../../src/assets/images/sub_album_img3.png"
                    alt="서브 앨범 이미지 3"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide>
                  <img
                    src="../../src/assets/images/sub_album_img4.png"
                    alt="서브 앨범 이미지 4"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide>
                  <img
                    src="../../src/assets/images/sub_album_img2.png"
                    alt="서브 앨범 이미지 2"
                    className="album_list_img"
                  />
                </SwiperSlide> */}
                {pastFundingSlides}
              </Swiper>
            </AlbumListBox>
          </ActiveListBox>
        </ArtistInfo>
      </ArtistInfoBox>
    </ArtistDetailInfoBox>
  );
};

const AlbumListBox = styled.div`
  /* display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  overflow: hidden;
  justify-content: space-around;
  width: 100%;

  .album_list_img {
 
    padding: 10px;
  } */

  overflow: hidden;

  .swiper-wrapper {
    display: -webkit-inline-box;
  }

  .swiper {
    /* width: 60%; */
    /* padding-top: 50px; */
    /* padding-bottom: 50px; */

    display: flex;
    flex-direction: row;
    justify-content: space-around;
  }

  .swiper-slide {
    background-position: center;
    background-size: cover;
    /* height: 250px; */
    /* width: 400px; */
    /* width: auto; */
    /* padding: 15px; */
  }

  .album_list_img {
    display: block;
    /* width: 350px; */
    height: 90px;
    width: 90px;
    object-fit: cover;
    border-radius: 6px;
    /* margin: 15px; */
    cursor: pointer;
  }
`;

const ActiveListBox = styled.div`
  width: 600px;
  .recent_actions {
    color: var(--Black, var(--black-color, #000));

    font-size: 20px;

    font-weight: 700;
    line-height: 25px;
    margin-bottom: 13px;
  }
`;

const LikeBtn = styled.button`
  /* color: #3061b9;
  font-size: 14px;
  font-weight: 700;
  line-height: 25px;

  display: flex;
  flex-direction: row;

  cursor: pointer;
  border: none;
  background: none;

  .likeIcon {
    margin-right: 4px;
    align-self: center;
  } */
`;

const ArtistName = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  .scrap_artist {
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
      margin-right: 4px;
      align-self: center;
    }
  }
  .unscrap_artist {
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
      margin-right: 4px;
      align-self: center;
      /* fill: #0bab4b; */
    }
    .likeIcon path {
      fill: #0bab4b; /* 원하는 색상으로 변경 */
    }
  }
`;

const TextBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 600px;
  height: 146px;

  .artist_name {
    align-self: center;
    font-size: 30px;
    font-weight: 700;
    line-height: normal;
  }
  .artist_desc {
    font-size: 14px;
    font-weight: 500;
    line-height: 25px;
    margin-top: 10px;
    margin-left: 2px;
  }
`;

const ImgBox = styled.div`
  width: 252px;
  height: 252px;
  border-radius: 500px;
  align-self: center;
  margin-right: 17px;

  .profile_img {
    width: 260px;
    height: 260px;
    object-fit: cover;
    border-radius: 999px;
    /* border: solid 10px linear-gradient(to bottom, #a8bee1, #ebf7f2); */
  }
`;

const ArtistInfo = styled.div`
  display: flex;
  flex-direction: column;
  gap: 35px;
  width: 90%;
`;
const ArtistInfoBox = styled.div`
  display: flex;
  gap: 35px;
`;
const LiveInfoBox = styled.div`
  display: flex;
  margin-left: 50px;
  margin-bottom: 20px;
  /* height: 25px; */
  cursor: pointer;

  .live_info {
    color: var(--Main, #3061b9);
    font-size: 16px;
    font-style: normal;
    font-weight: 800;
    line-height: 25px;

    margin-left: 7px;
  }
`;

const ArtistDetailInfoBox = styled.div`
  width: 66%;
`;

export default ArtistDetailInfo;
