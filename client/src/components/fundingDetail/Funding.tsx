import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '@api/apiController';
import styled from 'styled-components';
import ProgressBarFrame from '@components/Button/ProgressBar';
import { artist, concert, investment } from '@type/ConcertDetail';
import { nanoid } from 'nanoid';
import PaymentPage from '@pages/PaymentPage/PaymentPage';

interface Props {
  artistData: artist;
  concertData: concert;
  investmentData: investment;
}

const Funding: React.FC<Props> = ({
  artistData,
  concertData,
  investmentData,
}) => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const [countState, setCount] = useState(1);
  // 모달 관련
  const showModal = () => {
    setModalOpen(true);
  };
  //결제 요청
  const orderIdentifier = nanoid();
  const handleFundingClick = () => {
    const requestBody = {
      // 멤버 id 수정 필요
      memberId: 1,
      artistId: `${artistData.id}`,
      // 프로젝트 타입 수정 필요
      projectType: 'concert',
      projectId: `${concertData.id}`,
      orderId: orderIdentifier,
      amount: `${investmentData.overview.pricePerAccount * countState}`,
    };

    axios
      // http://localhost:8080/api/v1
      .post('http://localhost:8080/api/v1/payments/temp', requestBody)
      .then((response) => {
        console.log('결제 정보 전송 성공:', response.data);
        // console.log(`리퀘스트바디:${requestBody.amount}`);
        showModal();
      })
      .catch((error) => {
        console.error('결제 정보 전송 실패:', error);
      });
  };

  // 숫자 증감 관련

  const handleIncrement = () => {
    setCount(countState + 1);
  };

  const handleDecrement = () => {
    if (countState > 0) {
      setCount(countState - 1);
    }
  };

  // 남은날짜 계산하는 부분
  const startDate = new Date(concertData.startedAt);
  const endDate = new Date(concertData.endedAt);
  const today = new Date();

  const remainedDays = Math.floor(
    (endDate.getTime() - today.getTime()) / (1000 * 60 * 60 * 24),
  );
  const totalDays = Math.floor(
    (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24),
  );

  return (
    <FundingBox>
      <img src={concertData.posterImg} alt="앨범 자켓" className="album_img" />
      <FundingInfo>
        <div className="album_title">
          {/* KIM JAEHWAN 5th Album : Empty Dream */}
          {concertData.name}
        </div>
        <div className="album_desc">
          {/* 폭넓은 보컬 스펙트럼과 특유의 매력적인 보이스로 수많은 리스너를
          매료시키고 있는 가수 김재환. 지난해 12월 미니앨범 [THE LETTER] 발매
          이후 약 9개월 만에 발표하는 김재환의 다섯번째 미니앨범. */}
          {concertData.desc}
        </div>
        <RateBox>
          <ProgressBox>
            <TextBox>
              <div className="bar_title">참여일</div>
              <div className="bar_info">{remainedDays} 일 남음</div>
            </TextBox>
            <ProgressBarFrame
              score={totalDays - remainedDays}
              total={totalDays}
              background="var(--main2-color)"
              height={'11px'}
            />
          </ProgressBox>
          <ProgressBox>
            <TextBox>
              <div className="bar_title second">달성액</div>
              <div className="bar_info second">
                {Math.ceil(
                  (concertData.fundingAmount / concertData.targetAmount) * 100,
                )}
                % 달성
              </div>
            </TextBox>
            <ProgressBarFrame
              score={concertData.fundingAmount}
              total={concertData.targetAmount}
              background="var(--main2-color)"
              height={'11px'}
            />
          </ProgressBox>
          {/* <FundingBtn>펀딩하기</FundingBtn> */}
          <BuyFund>
            <PriceBox>
              <div className="price_eth">
                {(
                  investmentData.overview.pricePerAccount * countState
                ).toLocaleString()}{' '}
                원
              </div>
              <div className="price_dollar">
                최소 펀딩 금액 :{' '}
                {investmentData.overview.pricePerAccount.toLocaleString()} 원
              </div>
            </PriceBox>
            <BtnBox>
              <CounterBtnBox>
                <button
                  id="decrement"
                  className="counter_btn"
                  onClick={handleDecrement}
                >
                  -
                </button>
                <span id="count">{countState}</span>
                <button
                  id="increment"
                  className="counter_btn"
                  onClick={handleIncrement}
                >
                  +
                </button>
              </CounterBtnBox>
              <PublishBtnBox onClick={handleFundingClick}>
                <button className="publish_btn">펀딩하기</button>
              </PublishBtnBox>
            </BtnBox>
          </BuyFund>
          {modalOpen && (
            <PaymentPage
              setModalOpen={setModalOpen}
              artistData={artistData}
              concertData={concertData}
              investmentData={investmentData}
              count={countState}
              orderIdentifier={orderIdentifier}
            />
          )}
        </RateBox>
      </FundingInfo>
    </FundingBox>
  );
};

const PublishBtnBox = styled.div`
  height: 46px;
  width: 75%;
  background: var(--Main, #3061b9);
  border-radius: 6px;
  cursor: pointer;
  text-align: center;

  .publish_btn {
    background: none;
    border: none;
    padding: 0;
    cursor: pointer;

    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
  }
`;
const CounterBtnBox = styled.div`
  margin-right: 15px;

  height: 46px;
  width: 25%;
  background: #c7c7c7;
  border-radius: 6px;

  text-align: center;

  #count {
    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
    margin: 0 40px;
  }

  .counter_btn {
    background: none;
    border: none;
    padding: 0;

    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
    cursor: pointer;
  }
`;
const BtnBox = styled.div`
  display: flex;
`;
const PriceBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 24px;

  .price_eth {
    color: #3061b9;
    font-size: 25px;
    font-weight: 700;
    line-height: 17px;
  }

  .price_dollar {
    font-size: 17px;
    font-weight: 500;
    line-height: 17px;
  }
`;
const BuyFund = styled.div`
  background: var(--White, #fdfdfd);
  box-shadow: 0px 0px 7px 0px rgba(48, 97, 185, 0.1);
  padding: 30px;
  margin-top: 40px;
  border-radius: 6px;
`;
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
