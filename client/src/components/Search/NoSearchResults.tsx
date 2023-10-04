import React from 'react';
import styled from 'styled-components';
import noSearchLottie from '@assets/lottie/no-search.json';
import Lottie from 'lottie-react';

const NoSearchResults = () => {
  return (
    <SearchFrame>
      <LottieFrame>
        <Lottie
          className="lottie"
          animationData={noSearchLottie}
          height={200}
          width={200}
        />
      </LottieFrame>
      검색된 결과가 없습니다{' '}
    </SearchFrame>
  );
};

const LottieFrame = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;

  .lottie {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 80%;
    height: 80%;
  }
`;

const SearchFrame = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 100px;
  font-weight: 600;
  font-size: 20px;
`;

export default NoSearchResults;
