import React from 'react';
import styled from 'styled-components';
import { ReactComponent as LikeIcon } from '../../assets/icons/LikeIcon.svg';
import { ReactComponent as LiveIcon } from '../../assets/icons/LiveIcon.svg';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Navigation, Pagination } from 'swiper/modules';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement,
} from 'chart.js';
import { Bar, Line } from 'react-chartjs-2';
import faker from 'faker';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement,
);
const ArtistGraph = () => {
  // 그래프 관련
  const options1 = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
        // display: false,
      },
      title: {
        display: true,
        // text: 'Chart.js Bar Chart',
      },
    },
    elements: {
      point: {
        radius: 0,
      },
    },
    scales: {
      y: {
        display: false,
      },
    },
  };
  const options2 = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
        display: false,
      },
      title: {
        display: true,
        // text: 'Chart.js Bar Chart',
      },
    },
    elements: {
      point: {
        radius: 0,
      },
    },
    scales: {
      y: {
        display: false,
      },
    },
  };
  const data1 = {
    labels: ['January', 'February', 'March', 'April'],
    datasets: [
      {
        label: '관심 아티스트 등록 수',
        data: [123403, 123603, 125079, 126030],
        borderColor: '#3061B9',
        backgroundColor: '#3061B9',
      },
      {
        label: 'NFT 누적 구매수',
        data: [126030, 129029, 130294, 135049],
        borderColor: '#01CD3B',
        backgroundColor: '#01CD3B',
      },
    ],
  };
  const data2 = {
    labels: ['January', 'February', 'March', 'April'],
    datasets: [
      {
        label: '관심 아티스트 등록 수',
        data: [123403, 123603, 125079, 126030],
        borderColor: '#3061B9',
        backgroundColor: '#3061B9',
      },
    ],
  };

  return (
    <GraphBox>
      <TrafficGraphBox>
        <div className="traffic_graph_title">최근 유입도</div>
        <Line options={options1} data={data1} />
        {/* <img
          src="src/assets/images/traffic_graph.png"
          alt=""
          className="traffic_graph"
        /> */}
      </TrafficGraphBox>
      <FrequencyGraphBox>
        <div className="frequency_graph_title">최근 프리미엄 라이브 빈도</div>
        <Line options={options2} data={data2} />
        {/* <img
          src="src/assets/images/frequency_graph.png
        "
          alt=""
          className="frequency_graph"
        /> */}
      </FrequencyGraphBox>
    </GraphBox>
  );
};

const FrequencyGraphBox = styled.div`
  width: 330px;

  .frequency_graph_title {
    color: var(--Black, var(--black-color, #000));
    font-size: 22px;
    font-weight: 700;
    margin-bottom: 11px;
  }
`;
const TrafficGraphBox = styled.div`
  width: 330px;
  margin-bottom: 30px;
  .traffic_graph_title {
    color: var(--Black, var(--black-color, #000));
    font-size: 22px;
    font-weight: 700;
    margin-bottom: 11px;
  }
`;
const GraphBox = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 70px;
`;
export default ArtistGraph;
