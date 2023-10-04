import { MainTitle } from '@style/common';
import styled from 'styled-components';
import FundingList from './FundingList';

const FundingInterface = () => {
  return (
    <Frame>
      <Title>
        <MembershipTitle>
          펀딩 승인 내역<div className="dot"></div>
        </MembershipTitle>
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

export default FundingInterface;
