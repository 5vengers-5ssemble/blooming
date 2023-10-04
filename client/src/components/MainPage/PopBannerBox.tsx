import React, { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import { Carousel } from 'react-responsive-carousel';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Pagination, Autoplay } from 'swiper/modules';
// import { EffectCoverflow, Autoplay } from 'swiper';

import PopBannerCard from './PopBannerCard';

const PopBannerBox = () => {
  //
  const [swiperImages, setSwiperImages] = useState([
    '../../src/assets/images/pop_banner_concert1.png',
    '../../src/assets/images/pop_banner_album1.jfif',
    '../../src/assets/images/bts-concert.jpg',
    '../../src/assets/images/pop_banner_concert1.png',
    '../../src/assets/images/pop_banner_album1.jfif',
  ]);

  const [rightEdgeColor, setRightEdgeColor] = useState(['', '', '', '', '']);
  //

  useEffect(() => {
    const updatedRightEdgeColor = swiperImages.map((imgSrc, index) => {
      const img = new Image();
      img.src = imgSrc;

      const canvas = document.createElement('canvas');
      canvas.width = 1;
      canvas.height = img.height;
      const ctx = canvas.getContext('2d');

      if (ctx) {
        ctx.drawImage(img, 0, 0, 1, img.height / 2);
        const pixelData = ctx.getImageData(0, 0, 1, img.height / 2).data;
        const rightEdgePixel = Array.from(pixelData.slice(-4));

        const hexColor = `#${rightEdgePixel
          .slice(0, 3)
          .map((value) => value.toString(16).padStart(2, '0'))
          .join('')}`;

        return hexColor;
      }
      return rightEdgeColor[index];
    });
    setRightEdgeColor(updatedRightEdgeColor);
  }, [swiperImages]);

  return (
    <BannerBox>
      <Swiper
        slidesPerView={2}
        spaceBetween={30}
        centeredSlides={true}
        // centerInsufficientSlides={false}
        loop={true}
        autoplay={{
          delay: 3000,
          disableOnInteraction: false,
        }}
        pagination={{
          clickable: true,
        }}
        modules={[Pagination, Autoplay]}
        className="mySwiper"
      >
        {swiperImages.map((imgSrc, index) => (
          <SwiperSlide key={index}>
            <PopBannerCard imgSrc={imgSrc} hexColor={rightEdgeColor[index]} />
          </SwiperSlide>
        ))}
      </Swiper>
    </BannerBox>
  );
};

const BannerBox = styled.div`
  margin: 0 -280px;
  z-index: 999;
`;

export default PopBannerBox;
