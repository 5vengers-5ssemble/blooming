import React, { useState } from 'react';
import styled from 'styled-components';

interface Props {
  btnTitle: string;
}

const SeeMoreFundingBtn: React.FC<Props> = ({ btnTitle }) => {
  return (
    <SeeMoreBox>
      <SeeMoreBtn>
        <div className="see_more_text">{btnTitle} 전체보기</div>
        <img
          src="../../src/assets/icons/see-more-funding.svg"
          alt=""
          className="see_more_icon"
        />
      </SeeMoreBtn>
    </SeeMoreBox>
  );
};

const SeeMoreBtn = styled.div`
  display: flex;
  justify-content: center;

  background-color: white;
  width: 190px;
  height: 45px;
  border: 1px solid #e1e1e1;
  border-radius: 40px;

  .see_more_icon {
    height: 22px;
    width: 22px;
    align-self: center;
  }

  .see_more_text {
    color: var(--Charcoal, #666);
    text-align: center;
    font-size: 14px;
    font-weight: 700;
    line-height: 45px;
  }
`;
const SeeMoreBox = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 30px;
`;

export default SeeMoreFundingBtn;
