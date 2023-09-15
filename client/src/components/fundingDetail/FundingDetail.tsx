import React, { useRef, useState, useEffect } from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';

import { Navigation } from 'swiper/modules';
import { FreeMode, Pagination } from 'swiper/modules';

const MainPage = () => {
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
            앨범 정보
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
          <div className="detail_title">앨범 정보</div>
          <div className="detail_sub_title">앨범 소개</div>
          <div className="detail_text">
            폭넓은 보컬 스펙트럼과 특유의 매력적인 보이스로 수많은 리스너를
            매료시키고 있는 가수 김재환.지난해 12월 미니앨범 [THE LETTER] 발매
            이후 약 9개월 만에 발표하는 김재환의 다섯번째 미니앨범. `그 시절
            우리는`은 이별 후 함께했던 시간을 떠올리며 상대방에 대한 그리움을
            김재환만의 감성을 통해 서정적으로 풀어낸 미디엄 R&B 팝 장르의
            곡이다. 직접 작사, 작곡에 참여한 김재환은 과거의 행복하고 반짝였던
            기억을 회고하는 듯한 독백적인 가사와 이별의 그리움을 청량하게 표현한
            멜로디로 곡을 완성해 이별을 겪어 본 사람들의 아련한 감성을 자극했다.
          </div>
          <div className="detail_sub_title">앨범 트랙리스트</div>
          <img
            src="src/assets/images/album_tracklist.png"
            alt="앨범 트랙리스트"
            className="album_tracklist_img"
          />
          <div className="detail_sub_title">앨범 티저영상</div>
          <TeaserVideoBox>
            <VideoBox>
              <iframe
                width="100%"
                height="100%"
                src="https://www.youtube.com/embed/3jQjWhZH990?si=v-yxJj1bCxhfpyWf"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
                classname="album_teaser_video"
              ></iframe>
            </VideoBox>
            <ViewsBox>
              <div className="views_title">조회수</div>
              <img
                src="src/assets/images/views.png"
                alt="조회수 이미지"
                className="views_img"
              />
            </ViewsBox>
          </TeaserVideoBox>

          <div className="detail_sub_title">앨범 구성품</div>
          <img
            src="src/assets/images/albumcomposition.png"
            alt=""
            className="album_composition_img"
          />
          <div className="detail_sub_title">투자상품 개요</div>
          <TableBox>
            <TableContainer>
              <tbody>
                <TableRow>
                  <Tablecolumn1>법인명 (발행인)</Tablecolumn1>
                  <Tablecolumn2>스윙 엔터테인먼트</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>증권 종류</Tablecolumn1>
                  <Tablecolumn2>회사채</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>상환 방법</Tablecolumn1>
                  <Tablecolumn2>음원 + 공연 매출</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>투자금 조달 목적</Tablecolumn1>
                  <Tablecolumn2>음원, 공연 IP의 권리 매입 자금</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>계좌 가격</Tablecolumn1>
                  <Tablecolumn2>100,000 원</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>최소 투자 금액</Tablecolumn1>
                  <Tablecolumn2>100,000 원</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>최소 모집 목표 금액</Tablecolumn1>
                  <Tablecolumn2>200,000,000 원</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>최대 모집 목표 금액</Tablecolumn1>
                  <Tablecolumn2>250,432,000 원</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>모집일</Tablecolumn1>
                  <Tablecolumn2>2023.09.01 ~ 2023.09.20</Tablecolumn2>
                </TableRow>
                <TableRow>
                  <Tablecolumn1>증권 발행일</Tablecolumn1>
                  <Tablecolumn2>2023.09.22</Tablecolumn2>
                </TableRow>
                <tr>
                  <Tablecolumn1>증권 만기일</Tablecolumn1>
                  <Tablecolumn2>2024.09.22</Tablecolumn2>
                </tr>
              </tbody>
            </TableContainer>
          </TableBox>
        </AlbumInfoBox>

        {/* 지난 활동 수익 분석 */}
        <RevenueAnalysisBox ref={revenueAnalysisRef}>
          <div className="detail_title">지난 활동 수익 분석</div>
          <RevenueBox>
            <RevenueAlbumBox>
              <img
                src="src/assets/images/main_album_img.png"
                alt="메인 앨범 이미지"
                className="main_album_img"
              />
            </RevenueAlbumBox>

            <RevenueGraphBox>
              <img
                src="src/assets/images/graph.png"
                alt="수익분석그래프"
                className="revenue_graph"
              />
            </RevenueGraphBox>
            <MainAlbumTextBox>
              <div className="main_album_title">
                KIM JAEHWAN Single Album 봄바람
              </div>
              <div className="main_album_soldout">마감</div>
            </MainAlbumTextBox>
            <RevenueBarBox>
              <RevenueBarTextBox>
                <div className="bar_title">달성액</div>
                <div className="bar_info">223,000,000 원</div>
              </RevenueBarTextBox>
              <div className="bar"></div>
            </RevenueBarBox>

            <AlbumListBox>
              <Swiper
                slidesPerView={3}
                spaceBetween={15}
                pagination={{
                  clickable: true,
                }}
                modules={[Pagination]}
                className="mySwiper"
              >
                <SwiperSlide className="swiper-slide">
                  <img
                    src="src/assets/images/sub_album_img1.png"
                    alt="서브 앨범 이미지 1"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide className="swiper-slide">
                  <img
                    src="src/assets/images/sub_album_img2.png"
                    alt="서브 앨범 이미지 2"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide className="swiper-slide">
                  <img
                    src="src/assets/images/sub_album_img3.png"
                    alt="서브 앨범 이미지 3"
                    className="album_list_img"
                  />
                </SwiperSlide>
                <SwiperSlide className="swiper-slide">
                  <img
                    src="src/assets/images/sub_album_img4.png"
                    alt="서브 앨범 이미지 4"
                    className="album_list_img"
                  />
                </SwiperSlide>
              </Swiper>
            </AlbumListBox>

            <RevenueBarInfoBox>
              <RevenueBarInfoTextBox>
                <div className="revenue_bar_title">증권 발행일</div>
                <div className="revenue_bar_content">2023.09.22</div>
              </RevenueBarInfoTextBox>
              <RevenueBarInfoTextBox>
                <div className="revenue_bar_title">수익률</div>
                <div className="revenue_bar_content">+12%</div>
              </RevenueBarInfoTextBox>
            </RevenueBarInfoBox>
          </RevenueBox>
        </RevenueAnalysisBox>
        <OtherActionBox ref={otherActionRef}>
          <div className="detail_title">기타 활동</div>
          <ActionVideoBox>
            <Swiper
              slidesPerView={2}
              centeredSlides={true}
              spaceBetween={30}
              grabCursor={true}
              // pagination={{
              //   clickable: true,
              // }}
              modules={[Navigation]}
              className="mySwiper"
            >
              <SwiperSlide className="swiper-slide">
                <iframe
                  width="100%"
                  height="261px"
                  src="https://www.youtube.com/embed/NPyS5VSgxyw?si=Wtg0LZc8aaxdKqom"
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowfullscreen
                ></iframe>
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <iframe
                  width="100%"
                  height="261px"
                  src="https://www.youtube.com/embed/kmbMEZq8tIg?si=lt_HvN8A5tKB77TZ"
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowfullscreen
                ></iframe>
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <iframe
                  width="100%"
                  height="261px"
                  src="https://www.youtube.com/embed/aMsuBUtUQzQ?si=ITt-nRcURgVLCNae"
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowfullscreen
                ></iframe>
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <iframe
                  width="100%"
                  height="261px"
                  src="https://www.youtube.com/embed/Q6NieNXfLB8?si=RaeI-eSO2AYNJgsb"
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowfullscreen
                ></iframe>
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <iframe
                  width="100%"
                  height="261px"
                  src="https://www.youtube.com/embed/F_g-W7fqmhI?si=1uI5NW20QEAhd9bK"
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowfullscreen
                ></iframe>
              </SwiperSlide>
            </Swiper>
          </ActionVideoBox>
          <BroadPosterBox>
            <Swiper
              slidesPerView={2}
              centeredSlides={true}
              spaceBetween={30}
              grabCursor={true}
              // pagination={{
              //   clickable: true,
              // }}
              modules={[Navigation]}
              className="mySwiper"
            >
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster1.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster2.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster3.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster4.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster5.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster6.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
              <SwiperSlide className="swiper-slide">
                <img
                  src="src/assets/images/broad_poster7.jfif"
                  alt=""
                  className="borad_poster"
                />
              </SwiperSlide>
            </Swiper>
          </BroadPosterBox>
        </OtherActionBox>
        <PortfolioBox ref={artistPortfolioRef}>
          <div className="detail_title">아티스트 포트폴리오</div>
          <Portfolio>
            <div className="portfolio_title">유튜브</div>
            <div className="portfolio_content">
              음원 유튜브
              (https://www.youtube.com/channel/UCnx4K8yHnYo06t3zoanPhpg)
            </div>
            <div className="portfolio_content">
              오피셜 유튜브 (https://www.youtube.com/@JAEHWANKIM_official)
            </div>
          </Portfolio>
          <Portfolio>
            <div className="portfolio_title">공식 팬 카페</div>
            <div className="portfolio_content">
              다음 카페 (https://m.cafe.daum.net/KJHOfficial/_rec)
            </div>
          </Portfolio>
          <Portfolio>
            <div className="portfolio_title">공식 SNS </div>
            <div className="portfolio_content">
              인스타그램 (https://www.instagram.com/kjh_official/feed/?hl=ko)
            </div>
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

const OtherActionBox = styled.div``;

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
  width: 56.6%;
`;

const TeaserVideoBox = styled.div`
  display: flex;
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

  width: 960px;
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
    width: 960px;
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

export default MainPage;
