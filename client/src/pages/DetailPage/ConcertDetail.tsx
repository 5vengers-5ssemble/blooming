import React, { useEffect, useState } from 'react';
import axios from '@api/apiController';
// import axios from 'axios';
import ArtistInfo from '@components/fundingDetail/ArtistInfo';
import Funding from '@components/fundingDetail/Funding';
import FundingDetail from '@components/fundingDetail/FundingDetail';
import { concertDetail, pastConcert } from '@type/ConcertDetail';

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
  pastConcerts: [
    {
      id: 0,
      name: '',
      posterImg: '',
      publishedDate: '',
      revenuePercent: 0,
      targetAmount: 0,
      fundingAmount: 0,
    },
  ],

  viewCounts: [],
};

// const pastFundingInitData

const ActiveDetailPage = () => {
  const activityId = 1;
  const [data, setData] = useState<concertDetail>(initData);
  const [pastFundingdata, setPastFundingData] =
    useState<concertDetail>(initData);

  useEffect(() => {
    //펀딩 상세 조회
    axios
      // .get('http://localhost:7700/concerts', {
      .get('/concerts/1', {
        //   params: {
        //     activityId: activityId,
        //   },
      })
      .then((response) => {
        console.log('요청 성공:', response);
        setData(response.data.results);
      })
      .catch((error) => {
        console.error('요청 실패:', error);
      });

    // axios
    //   .get('/artists/{artistId}/concert/histories')
    //   .then((response) => {
    //     console.log('과거 펀딩목록 조회 성공:', response);
    //     setData;
    //   })
    //   .catch((error) => {
    //     console.error('과거 펀딩목록 조회 실패:', error);
    //   });
  }, []);

  return (
    <>
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
        pastConcertsData={data.pastConcerts}
        viewCountData={data.viewCounts}
      />
      <br />
      <br />
    </>
  );
};

export default ActiveDetailPage;
