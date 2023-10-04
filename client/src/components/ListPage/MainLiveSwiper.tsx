import { useNavigate } from 'react-router';
import { styled } from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper/modules';
import { LiveInfo } from '@type/ProcessInfo';
import NowRank from './EachRankBox';

import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';
import { ThumbnailEachLive } from './ThumbnailEach';

interface Props {
  nowRank: LiveInfo[];
  nowStat: string;
}
const MainLiveSwiper = ({ nowRank, nowStat }: Props) => {
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
          pagination={{ clickable: true }}
        >
          {nowRank.map((eachRank, idx) => (
            <SwiperSlide key={idx}>
              <div className="each-slide">
                <ThumbnailEachLive key={idx} data={eachRank} />
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
    color: var(--main1-color);
  }

  .swiper-scrollbar-drag,
  .swiper-pagination-bullet-active {
    background-color: var(--main1-color);
  }
`;

export default MainLiveSwiper;
