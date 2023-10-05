import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from '@api/apiController';
// import axios from 'axios';
import ArtistInfo from '@components/fundingDetail/ArtistInfo';
import FundingActivity from '@components/fundingDetail/FundingActivity';
import FundingDetailActivity from '@components/fundingDetail/FundingDetailActivity';
import { activeDetail, pastActivity } from '@type/ActiveDetail';
import { MainTitle } from '@style/common';
import NavBar from '@components/common/NavBar';

const initData: activeDetail = {
  artist: {
    id: 0,
    profileImg: '',
    name: '',
    desc: '',
    youtubeUrl: '',
    fancafeUrl: '',
    snsUrl: '',
  },
  activity: {
    id: 0,
    albumImg: '',
    name: '',
    intro: '',
    desc: '',
    startedAt: '',
    endedAt: '',
    targetAmount: 0,
    fundingAmount: 0,
    tracklistImg: '',
    teaserVideoUrl: '',
    albumCompositionImg: '',
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

const pastInitData: pastActivity[] = [
  {
    id: 0,
    name: '',
    albumImg: '',
    publishedDate: '',
    revenuePercent: 0,
    targetAmount: 0,
    fundingAmount: 0,
  },
];

const ActiveDetailPage = () => {
  const [data, setData] = useState<activeDetail>(initData);
  const [pastFundingdata, setPastFundingData] =
    useState<pastActivity[]>(pastInitData);
  const { activityId } = useParams();

  useEffect(() => {
    //펀딩 상세 조회
    axios
      .get(`/activities/${activityId}`)
      .then((response) => {
        console.log('현재 활동 상세 요청 성공:', response);
        setData(response.data.results);
      })
      .catch((error) => {
        console.error('현재 활동 상세 요청 실패:', error);
      });
  }, []);

  useEffect(() => {
    axios
      .get(`/artists/${data.artist.id}/activity/histories`)
      .then((response) => {
        console.log('과거 활동 펀딩목록 조회 성공:', response);
        setPastFundingData(response.data.results);
      })
      .catch((error) => {
        console.error('과거 활동 펀딩목록 조회 실패:', error);
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
        활동<div className="dot"></div>
      </MainTitle>
      <br />
      <br />
      <br />
      <ArtistInfo artistData={data.artist} />
      <br />
      <br />
      <FundingActivity
        artistData={data.artist}
        activityData={data.activity}
        investmentData={data.investment}
      />
      <br />
      <br />
      <FundingDetailActivity
        artistData={data.artist}
        activityData={data.activity}
        investmentData={data.investment}
        pastActivitiesData={pastFundingdata}
        viewCountData={data.viewCounts}
      />
      <br />
      <br />
    </div>
  );
};

export default ActiveDetailPage;
