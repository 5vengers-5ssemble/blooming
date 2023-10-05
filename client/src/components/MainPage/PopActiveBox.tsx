import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import axios from '@api/apiController';

import { Pagination, Navigation } from 'swiper/modules';

import PopActiveCard from './PopActiveCard';
import SeeMoreFundingBtn from '@components/Button/SeeMoreFundingBtn';
import { ProcessInfo } from '@type/ProcessInfo';

const PopActiveBox = () => {
  const [bestActivities, setBestActivities] = useState<ProcessInfo[]>([]);

  useEffect(() => {
    axios
      .get('/activities/best')
      .then((response) => {
        console.log('인기 앨범활동 펀딩 조회 성공', response.data.results);
        setBestActivities(response.data.results);
      })
      .catch((error) => {
        console.error('인기 앨범활동 펀딩 조회 실패', error);
      });
  }, []);

  const navigate = useNavigate();
  const goDetailPage = (index: number) => {
    navigate(`active-detail/${bestActivities[index].id}`);
  };

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
        {bestActivities.map((activity, index) => (
          <SwiperSlide
            key={index}
            onClick={() => {
              goDetailPage(index);
            }}
          >
            <PopActiveCard bestActivityData={activity} />
          </SwiperSlide>
        ))}
      </Swiper>
      <SeeMoreFundingBtn btnTitle="펀딩" type="active" />
    </ActiveBox>
  );
};

const ActiveBox = styled.div`
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
export default PopActiveBox;
