import { useNavigate } from 'react-router';
import { styled } from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import { ProcessInfo } from '@type/ProcessInfo';
import NowRank from './EachRankBox';

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
        <Swiper slidesPerView={'auto'} spaceBetween={20}>
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
    margin-right: -200px;
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
`;

export default MainSwiper;
