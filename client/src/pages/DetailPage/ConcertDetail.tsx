import React, { useEffect, useState } from 'react';
// import axios from '@api/apiController';
import axios from 'axios';
import ArtistInfo from '@components/fundingDetail/ArtistInfo';
import Funding from '@components/fundingDetail/Funding';
import FundingDetail from '@components/fundingDetail/FundingDetail';
import {
  artist,
  concert,
  investment,
  pastConcerts,
  viewCounts,
} from '@type/ConcertDetail';
import { concertDetail } from '@type/ConcertDetail';

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
  },
  pastConcerts: {
    id: 0,
    name: '',
    posterImg: '',
    publishedDate: '',
    revenuePercent: 0,
    targetAmount: 0,
    fundingAmount: 0,
  },

  viewCounts: {
    viewCount: [0, 0, 0, 0, 0, 0],
  },
};

const ActiveDetailPage = () => {
  const activityId = 1;
  const [data, setData] = useState<concertDetail>(initData);

  useEffect(() => {
    axios
      .get('http://localhost:7700/concerts', {
        //   params: {
        //     activityId: activityId,
        //   },
      })
      .then((response) => {
        console.log('요청 성공:', response);
        setData(response.data);
      })
      .catch((error) => {
        console.error('요청 실패:', error);
      });
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
        pastConcertsData={[data.pastConcerts]}
        viewCountData={data.viewCount}
      />
      <br />
      <br />
    </>
  );
};

export default ActiveDetailPage;
