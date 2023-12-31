import React, { useRef, useState, useEffect } from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination } from 'swiper/modules';
import { ReactComponent as LinkIcon } from '@assets/icons/LinkIcon.svg';

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
import {
  artist,
  concert,
  investment,
  pastConcert,
  // concertDetail,
} from '@type/ConcertDetail';

interface Props {
  artistData: artist;
  concertData: concert;
  investmentData: investment;
  pastConcertsData: pastConcert[];
  viewCountData: number[];
}

interface Concert {
  id: number;
  name: string;
  posterImg: string;
  publishedDate: string;
  revenuePercent: number;
  targetAmount: number;
  fundingAmount: number;
}

// 그래프 관련
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

const FundingDetail: React.FC<Props> = ({
  artistData,
  concertData,
  investmentData,
  pastConcertsData,
  viewCountData,
}) => {
  //그래프 관련
  const options = {
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
  };

  //조회수 선 그래프
  // 현재 날짜 가져오기
  const today = new Date();

  // 레이블 배열 초기화
  const labels = [];

  // 7일 전부터 오늘까지의 날짜를 레이블 배열에 추가
  for (let i = 7; i >= 1; i--) {
    const date = new Date(today);
    date.setDate(today.getDate() - i);
    const month = date.getMonth() + 1;
    const day = date.getDate(); // 일

    // 레이블에 날짜 추가
    labels.push(`${month} / ${day}`);
  }

  const data1 = {
    labels: labels,
    datasets: [
      {
        label: '누적 조회수 (회)',
        // data: viewCountData,
        // data: viewCountData.map((viewCount) => viewCount),
        data: [100000, 4000, 200000, 1230000],
        borderColor: '#3061B9',
        backgroundColor: '#3061B9',
      },
    ],
  };
  //지난 활동 수익율 막대 그래프
  const data2 = {
    // labels: [
    //   ['봄바람', '2022-01-01'],
    //   ['Empty Dream', '2022-01-01'],
    //   ['J.A.M (Journey Above Music)', '2022-01-01'],
    //   ['THE LETTER', '2022-01-01'],
    //   ['B-Side', '2022-01-01'],
    // ],
    labels: pastConcertsData.map((concert) => [
      concert.name,
      concert.publishedDate.split('T')[0],
    ]),
    datasets: [
      {
        label: '수익률 (%)',
        data: [1, 2, -3, 5, 6],
        // data: pastConcertsData.map((concert) => concert.revenuePercent),
        backgroundColor: '#A8BEE1',
      },
    ],
  };

  // 스크롤 관련
  const albumInfoRef = useRef<HTMLDivElement>(null);
  const revenueAnalysisRef = useRef<HTMLDivElement>(null);
  const otherActionRef = useRef<HTMLDivElement>(null);
  const artistPortfolioRef = useRef<HTMLDivElement>(null);
  const investmentInfoRef = useRef<HTMLDivElement>(null);

  const [activeSection, setActiveSection] =
    useState<React.RefObject<HTMLDivElement> | null>(null);

  const handleScrollToInvestmentInfo = (
    ref: React.RefObject<HTMLDivElement>,
  ) => {
    if (ref.current) {
      ref.current.scrollIntoView({ behavior: 'smooth' });
      // setActiveSection(ref);
    }
  };

  useEffect(() => {
    const handleScroll = () => {
      const scrollPosition = window.scrollY;
      const sections = [
        albumInfoRef,
        revenueAnalysisRef,
        otherActionRef,
        artistPortfolioRef,
        investmentInfoRef,
      ];

      // Find the first section that is currently in the viewport
      for (const sectionRef of sections) {
        if (sectionRef.current) {
          const sectionTop = sectionRef.current.offsetTop;
          const sectionHeight = sectionRef.current.offsetHeight;

          if (
            scrollPosition >= sectionTop &&
            scrollPosition < sectionTop + sectionHeight
          ) {
            setActiveSection(sectionRef);
            break;
          }
        }
      }
    };

    window.addEventListener('scroll', handleScroll);
    // Clean up the event listener when the component unmounts
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);
  // console.log(
  //   `pastConcertData: ${pastConcertsData.map((concert) => [
  //     concert.name,
  //     concert.publishedDate,
  //   ])}`,
  // );
  return (
    <FundingDetailBox>
      <div className="funding_detail">투자 상품 상세</div>
      <TabBox>
        <ButtonBox>
          <button
            className={`tab_btn ${
              activeSection === albumInfoRef ? 'active' : ''
            }`}
            onClick={() => {
              handleScrollToInvestmentInfo(albumInfoRef);
            }}
          >
            콘서트 정보
          </button>
          <button
            className={`tab_btn ${
              activeSection === revenueAnalysisRef ? 'active' : ''
            }`}
            onClick={() => {
              handleScrollToInvestmentInfo(revenueAnalysisRef);
            }}
          >
            지난 활동 수익 분석
          </button>
          <button
            className={`tab_btn ${
              activeSection === otherActionRef ? 'active' : ''
            }`}
            onClick={() => {
              handleScrollToInvestmentInfo(otherActionRef);
            }}
          >
            기타 활동
          </button>
          <button
            className={`tab_btn ${
              activeSection === artistPortfolioRef ? 'active' : ''
            }`}
            onClick={() => {
              handleScrollToInvestmentInfo(artistPortfolioRef);
            }}
          >
            아티스트 포트폴리오
          </button>
          <button
            className={`tab_btn ${
              activeSection === investmentInfoRef ? 'active' : ''
            }`}
            onClick={() => {
              handleScrollToInvestmentInfo(investmentInfoRef);
            }}
          >
            투자 위험 안내
          </button>
        </ButtonBox>
      </TabBox>
      <DetailBox>
        <AlbumInfoBox ref={albumInfoRef}>
          <div className="detail_title">콘서트 정보</div>
          <div className="detail_sub_title">콘서트 소개</div>
          <div className="detail_text">
            {/* 폭넓은 보컬 스펙트럼과 특유의 매력적인 보이스로 수많은 리스너를
            매료시키고 있는 가수 김재환.지난해 12월 미니앨범 [THE LETTER] 발매
            이후 약 9개월 만에 발표하는 김재환의 다섯번째 미니앨범. `그 시절
            우리는`은 이별 후 함께했던 시간을 떠올리며 상대방에 대한 그리움을
            김재환만의 감성을 통해 서정적으로 풀어낸 미디엄 R&B 팝 장르의
            곡이다. 직접 작사, 작곡에 참여한 김재환은 과거의 행복하고 반짝였던
            기억을 회고하는 듯한 독백적인 가사와 이별의 그리움을 청량하게 표현한
            멜로디로 곡을 완성해 이별을 겪어 본 사람들의 아련한 감성을 자극했다. */}
            {concertData.desc}
          </div>
          <div className="detail_sub_title">공연 순서</div>
          <img
            // src="src/assets/images/album_tracklist.png"
            src={concertData.setlistImg}
            alt="콘서트 순서"
            className="album_tracklist_img"
          />
          <div className="detail_sub_title">콘서트 티저영상</div>
          <TeaserVideoBox>
            <VideoBox>
              <iframe
                width="100%"
                height="100%"
                src={`https://www.youtube.com/embed/${
                  concertData.teaserVideoUrl.split('v=')[1]
                }`}
                title="YouTube video player"
                frameBorder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowFullScreen
                className="album_teaser_video"
              ></iframe>
            </VideoBox>
            <ViewsBox>
              <div className="views_title">조회수</div>
              {/* <img
                src="src/assets/images/views.png"
                alt="조회수 이미지"
                className="views_img"
              /> */}
              <Line options={options} data={data1} />
            </ViewsBox>
          </TeaserVideoBox>

          <div className="detail_sub_title">콘서트 펀딩 구성</div>
          <img
            // src="src/assets/images/albumcomposition.png"
            src={concertData.concertGoodsImg}
            alt=""
            className="album_composition_img"
          />
          <div className="detail_sub_title">투자상품 개요</div>
          <TableBox>
            <TableContainer>
              <tbody>
                <TableRow>
                  <Tablecolumn1>법인명 (발행인)</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.publisher}
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>증권 종류</Tablecolumn1>
                  <Tablecolumn2>{investmentData.overview.type}</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>상환 방법</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.redemptionType}
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>투자금 조달 목적</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.financingPurpose}
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>계좌 가격</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.pricePerAccount.toLocaleString()}{' '}
                    원
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>최소 투자 금액</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.minimumPrice.toLocaleString()} 원
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>최소 모집 목표 금액</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.minimumFundingAmount.toLocaleString()}{' '}
                    원
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>최대 모집 목표 금액</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.maximumFundingAmount.toLocaleString()}{' '}
                    원
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>모집일</Tablecolumn1>
                  <Tablecolumn2>
                    {investmentData.overview.fundingStartDate.split('T')[0]} ~{' '}
                    {investmentData.overview.fundingEndDate.split('T')[0]}
                  </Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>증권 발행일</Tablecolumn1>
                  <Tablecolumn2>
                    {
                      investmentData.overview.investmentPublishedDate.split(
                        'T',
                      )[0]
                    }
                  </Tablecolumn2>
                </TableRow>
                <tr>
                  <Tablecolumn1>증권 만기일</Tablecolumn1>
                  <Tablecolumn2>
                    {
                      investmentData.overview.investmentMaturedDate.split(
                        'T',
                      )[0]
                    }
                  </Tablecolumn2>
                </tr>
              </tbody>
            </TableContainer>
          </TableBox>
        </AlbumInfoBox>

        {/* 지난 활동 수익 분석 */}
        <RevenueAnalysisBox ref={revenueAnalysisRef}>
          <div className="detail_title">지난 활동 수익 분석</div>

          <Bar options={options} data={data2} />
        </RevenueAnalysisBox>
        <OtherActionBox ref={otherActionRef}>
          <div className="detail_title">기타 활동</div>
          <div className="video_active_div">
            <LinkIcon />
            <a
              href={`https://www.youtube.com/results?search_query=${artistData.name}`}
              className="video_active_link"
            >
              아티스트의 영상활동 더 보러가기↗
            </a>
          </div>
          <div className="broad_active_div">
            <LinkIcon />
            <a
              href={`https://namu.wiki/w/${artistData.name}`}
              className="broad_active_link"
            >
              아티스트의 방송활동 더 보러가기↗
            </a>
          </div>
        </OtherActionBox>
        <PortfolioBox ref={artistPortfolioRef}>
          <div className="detail_title">아티스트 포트폴리오</div>
          <Portfolio>
            <div className="portfolio_title">유튜브</div>
            <a href={artistData.youtubeUrl} className="portfolio_content">
              공식 유튜브 바로가기 ↗
            </a>
          </Portfolio>
          <Portfolio>
            <div className="portfolio_title">공식 팬 카페</div>
            <a href={artistData.fancafeUrl} className="portfolio_content">
              공식 팬카페 바로가기 ↗
            </a>
          </Portfolio>
          <Portfolio>
            <div className="portfolio_title">공식 SNS </div>
            <a href={artistData.snsUrl} className="portfolio_content">
              SNS 바로가기 ↗
            </a>
          </Portfolio>
        </PortfolioBox>
        <InvestmentInfoBox ref={investmentInfoRef}>
          <div className="detail_title">투자 위험 안내</div>
          <InvestmentInfo>
            <NumberBox>1</NumberBox>
            <InvestmentInfoText>
              <div className="investment_info_title">원금 손실 위험</div>
              <div className="investment_info_content">
                투자자는 투자원금액의 전부 또는 일부에 대한 손실 위험이 있으며,
                투자자의 투자원금액 손실은 전적으로 투자자가 부담합니다.
              </div>
            </InvestmentInfoText>
          </InvestmentInfo>
          <InvestmentInfo>
            <NumberBox>2</NumberBox>
            <InvestmentInfoText>
              <div className="investment_info_title">
                유명인, 주요인물(개인) 신뢰도 하락위험
              </div>
              <div className="investment_info_content">
                관련 주요 인물의 개인적 신뢰 및 비도덕적 문제로 인한 이미지 타격
                및 인지도가 급격하게 하락할 수 있습니다.
              </div>
            </InvestmentInfoText>
          </InvestmentInfo>
          <InvestmentInfo>
            <NumberBox>3</NumberBox>
            <InvestmentInfoText>
              <div className="investment_info_title">시장 위험</div>
              <div className="investment_info_content">
                예상치 못한 정치· 경제 상황, 정부의 조치 및 세제의 변경으로
                영향을 미칠 수 있습니다.
              </div>
            </InvestmentInfoText>
          </InvestmentInfo>
          <InvestmentInfo>
            <NumberBox>4</NumberBox>
            <InvestmentInfoText>
              <div className="investment_info_title">그 외 위험 사항</div>
              <div className="investment_info_content">
                투자상품설명서 내 투자위험 참고
              </div>
            </InvestmentInfoText>
          </InvestmentInfo>
        </InvestmentInfoBox>
      </DetailBox>
    </FundingDetailBox>
  );
};

// 투자 위험 안내
const InvestmentInfoText = styled.div`
  display: flex;
  flex-direction: column;

  .investment_info_title {
    font-size: 18px;
    font-weight: 700;
    margin-bottom: 13px;
  }

  .investment_info_content {
    font-size: 14px;
    font-weight: 500;
  }
`;

const NumberBox = styled.div`
  width: 52px;
  height: 52px;
  border-radius: 6px;
  background: var(--Main, #3061b9);

  color: var(--White, #fdfdfd);
  font-size: 20px;
  font-weight: 700;
  line-height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;

  margin-right: 23px;
`;

const InvestmentInfo = styled.div`
  display: flex;
  flex-direction: row;
  margin: 0 26px 26px;
`;

const InvestmentInfoBox = styled.div``;

// 아티스트 포트폴리오

const Portfolio = styled.div`
  margin: 0 26px 30px;

  .portfolio_title {
    color: var(--Main, #3061b9);
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;
    margin-bottom: 15px;
  }

  .portfolio_content {
    color: var(--Black, var(--black-color, #000));
    font-size: 14px;
    font-weight: 700;
    line-height: 30px;
    text-decoration-line: underline;
    margin-left: 7px;
  }
`;

const PortfolioBox = styled.div``;

// 기타 활동
const BroadPosterBox = styled.div`
  margin: 0 26px;
  display: flex;
  /* flex-wrap: wrap; */
  overflow: hidden;
  justify-content: space-around;
  height: 270px;

  .borad_poster {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 이미지가 부모 요소에 맞게 조절되도록 */
  }

  .swiper {
    width: 40%;
    height: 100%;
  }

  .swiper-slide {
    width: 20%;
  }
  .swiper-wrapper {
    display: -webkit-inline-box;
  }
`;

const ActionVideoBox = styled.div`
  margin: 0 26px;
  display: flex;
  flex-wrap: wrap;
  overflow: hidden;
  justify-content: space-around;
  align-items: center;
  height: 300px;
  .swiper {
    width: 100%;
  }

  .swiper-slide {
    width: 40%;
  }
  .swiper-wrapper {
    display: -webkit-inline-box;
  }
`;

const OtherActionBox = styled.div`
  .video_active_div {
    margin-left: 26px;
  }
  .broad_active_div {
    margin: 20px 0 0 26px;
  }

  .broad_active_link {
    color: var(--Main, #3061b9);
    font-size: 20px;
    font-weight: 700;
    margin-left: 10px;
  }
  .video_active_link {
    color: var(--Main, #3061b9);
    font-size: 20px;
    font-weight: 700;
    margin-left: 10px;
  }
`;

// 지난 활동 수익 분석

const RevenueBarInfoTextBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-right: 35px;

  .revenue_bar_title {
    font-size: 16px;
    font-weight: 600;
    line-height: 30px;
  }

  .revenue_bar_content {
    font-size: 16px;
    font-weight: 500;
    line-height: 30px;
    margin-left: 70px;
  }
`;
const RevenueBarTextBox = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  .bar_title {
    font-size: 18px;
    font-weight: 700;
    line-height: 17px;
  }

  .bar_info {
    text-align: right;
    font-size: 18px;
    font-weight: 500;
    line-height: 30px;
    margin-right: 35px;
  }
`;

const RevenueBarInfoBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
const RevenueBarBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  .bar {
    height: 11px;
    background-color: #c7c7c7;
    margin-top: 10px;
    margin-bottom: 26px;
    margin-right: 35px;
    border-radius: 10px;
  }
`;

const AlbumListBox = styled.div`
  display: flex;
  flex-wrap: wrap;
  overflow: hidden;
  justify-content: space-around;
  align-items: center;
  .swiper {
    width: 100%;
  }

  .swiper-slide {
    width: 40%;
  }
  .swiper-wrapper {
    display: -webkit-inline-box;
  }

  .album_list_img {
    display: flex;
    flex-direction: row;
    /* width: 100%; */
    /* height: 100%; */
    /* object-fit: cover; */
    padding: 10px;
  }
`;
const MainAlbumTextBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  .main_album_title {
    font-size: 14px;
    font-weight: 600;
    /* line-height: 25px; */
  }

  .main_album_soldout {
    color: var(--Error, #e30f0f);
    font-size: 14px;
    font-weight: 600;
    /* line-height: 25px; */
    margin-right: 5px;
  }
`;

const RevenueGraphBox = styled.div`
  width: 100%;

  .revenue_graph {
    width: 100%;
  }
`;

const RevenueAlbumBox = styled.div`
  .main_album_img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;
const RevenueBox = styled.div`
  margin: 0 26px;
  display: grid;
  grid-template-columns: 2fr 3fr;
  /* grid-template-rows: 300px 25px 120px;  */
  grid-template-rows: auto auto auto;
  grid-gap: 15px;
`;

const RevenueAnalysisBox = styled.div``;

// 앨범 정보
const TableRow = styled.tr`
  border-bottom: 1px solid #c7c7c7;
`;

const Tablecolumn2 = styled.td`
  font-size: 14px;
  font-weight: 500;
  line-height: 30px;
  padding: 7px 30px;
`;
const Tablecolumn1 = styled.th`
  font-size: 14px;
  font-weight: 500;
  line-height: 30px;
  text-align: center;
  width: 22%;
  background-color: #ebf7f2;
  padding: 7px 30px;
`;

const TableContainer = styled.table`
  border-collapse: collapse;
  width: 100%;
`;

const TableBox = styled.div`
  margin: 0 26px;
`;

const ViewsBox = styled.div`
  width: 35%;
  align-self: center;

  .views_title {
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 30px;
  }
  .views_img {
    width: 100%;
  }
`;

const VideoBox = styled.div`
  margin: 0 26px;
  width: 100%;
  height: 'auto';
`;

const TeaserVideoBox = styled.div`
  display: flex;
  height: 350px;
`;

const AlbumInfoBox = styled.div`
  .detail_title {
    margin-top: 40px !important;
  }

  .detail_text {
    margin: 0 26px;
  }

  .album_tracklist_img {
    margin: 0 26px;
  }

  .album_composition_img {
    margin: 0 26px;
    width: 90%;
  }
`;
const DetailBox = styled.div`
  .detail_text {
    font-size: 14px;
    font-weight: 600;
    line-height: 30px;
  }
  .detail_sub_title {
    font-size: 18px;
    font-weight: 700;
    line-height: 30px;
    margin: 50px 0 25px;
  }

  .detail_title {
    font-size: 20px;
    font-weight: 700;
    margin: 100px 0 36px;
  }
`;
const ButtonBox = styled.div`
  display: flex;
  justify-content: space-evenly;
`;
const TabBox = styled.div`
  padding-top: 10px;
  z-index: 999;
  position: sticky;
  top: 30px;

  width: 1120px;
  height: auto;

  border-bottom: 3px #c7c7c7 solid;
  background-color: #fcfcfc;

  .tab_btn {
    &.active {
      color: black;
    }

    border: none;
    background: none;
    padding: 15px;
    width: 10;

    color: #a8bee1;
    font-size: 19px;
    font-weight: 700;
    line-height: 25px;

    cursor: pointer;
  }
`;

const FundingDetailBox = styled.div`
  .funding_detail {
    top: 0px;
    width: 1120px;
    height: auto;
    position: sticky;
    z-index: 999;
    background-color: #fcfcfc;
    display: block;

    font-size: 25px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    color: #000000;
    padding-bottom: 10px;
  }
`;

export default FundingDetail;
