import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from '@api/apiController';
// import axios from 'axios';
import ArtistInfo from '@components/fundingDetail/ArtistInfo';
import Funding from '@components/fundingDetail/Funding';
import FundingDetail from '@components/fundingDetail/FundingDetail';
import { concertDetail, pastConcert } from '@type/ConcertDetail';
import { MainTitle } from '@style/common';
import NavBar from '@components/common/NavBar';

const initData: concertDetail = {
  artist: {
    id: 0,
    profileImg: '',
    name: '',
    desc: '',
    youtubeUrl: '',
    fancafeUrl: '',
    snsUrl: '',
  },
  concert: {
    id: 0,
    posterImg: '',
    name: '',
    intro: '',
    desc: '',
    startedAt: '',
    endedAt: '',
    targetAmount: 0,
    fundingAmount: 0,
    setlistImg: '',
    teaserVideoUrl: '',
    concertGoodsImg: '',
  },
  investment: {
    overview: {
      publisher: '',
      type: '',
      redemptionType: '',
      financingPurpose: '',
      pricePerAccount: 0,
      minimumPrice: 0,
      minimumFundingAmount: 0,
      maximumFundingAmount: 0,
      fundingStartDate: '',
      fundingEndDate: '',
      investmentPublishedDate: '',
      investmentMaturedDate: '',
    },
    structure: '',
    goods: '',
  },
  viewCounts: [],
};

const pastInitData: pastConcert[] = [
  {
    id: 0,
    name: '',
    posterImg: '',
    publishedDate: '',
    revenuePercent: 0,
    targetAmount: 0,
    fundingAmount: 0,
  },
];

const ConcertDetailPage = () => {
  const [data, setData] = useState<concertDetail>(initData);
  const [pastFundingdata, setPastFundingData] =
    useState<pastConcert[]>(pastInitData);
  const { concertId } = useParams();

  useEffect(() => {
    //펀딩 상세 조회
    axios
      .get(`/concerts/${concertId}`)
      .then((response) => {
        console.log('현재 콘서트 상세 요청 성공:', response);
        setData(response.data.results);
      })
      .catch((error) => {
        console.error('현재 콘서트 상세요청 실패:', error);
      });
  }, []);

  useEffect(() => {
    axios
      .get(`/artists/${data.artist.id}/concert/histories`)
      .then((response) => {
        console.log('과거 콘서트 펀딩목록 조회 성공:', response);
        setPastFundingData(response.data.results);
      })
      .catch((error) => {
        console.error('과거 콘서트 펀딩목록 조회 실패:', error);
      });
  }, [data]);

  return (
    <div>
      <NavBar></NavBar>
      <br />
      <br />
      <br />
      <br />
      <MainTitle>
        콘서트<div className="dot"></div>
      </MainTitle>
      <br />
      <br />
      <br />
      <ArtistInfo artistData={data.artist} />
      <br />
      <br />
      <Funding
        artistData={data.artist}
        concertData={data.concert}
        investmentData={data.investment}
      />
      <br />
      <br />
      <FundingDetail
        artistData={data.artist}
        concertData={data.concert}
        investmentData={data.investment}
        pastConcertsData={pastFundingdata}
        viewCountData={data.viewCounts}
      />
      <br />
      <br />
    </div>
  );
};

export default ConcertDetailPage;
