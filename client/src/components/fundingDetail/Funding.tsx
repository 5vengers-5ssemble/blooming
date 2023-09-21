import React from 'react';
import axios from '@api/apiController';
import styled from 'styled-components';
import ProgressBarFrame from '@components/Button/ProgressBar';

const Funding = () => {
  // const requestbody = {
  //   // 수정 필요
  //   memberId: number, // 로그인한 멤버 ID
  //   artistId: number, // 활동이나 콘서트와 연관된 아티스트의 ID
  //   projectType: String, // "activity"와 "concert"로 보내주시면 됩니다.
  //   projectId: number, // 해당 프로젝트의 ID
  //   orderId: String, // 임시로 생성된 주문 번호
  //   amount: number, // 금액
  // };

  const handleFundingClick = () => {
    axios
      .post('/payments/temp', requestbody)
      .then((response) => {
        console.log('결제 정보 전송 성공:', response.data);
      })
      .catch((error) => {
        console.error('결제 정보 전송 실패:', error);
      });
  };

  return (
    <FundingBox>
      <img
        src="src/assets/images/album_img.jfif"
        alt="앨범 자켓"
        className="album_img"
      />
      <FundingInfo>
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
            <ProgressBarFrame
              // score={data.nowProcess}
              score="124"
              // total={data.totalProcess}
              total="300"
              background="var(--main1-color)"
              height={'11px'}
            />
          </ProgressBox>
          <ProgressBox>
            <TextBox>
              <div className="bar_title second">달성액</div>
              <div className="bar_info second">72% 달성</div>
            </TextBox>
            <ProgressBarFrame
              // score={data.nowProcess}
              score="234"
              // total={data.totalProcess}
              total="300"
              background="var(--main1-color)"
              height={'11px'}
            />
          </ProgressBox>
          <FundingBtn onClick={handleFundingClick}>펀딩하기</FundingBtn>
        </RateBox>
      </FundingInfo>
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
  margin-top: 30px;
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
  margin-right: 35px;

  .bar_info {
    font-size: 14px;
    font-weight: 500;
    line-height: 17px;
  }
  .bar_title {
    font-size: 16px;
    font-weight: 700;
    line-height: 17px;
  }

  .second {
    margin-top: 16px;
  }
`;

const RateBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 49px;
`;

const FundingInfo = styled.div`
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

export default Funding;
