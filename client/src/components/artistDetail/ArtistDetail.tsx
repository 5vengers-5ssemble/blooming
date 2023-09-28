import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { ReactComponent as LiveIcon } from '../../assets/icons/LiveIcon.svg';
import { Swiper, SwiperSlide } from 'swiper/react';

import { EffectCoverflow, Pagination } from 'swiper/modules';
import { ArtistDetailType } from '@type/ArtistDetailType';

interface Props {
  artistData: ArtistDetailType;
}

const ArtistDetail: React.FC<Props> = ({ artistData }) => {
  // useEffect(() => {
  //   // Google Trends 스크립트를 동적으로 추가
  //   const script = document.createElement('script');
  //   script.src =
  //     'https://ssl.gstatic.com/trends_nrtr/3461_RC01/embed_loader.js';
  //   script.async = true;
  //   document.head.appendChild(script);

  //   script.onload = () => {
  //     // Google Trends 그래프를 그리기 위한 스크립트
  //     const trendsScript = document.createElement('script');
  //     trendsScript.type = 'text/javascript';
  //     trendsScript.innerHTML = `
  //       trends.embed.renderExploreWidget("TIMESERIES", {"comparisonItem":[{"keyword":"김재환","geo":"KR","time":"now 1-d"}],"category":0,"property":""}, {"exploreQuery":"date=now%201-d&geo=KR&q=%EA%B9%80%EC%9E%AC%ED%99%98&hl=ko","guestPath":"https://trends.google.co.kr:443/trends/embed/"});
  //     `;
  //     document.body.appendChild(trendsScript);
  //   };
  // }, []);

  // const videoSlides = artistData.artistVideo.map((video, index) => (
  //   <SwiperSlide key={index}>
  //     <iframe
  //       width="100%"
  //       height="100%"
  //       src={video.videoUrl}
  //       title="YouTube video player"
  //       frameBorder="0"
  //       allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
  //       allowFullScreen
  //       className="video_img"
  //     ></iframe>
  //   </SwiperSlide>
  // ));

  return (
    <ArtistDetailBox>
      <ActiveFundingBox>
        <div className="detail_title">현재 진행중인 활동 펀딩</div>
        <FundingBox>
          <FundingImgBox>
            <img
              src="../../src/assets/images/active_funding_img.png"
              alt=""
              className="active_funding_img"
            />
          </FundingImgBox>
          <FundingTextBox>
            <FundingDesc>
              <div className="funding_title">IU 5th Album 'LILAC'</div>
              <div className="funding_desc">
                29살인 아이유가 20대를 마무리하면서 지금까지 자신을 지켜봐 준
                모든 사람들에게 감사 인사를 전하는 앨범..
              </div>
            </FundingDesc>
            <FundingInfo>
              <div className="funding_percent">1,204% 달성</div>
              <div className="funding_price">12,042,200 원</div>
              <div className="funding_remained">남은 펀딩 일수 : 20 일</div>
            </FundingInfo>
          </FundingTextBox>
        </FundingBox>
      </ActiveFundingBox>

      <ConcertFundingBox>
        <div className="detail_title">아티스트 콘서트 펀딩</div>
        <FundingBox>
          <FundingImgBox>
            <img
              src="../../src/assets/images/concert_funding_img.jfif"
              alt=""
              className="concert_funding_img"
            />
          </FundingImgBox>
          <FundingTextBox>
            <FundingDesc>
              <div className="funding_title">
                2023 아이유 팬콘서트 'I+UN1VER5E'
              </div>
              <div className="funding_desc">
                아이유와 유애나가 함께한 어제, 오늘, 내일의 모든 순간. 데뷔부터
                지금까지, 서로가 함께 유영해 온 긴 우주 'I+UN1VER5E' 그 마법
                같은 순간으로 유애나를 초대합니다.
              </div>
            </FundingDesc>
            <FundingInfo>
              <div className="funding_percent">1,204% 달성</div>
              <div className="funding_price">12,042,200 원</div>
              <div className="funding_remained">남은 펀딩 일수 : 20 일</div>
            </FundingInfo>
          </FundingTextBox>
        </FundingBox>
      </ConcertFundingBox>
      <YoutubeBox>
        <div className="detail_title">아티스트 YOUTUBE</div>
        <VideoBox>
          {/* <Swiper
            effect={'coverflow'}
            grabCursor={true}
            centeredSlides={true}
            slidesPerView={'auto'}
            // spaceBetween={15}
            coverflowEffect={{
              rotate: 50,
              stretch: 0,
              depth: 100,
              modifier: 1,
              slideShadows: true,
            }}
            pagination={true}
            modules={[EffectCoverflow, Pagination]} */}
          <Swiper
            slidesPerView={2}
            spaceBetween={30}
            grabCursor={true}
            pagination={{
              clickable: true,
            }}
            modules={[Pagination]}
            className="swiper"
          >
            {/* <SwiperSlide>
              <iframe
                width="100%"
                height="100%"
                src="https://www.youtube.com/embed/0-q1KafFCLU?si=psp1HgdtGSmXiO8k"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
                className="video_img"
              ></iframe>
            </SwiperSlide>
            <SwiperSlide>
              <iframe
                width="100%"
                height="100%"
                src="https://www.youtube.com/embed/D1PvIWdJ8xo?si=_uDdB9Qbbpd1ymnZ"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
                className="video_img"
              ></iframe>
            </SwiperSlide>
            <SwiperSlide>
              <iframe
                width="100%"
                height="100%"
                src="https://www.youtube.com/embed/sIs7Ur8TUUA?si=CUz5ikZP9Mrt1Gf8"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
                className="video_img"
              ></iframe>
            </SwiperSlide>
            <SwiperSlide>
              <iframe
                width="100%"
                height="100%"
                src="https://www.youtube.com/embed/3iM_06QeZi8?si=s5tMtByENQYgj0I6"
                title="YouTube video player"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
                className="video_img"
              ></iframe>
            </SwiperSlide>
            <SwiperSlide>
              <iframe
                width="100%"
                height="100%"
                src="https://www.youtube.com/embed/ebu2cxRXU-I?si=Zed_CCsWeqZE_8rV"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
                className="video_img"
              ></iframe>
            </SwiperSlide> */}
            {/* {videoSlides} */}
          </Swiper>
        </VideoBox>
      </YoutubeBox>
      <SearchGraphBox>
        <div className="detail_title">검색결과 분석</div>
        <div id="trends-widget"></div>
      </SearchGraphBox>
    </ArtistDetailBox>
  );
};

const SearchGraphBox = styled.div``;
const VideoBox = styled.div`
  /* width: 100%; */
  /* display: flex; */
  /* justify-content: space-around; */
  overflow: hidden;

  .swiper-wrapper {
    display: -webkit-inline-box;
  }

  .swiper {
    width: 60%;
    /* padding-top: 50px; */
    /* padding-bottom: 50px; */

    display: flex;
    flex-direction: row;
  }

  .swiper-slide {
    background-position: center;
    background-size: cover;
    height: 250px;
    /* width: 400px; */
    /* width: auto; */
  }

  .video_img {
    display: block;
    /* width: 350px; */
    height: 250px;
    object-fit: cover;
  }
`;
const YoutubeBox = styled.div``;
const ConcertFundingBox = styled.div``;
const FundingInfo = styled.div`
  text-align: right;
  .funding_remained {
    font-size: 14px;
    font-weight: 600;
    line-height: 25px;
    margin-top: 16px;
  }
  .funding_price {
    font-size: 14px;
    font-weight: 700;
    line-height: 17px; /* 121.429% */
    margin-top: 7px;
  }

  .funding_percent {
    font-size: 20px;
    font-weight: 800;
    line-height: 17px;
    margin-top: 115px;
  }
`;
const FundingDesc = styled.div`
  margin-left: 25px;
  .funding_title {
    font-size: 20px;
    font-weight: 800;
    line-height: 25px;
    margin-top: 38px;
  }

  .funding_desc {
    margin-top: 15px;
    font-size: 14px;
    font-weight: 500;
    line-height: 20px;
  }
`;
const FundingTextBox = styled.div``;
const FundingImgBox = styled.div`
  .concert_funding_img {
    height: 350px;
    height: 100%;
    border-radius: 6px;
  }

  .active_funding_img {
    height: 350px;
    width: 100%;
    border-radius: 6px;
  }
`;
const FundingBox = styled.div`
  display: flex;
`;
const ActiveFundingBox = styled.div``;
const ArtistDetailBox = styled.div`
  .detail_title {
    font-size: 25px;
    font-weight: 700;
    margin-bottom: 24px;
    margin-top: 100px;
  }
`;
export default ArtistDetail;
