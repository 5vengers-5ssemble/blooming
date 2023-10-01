import React from 'react';
import styled from 'styled-components';

const PopConcertCard = () => {
  return (
    <ConcertCard>
      <img
        src="../../src/assets/images/pop_active_img1.jfif"
        alt=""
        className="concert_img"
      />
      <ConcertInfo>
        <div className="concert_title">The Golden Hour: under the..</div>
        {/* <div className="hidden">243%</div> */}
        <div className="concert_artist">아이유 | 앨범활동</div>
        <div className="concert_funding_percent">243%</div>
      </ConcertInfo>
    </ConcertCard>
  );
};

const ConcertInfo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 15px;
  padding: 0 10px;

  .hidden {
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;
    opacity: 0;
  }

  .concert_funding_percent {
    color: var(--Main, #3061b9);
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;
    margin-top: 120px;
    align-self: end;
  }

  .concert_artist {
    color: var(--Charcoal, #666);
    font-size: 13px;
    font-weight: 500;
    line-height: 17px;
    justify-self: center;
    margin-top: 15px;
  }
`;
const ConcertCard = styled.div`
  display: flex;
  border-radius: 10px;
  border: 0.5px solid rgba(0, 0, 0, 0.15);
  background: #fff;

  height: 300px;
  width: 400px;

  .concert_title {
    text-align: center;
    font-size: 20px;
    font-weight: 700;
    line-height: 25px;

    margin-top: 17px;
  }

  .concert_img {
    object-fit: cover;
    height: 300px;
    width: 100%;
    border-radius: 6px;
  }
`;

export default PopConcertCard;
