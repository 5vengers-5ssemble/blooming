import React from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Pagination, Navigation } from 'swiper/modules';

import SeeMoreFundingBtn from '@components/Button/SeeMoreFundingBtn';
import PopStreamCard from './PopStreamCard';

const PopStreamBox = () => {
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
        <SwiperSlide>
          <PopStreamCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopStreamCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopStreamCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopStreamCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopStreamCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopStreamCard />
        </SwiperSlide>
      </Swiper>
      <SeeMoreFundingBtn btnTitle="스트리밍" />
    </StreamBox>
  );
};

const StreamBox = styled.div`
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
