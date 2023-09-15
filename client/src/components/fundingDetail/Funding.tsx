import React from 'react';
import styled from 'styled-components';

const MainPage = () => {
  return (
    <FundingBox>
      <img
        src="src/assets/images/album_img.jfif"
        alt="앨범 자켓"
        className="album_img"
      />
      <Funding>
        <div className="album_title">KIM JAEHWAN 5th Album : Empty Dream</div>
        <div className="album_desc">
          폭넓은 보컬 스펙트럼과 특유의 매력적인 보이스로 수많은 리스너를
          매료시키고 있는 가수 김재환. 지난해 12월 미니앨범 [THE LETTER] 발매
          이후 약 9개월 만에 발표하는 김재환의 다섯번째 미니앨범.
        </div>
        <RateBox>
          <ProgressBox>
            <TextBox>
              <div className="bar_title">참여일</div>
              <div className="bar_info">15일 남음</div>
            </TextBox>
            <div className="bar"></div>
          </ProgressBox>
          <ProgressBox>
            <TextBox>
              <div className="bar_title">달성액</div>
              <div className="bar_info">72% 달성</div>
            </TextBox>
            <div className="bar"></div>
          </ProgressBox>
          <FundingBtn>펀딩하기</FundingBtn>
        </RateBox>
      </Funding>
    </FundingBox>
  );
};

const FundingBtn = styled.button`
  border-radius: 6px;
  background: #3061b9;
  border: none;
  padding: 8px 25px;

  font-size: 20px;
  font-weight: 700;
  line-height: 25px;
  color: #fdfdfd;

  cursor: pointer;
  margin-right: 35px;
  margin-top: 16px;
  display: flex;
  align-self: flex-end;
`;

const TextBox = styled.div`
  display: flex;
  justify-content: space-between;
`;

const ProgressBox = styled.div`
  display: flex;
  flex-direction: column;

  .bar_info {
    font-size: 14px;
    font-weight: 500;
    line-height: 17px;
    margin-right: 35px;
  }
  .bar_title {
    font-size: 16px;
    font-weight: 700;
    line-height: 17px;
  }

  .bar {
    height: 11px;
    background-color: #c7c7c7;
    margin-top: 10px;
    margin-bottom: 26px;
    margin-right: 35px;
    border-radius: 10px;
  }
`;

const RateBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 49px;
`;

const Funding = styled.div`
  display: flex;
  flex-direction: column;

  .album_title {
    font-size: 25px;
    font-weight: 800;
    line-height: 25px;
    margin-top: 57px;
    margin-right: 45px;
  }

  .album_desc {
    font-size: 14px;
    font-weight: 600;
    line-height: 30px;
    margin-top: 22px;
    margin-right: 45px;
  }
`;

const FundingBox = styled.div`
  display: flex;

  .album_img {
    margin-right: 53px;
    align-self: center;
    width: 35%;
    height: 100%;
    object-fit: cover;
  }
`;

export default MainPage;
