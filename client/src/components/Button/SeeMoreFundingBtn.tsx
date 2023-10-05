import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { ReactComponent as SeeMoreIcon } from '@assets/icons/see-more-funding.svg';

interface Props {
  btnTitle: string;
  type: string;
}

const SeeMoreFundingBtn: React.FC<Props> = ({ btnTitle, type }) => {
  const navigate = useNavigate();

  const goListPage = () => {
    navigate(`/${type}`);
  };

  return (
    <SeeMoreBox onClick={goListPage}>
      <SeeMoreBtn>
        <div className="see_more_text">{btnTitle} 전체보기</div>
        <SeeMoreIcon className="see_more_icon"></SeeMoreIcon>
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

  cursor: pointer;
`;

export default SeeMoreFundingBtn;
