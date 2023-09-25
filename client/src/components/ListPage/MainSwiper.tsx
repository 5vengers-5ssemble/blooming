import { useNavigate } from 'react-router';
import { styled } from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper/modules';
import { ProcessInfo } from '@type/ProcessInfo';
import NowRank from './EachRankBox';

import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';

interface Props {
  nowRank: ProcessInfo[];
  nowStat: string;
}
const MainSwiper = ({ nowRank, nowStat }: Props) => {
  const navigate = useNavigate();
  const handleDetailInfo = (id: number) => {
    navigate(`/${nowStat}-detail/${id}`);
  };

  return (
    <>
      <SwiperContainer>
        <Swiper
          slidesPerView={'auto'}
          spaceBetween={20}
          modules={[Navigation, Pagination, Scrollbar, A11y]}
          navigation
          pagination={{ clickable: true }}
          scrollbar={{ draggable: true }}
        >
          {nowRank.map((eachRank, idx) => (
            <SwiperSlide key={idx}>
              <div className="each-slide">
                <NowRank nowRank={eachRank} nowStat={nowStat} />
              </div>
            </SwiperSlide>
          ))}
        </Swiper>
      </SwiperContainer>
    </>
  );
};

const SwiperContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  overflow: hidden;
  justify-content: space-between;
  .swiper {
    width: 100%;
  }

  .each-slide {
    cursor: pointer;
  }
  .swiper-slide {
    width: 400px;
  }
  .swiper-wrapper {
    display: -webkit-inline-box;
  }

  .swiper-button-prev,
  .swiper-button-next {
    color: var(--white-color);
  }

  .swiper-scrollbar-drag,
  .swiper-pagination-bullet-active {
    background-color: var(--white-color);
  }
`;

export default MainSwiper;
