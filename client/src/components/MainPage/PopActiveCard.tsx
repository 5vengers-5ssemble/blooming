import React from 'react';
import styled from 'styled-components';

const PopActiveCard = () => {
  return (
    <ActiveCard>
      <img
        src="../../src/assets/images/pop_active_img1.jfif"
        alt=""
        className="active_img"
      />
      <div className="active_title">The Golden Hour: under the..</div>
      <ActiveInfo>
        <div className="hidden">243%</div>
        <div className="active_artist">아이유 | 앨범활동</div>
        <div className="active_funding_percent">243%</div>
      </ActiveInfo>
    </ActiveCard>
  );
};

const ActiveInfo = styled.div`
  display: flex;
  margin-top: 15px;
  justify-content: space-between;
  padding: 0 10px;

  .hidden {
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;
    opacity: 0;
  }

  .active_funding_percent {
    color: var(--Main, #3061b9);
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;
  }

  .active_artist {
    color: var(--Charcoal, #666);
    font-size: 13px;
    font-weight: 500;
    line-height: 17px;
    justify-self: center;
  }
`;
const ActiveCard = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  border: 0.5px solid rgba(0, 0, 0, 0.15);
  background: #fff;

  height: 400px;
  width: 300px;

  .active_title {
    text-align: center;
    font-size: 20px;
    font-weight: 700;
    line-height: 25px;

    margin-top: 17px;
  }

  .active_img {
    object-fit: cover;
    height: 300px;
    width: 100%;
    border-radius: 6px;
  }
`;

export default PopActiveCard;
