import React, { useEffect } from 'react';
import axios from '@api/apiController';

import ArtistInfo from '@components/fundingDetail/ArtistInfo';
import Funding from '@components/fundingDetail/Funding';
import FundingDetail from '@components/fundingDetail/FundingDetail';

const ActiveDetailPage = () => {
  const activityId = 1; // 나중에 수정

  // get 요청으로 전체 정보 불러옴
  axios
    .get(`/activities/1`, {
      params: {
        activityId: activityId,
      },
    })
    .then((response) => {
      console.log('요청 성공:', response.results);
    })
    .catch((error) => {
      console.error('요청 실패:', error);
    });

  return (
    <>
      <br />
      <br />
      <ArtistInfo></ArtistInfo>
      <br />
      <br />
      <Funding></Funding>
      <br />
      <br />
      <FundingDetail></FundingDetail>
      <br />
      <br />
    </>
  );
};

export default ActiveDetailPage;
