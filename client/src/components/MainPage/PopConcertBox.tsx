import React from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Pagination, Navigation } from 'swiper/modules';
import PopConcertCard from './PopConcertCard';
import SeeMoreFundingBtn from '@components/Button/SeeMoreFundingBtn';

const PopConcertBox = () => {
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
        <SwiperSlide>
          <PopConcertCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopConcertCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopConcertCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopConcertCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopConcertCard />
        </SwiperSlide>
      </Swiper>
      <SeeMoreFundingBtn btnTitle="펀딩" />
    </ConcertBox>
  );
};

const ConcertBox = styled.div`
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
