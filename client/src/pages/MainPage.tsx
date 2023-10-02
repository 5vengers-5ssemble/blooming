import React from 'react';
import styled from 'styled-components';

import Navbar from '@components/common/NavBar';
import PopBannerBox from '@components/MainPage/PopBannerBox';
import PopActiveBox from '@components/MainPage/PopActiveBox';
import PopConcertBox from '@components/MainPage/PopConcertBox';
import PopStreamBox from '@components/MainPage/PopStreamBox';

const MainPage = () => {
  return (
    <div>
      <Navbar isMain={true} />
      <Background></Background>
      <br />
      <PopBannerBox></PopBannerBox>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <PopActiveBox></PopActiveBox>
      <br />
      <br />
      <br />
      <br />
      <br />
      <PopConcertBox></PopConcertBox>
      <br />
      <br />
      <br />
      <br />
      <br />
      <PopStreamBox></PopStreamBox>
    </div>
  );
};

const Background = styled.div`
  position: absolute;
  top: 60px;
  left: 0;
  width: 100%;
  height: 250px;
  background: var(--Main4, #1b335e);
  z-index: 1;
`;
export default MainPage;
