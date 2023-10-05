import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import axios from '@api/apiController';

import { Pagination, Navigation } from 'swiper/modules';
import { useNavigate } from 'react-router-dom';
import PopConcertCard from './PopConcertCard';
import SeeMoreFundingBtn from '@components/Button/SeeMoreFundingBtn';
import { ProcessInfo } from '@type/ProcessInfo';

const PopConcertBox = () => {
  const [bestConcerts, setBestConcerts] = useState<ProcessInfo[]>([]);

  const navigate = useNavigate();
  const goDetailPage = (index: number) => {
    navigate(`concert-detail/${bestConcerts[index].id}`);
  };

  useEffect(() => {
    axios
      .get('/concerts/best')
      .then((response) => {
        console.log('인기 콘서트 펀딩 조회 성공', response.data.results);
        setBestConcerts(response.data.results);
      })
      .catch((error) => {
        console.error('인기 콘서트 펀딩 조회 실패', error);
      });
  }, []);

  return (
    <ConcertBox>
      <div className="box_title">인기 콘서트 펀딩</div>

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
        {bestConcerts.map((concert, index) => (
          <SwiperSlide key={index} onClick={() => goDetailPage(index)}>
            <PopConcertCard bestConcertData={concert} />
          </SwiperSlide>
        ))}
      </Swiper>
      <SeeMoreFundingBtn btnTitle="펀딩" type="concert" />
    </ConcertBox>
  );
};

const ConcertBox = styled.div`
  margin: 0 -80px;
  .mySwiper {
    display: flex;
    justify-content: center;
  }
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

export default PopConcertBox;
