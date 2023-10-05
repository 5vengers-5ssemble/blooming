import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Pagination, Navigation } from 'swiper/modules';
import axios from '@api/apiController';

import { bestLive } from '@type/BestLiveData';
import SeeMoreFundingBtn from '@components/Button/SeeMoreFundingBtn';
import PopStreamCard from './PopStreamCard';

const PopStreamBox = () => {
  const [bestStreams, setBestStreams] = useState<bestLive[]>([]);

  useEffect(() => {
    axios
      .get('lives/best', {
        params: {
          numberOfLives: 5,
        },
      })
      .then((response) => {
        console.log('인기 라이브 조회 성공', response.data.results);
        setBestStreams(response.data.results);
      })
      .catch((error) => {
        console.error('인기 라이브 조회 실패', error);
      });
  }, []);

  const navigate = useNavigate();
  const goDetailPage = (index: number) => {
    navigate(`live/${bestStreams[index].id}`);
  };

  return (
    <StreamBox>
      <div className="box_title">인기 스트리밍</div>
      <Swiper
        slidesPerView={2}
        spaceBetween={30}
        centeredSlides={true}
        loop={true}
        navigation={true}
        modules={[Navigation]}
        className="mySwiper"
      >
        {bestStreams.map((stream, index) => (
          <SwiperSlide
            key={index}
            onClick={() => {
              goDetailPage(index);
            }}
          >
            <PopStreamCard bestStreamData={stream} />
          </SwiperSlide>
        ))}
      </Swiper>
      <SeeMoreFundingBtn btnTitle="스트리밍" type="live" />
    </StreamBox>
  );
};

const StreamBox = styled.div`
  margin: 0 -80px;
  .box_title {
    overflow: hidden;
    color: var(--Black, var(--black-color, #000));
    text-align: center;
    text-overflow: ellipsis;

    font-size: 30px;

    font-weight: 700;
    line-height: 40px;
    margin-bottom: 40px;
  }
`;

export default PopStreamBox;
