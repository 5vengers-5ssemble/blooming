import React from 'react';
import styled from 'styled-components';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { ReactComponent as LiveIcon } from '../../assets/icons/LiveIcon.svg';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Navigation, Pagination } from 'swiper/modules';

const ArtistGraph = () => {
  return (
    <GraphBox>
      <TrafficGraphBox>
        <div className="traffic_graph_title">최근 유입도</div>
        <img
          src="src/assets/images/traffic_graph.png"
          alt=""
          className="traffic_graph"
        />
      </TrafficGraphBox>
      <FrequencyGraphBox>
        <div className="frequency_graph_title">최근 프리미엄 라이브 빈도</div>
        <img
          src="src/assets/images/frequency_graph.png
        "
          alt=""
          className="frequency_graph"
        />
      </FrequencyGraphBox>
    </GraphBox>
  );
};

const FrequencyGraphBox = styled.div`
  width: 330px;

  .frequency_graph_title {
    color: var(--Black, var(--black-color, #000));
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 11px;
  }
`;
const TrafficGraphBox = styled.div`
  width: 330px;
  margin-bottom: 30px;
  .traffic_graph_title {
    color: var(--Black, var(--black-color, #000));
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 11px;
  }
`;
const GraphBox = styled.div``;
export default ArtistGraph;
