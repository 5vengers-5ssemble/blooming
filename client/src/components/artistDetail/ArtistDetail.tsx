import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';
import Myaxios from '@api/apiController';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { ReactComponent as LiveIcon } from '../../assets/icons/LiveIcon.svg';
import { Swiper, SwiperSlide } from 'swiper/react';

import { EffectCoverflow, Pagination, Navigation } from 'swiper/modules';
import { ArtistDetailType } from '@type/ArtistDetailType';
import { ongoingActivity, ongoingConcert } from '@type/OngoingFundingData';
import { searchTrend } from '@type/SearchTrendData';
import { request } from 'http';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement,
} from 'chart.js';
import { Bar, Line } from 'react-chartjs-2';
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement,
);
// const initSearchTrendData: searchTrend[] = [
//   {
//     period: '',
//     ratio: 0,
//   },
// ];

interface Props {
  artistData: ArtistDetailType;
  artistId: string;
}

const ArtistDetail: React.FC<Props> = ({ artistData, artistId }) => {
  const navigate = useNavigate();

  const videoSlides = artistData.artistVideo.map((video, index) => (
    <SwiperSlide key={index}>
      <IframeBox>
        <iframe
          width="100%"
          height="100%"
          src={`https://www.youtube.com/embed/${video.videoUrl.split('v=')[1]}`}
          title="YouTube video player"
          frameBorder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowFullScreen
          className="video_img"
        ></iframe>
      </IframeBox>
    </SwiperSlide>
  ));

  // 네이버 검색어 api
  const [searchTrendData, setSearchTrendData] = useState<searchTrend[]>([]);
  const [ongoingActivityData, setOngoingActivityData] =
    useState<ongoingActivity>();
  const [ongoingConcertData, setOngoingConcertData] =
    useState<ongoingConcert>();

  useEffect(() => {
    const today = new Date();
    const lastMonth = new Date(today);
    const lastYearSameMonth = new Date(today);

    // startDate를 현재 날짜의 이전 달로 설정
    lastMonth.setMonth(today.getMonth() - 1);

    // endDate를 현재 날짜의 1년 전 같은 달로 설정
    lastYearSameMonth.setFullYear(today.getFullYear() - 1);

    function formatDate(date: Date) {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    }

    const requestBody = {
      startDate: formatDate(lastYearSameMonth),
      endDate: formatDate(lastMonth),
      timeUnit: 'month',
      keywordGroups: [
        {
          groupName: '아티스트',
          // keywords: [`${artistData.stageName}`],
          keywords: ['아이유'],
        },
      ],
    };

    axios
      .post('http://localhost:8084/data', requestBody)
      .then((response) => {
        console.log('데이터랩 조회 성공:', response);
        setSearchTrendData(response.data.results[0].data);
      })
      .catch((error) => {
        console.error('데이터랩 조회 실패:', error);
      });

    Myaxios.get(`/artists/${artistId}/activity/ongoing`)
      .then((response) => {
        console.log('아티스트 현재 액티비티 조회 성공:', response.data.results);
        setOngoingActivityData(response.data.results);
      })
      .catch((error) => {
        console.error('아티스트 현재 액티비티 조회 실패:', error);
      });
    Myaxios.get(`/artists/${artistId}/concert/ongoing`)
      .then((response) => {
        console.log('아티스트 현재 콘서트 조회 성공:', response.data.results);
        setOngoingConcertData(response.data.results);
      })
      .catch((error) => {
        console.error('아티스트 현재 콘서트 조회 실패:', error);
      });
  }, [artistData]);
  // console.log('searchTrendData:', searchTrendData);

  // 그래프 관련
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
        display: false,
        labels: {
          font: {
            family: 'Pretendard',
          },
        },
      },
      title: {
        display: true,
        text: '아티스트 검색량 추이 (지난 30일)',
      },
    },
    elements: {
      point: {
        radius: 3,
      },
    },
    scales: {
      y: {
        // display: false,
      },
    },
  };

  const data = {
    labels: searchTrendData.map(
      (data) => data.period.split('-')[0] + '.' + data.period.split('-')[1],
    ),
    datasets: [
      {
        label: '검색량 (ratio)',
        data: searchTrendData.map((data) => data.ratio),
        borderColor: '#3061B9',
        backgroundColor: '#3061B9',
      },
    ],
  };

  const calRemainedDays = (endDate: string) => {
    const currentDate = new Date().getTime();
    const endedAt = new Date(endDate).getTime();
    const daysRemaining = Math.ceil(
      (endedAt - currentDate) / (1000 * 60 * 60 * 24),
    );
    return daysRemaining;
  };

  // console.log('남은날 계산:', calRemainedDays('2023-11-07T00:00:00'));

  const goActivityDetailPage = () => {
    navigate(`/activity-detail/${ongoingActivityData?.activity.id}`);
  };
  const goConcertDetailPage = () => {
    navigate(`/concert-detail/${ongoingConcertData?.concert.id}`);
  };

  return (
    <ArtistDetailBox>
      <ActiveFundingBox>
        <div className="detail_title">현재 진행중인 활동 펀딩</div>
        {ongoingActivityData?.isExists ? (
          <FundingBox onClick={goActivityDetailPage}>
            <FundingImgBox>
              <img
                src={ongoingActivityData.activity.thumbnail}
                alt=""
                className="active_funding_img"
              />
            </FundingImgBox>
            <FundingTextBox>
              <FundingDesc>
                <div className="funding_title">
                  {/* IU 5th Album 'LILAC' */}
                  {ongoingActivityData.activity.title}
                </div>
                <div className="funding_desc">
                  {/* 29살인 아이유가 20대를 마무리하면서 지금까지 자신을 지켜봐 준
                  모든 사람들에게 감사 인사를 전하는 앨범.. */}
                  {ongoingActivityData.activity.introduction}
                </div>
              </FundingDesc>
              <FundingInfo>
                <div className="funding_percent">
                  {Math.ceil(
                    (ongoingActivityData.activity.fundingAmount /
                      ongoingActivityData.activity.targetAmount) *
                      100,
                  )}
                  % 달성
                </div>
                <div className="funding_price">
                  {ongoingActivityData.activity.fundingAmount.toLocaleString()}{' '}
                  원
                </div>
                <div className="funding_remained">
                  남은 펀딩 일수 :{' '}
                  {calRemainedDays(ongoingActivityData.activity.endedAt)}일
                </div>
              </FundingInfo>
            </FundingTextBox>
          </FundingBox>
        ) : (
          <div className="funding_not_exists">
            현재 진행중인 펀딩이 없습니다. 😥
          </div>
        )}
      </ActiveFundingBox>

      <ConcertFundingBox>
        <div className="detail_title">아티스트 콘서트 펀딩</div>
        {ongoingConcertData?.isExists ? (
          <FundingBox onClick={goConcertDetailPage}>
            <FundingImgBox>
              <img
                src={ongoingConcertData.concert.thumbnail}
                alt=""
                className="concert_funding_img"
              />
            </FundingImgBox>
            <FundingTextBox>
              <FundingDesc>
                <div className="funding_title">
                  {/* 2023 아이유 팬콘서트 'I+UN1VER5E' */}
                  {ongoingConcertData.concert.title}
                </div>
                <div className="funding_desc">
                  {/* 아이유와 유애나가 함께한 어제, 오늘, 내일의 모든 순간.
                  데뷔부터 지금까지, 서로가 함께 유영해 온 긴 우주 'I+UN1VER5E'
                  그 마법 같은 순간으로 유애나를 초대합니다. */}
                  {ongoingConcertData.concert.introduction}
                </div>
              </FundingDesc>
              <FundingInfo>
                <div className="funding_percent">
                  {' '}
                  {Math.ceil(
                    (ongoingConcertData.concert.fundingAmount /
                      ongoingConcertData.concert.targetAmount) *
                      100,
                  )}
                  % 달성
                </div>
                <div className="funding_price">
                  {ongoingConcertData.concert.fundingAmount.toLocaleString()} 원
                </div>
                <div className="funding_remained">
                  남은 펀딩 일수 :{' '}
                  {calRemainedDays(ongoingConcertData.concert.endedAt)} 일
                </div>
              </FundingInfo>
            </FundingTextBox>
          </FundingBox>
        ) : (
          <div className="funding_not_exists">
            현재 진행중인 펀딩이 없습니다. 😥
          </div>
        )}
      </ConcertFundingBox>
      <YoutubeBox>
        <div className="detail_title">아티스트 YOUTUBE</div>
        <VideoBox>
          <Swiper
            slidesPerView={2.3}
            spaceBetween={30}
            centeredSlides={true}
            // centerInsufficientSlides={false}
            loop={true}
            navigation={true}
            modules={[Navigation]}
            className="mySwiper"
          >
            {/* <SwiperSlide>
              <IframeBox>
                <iframe
                  width="100%"
                  height="100%"
                  src={`https://www.youtube.com/embed/QOV2UpUWFHM`}
                  title="YouTube video player"
                  frameBorder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowFullScreen
                  className="video_img"
                ></iframe>
              </IframeBox>
            </SwiperSlide>
            <SwiperSlide>
              <IframeBox>
                <iframe
                  width="100%"
                  height="100%"
                  src={`https://www.youtube.com/embed/txtKTTb3U8g`}
                  title="YouTube video player"
                  frameBorder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowFullScreen
                  className="video_img"
                ></iframe>
              </IframeBox>
            </SwiperSlide>
            <SwiperSlide>
              <IframeBox>
                <iframe
                  width="100%"
                  height="100%"
                  src={`https://www.youtube.com/embed/3Hr35Kr2aXA`}
                  title="YouTube video player"
                  frameBorder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowFullScreen
                  className="video_img"
                ></iframe>
              </IframeBox>
            </SwiperSlide>
            <SwiperSlide>
              <IframeBox>
                <iframe
                  width="100%"
                  height="100%"
                  src={`https://www.youtube.com/embed/d9IxdwEFk1c`}
                  title="YouTube video player"
                  frameBorder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowFullScreen
                  className="video_img"
                ></iframe>
              </IframeBox>
            </SwiperSlide>
            <SwiperSlide>
              <IframeBox>
                <iframe
                  width="100%"
                  height="100%"
                  src={`https://www.youtube.com/embed/D1PvIWdJ8xo`}
                  title="YouTube video player"
                  frameBorder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowFullScreen
                  className="video_img"
                ></iframe>
              </IframeBox>
            </SwiperSlide> */}
            {videoSlides}
          </Swiper>
        </VideoBox>
      </YoutubeBox>
      <SearchGraphBox>
        <div className="detail_title">검색결과 분석</div>
        <Line options={options} data={data} />
      </SearchGraphBox>
    </ArtistDetailBox>
  );
};

const SearchGraphBox = styled.div``;
const IframeBox = styled.div``;
const VideoBox = styled.div`
  /* width: 100%; */
  /* display: flex; */
  /* justify-content: space-around; */

  /* .swiper-wrapper {
    display: -webkit-inline-box;
  } */

  .swiper-slide {
    background-position: center;
    background-size: cover;
    height: 250px;
    /* width: 400px; */
    /* width: auto; */
    /* border-radius: 6px; */
  }

  .video_img {
    display: block;
    /* width: 350px; */
    height: 250px;
    object-fit: cover;
    border-radius: 6px;
  }
`;
const YoutubeBox = styled.div``;
const ConcertFundingBox = styled.div``;
const FundingInfo = styled.div`
  text-align: right;
  width: 313px;
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
  }
`;
const FundingDesc = styled.div`
  margin-left: 25px;
  height: 228px;
  .funding_title {
    font-size: 20px;
    font-weight: 800;
    line-height: 25px;
    margin-top: 25px;
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
  width: 550px;
  .concert_funding_img {
    height: 350px;
    width: 550px;
    object-fit: cover;
    border-radius: 6px;
  }

  .active_funding_img {
    height: 350px;
    width: 550px;
    object-fit: cover;
    border-radius: 6px;
  }
`;
const FundingBox = styled.div`
  display: flex;
  height: 350px;
  width: 100%;
  cursor: pointer;
`;
const ActiveFundingBox = styled.div``;
const ArtistDetailBox = styled.div`
  .funding_not_exists {
    font-size: 16px;
    font-weight: 600;
    margin-left: 15px;
  }

  .detail_title {
    font-size: 25px;
    font-weight: 700;
    margin-bottom: 24px;
    margin-top: 100px;
  }
`;
export default ArtistDetail;
