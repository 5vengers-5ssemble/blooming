import { MainTitle } from '@style/common';
import { useEffect, useState } from 'react';
import styled from 'styled-components';

import FundingList from './FundingList';
import { useNavigate } from 'react-router-dom';

const FundingInterface = () => {
  const navigate = useNavigate();
  const [isFundingAvailable, setFundingAvailable] = useState<boolean>(false);

  useEffect(() => {
    //추후 변경
    setFundingAvailable(true);
  }, []);

  return (
    <Frame>
      <Title>
        <MembershipTitle>
          펀딩 신청 내역<div className="dot"></div>
        </MembershipTitle>
        {!isFundingAvailable ? (
          <div className="subInfo">펀딩을 더 진행할 수 없어요</div>
        ) : (
          <>
            <Button onClick={() => navigate('/add-fund')}>신청하기</Button>
          </>
        )}
      </Title>

      <FundingList />
    </Frame>
  );
};

export const Frame = styled.div`
  padding: 70px 50px;
`;

const Title = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 50px;
  .subInfo {
    color: var(--main1-color);
    font-weight: 600;
  }
`;
const MembershipTitle = styled(MainTitle)`
  font-size: 30px;
`;

const Button = styled.div`
  cursor: pointer;
  padding: 10px 15px;
  background-color: var(--main1-color);
  border-radius: 6px;
  color: var(--white-color);
`;
export default FundingInterface;
