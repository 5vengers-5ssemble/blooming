import React from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Pagination, Navigation } from 'swiper/modules';

import PopActiveCard from './PopActiveCard';
import SeeMoreFundingBtn from '@components/Button/SeeMoreFundingBtn';

const PopActiveBox = () => {
  return (
    <ActiveBox>
      <div className="box_title">인기 앨범활동 펀딩</div>

      <Swiper
        slidesPerView={3}
        spaceBetween={30}
        centeredSlides={true}
        loop={true}
        navigation={true}
        modules={[Navigation]}
        className="mySwiper"
      >
        <SwiperSlide>
          <PopActiveCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopActiveCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopActiveCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopActiveCard />
        </SwiperSlide>
        <SwiperSlide>
          <PopActiveCard />
        </SwiperSlide>
      </Swiper>
      <SeeMoreFundingBtn btnTitle="펀딩" />
    </ActiveBox>
  );
};

const ActiveBox = styled.div`
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
export default PopActiveBox;
