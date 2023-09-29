import { MainTitle } from '@style/common';
import styled from 'styled-components';
import FundingList from './SettlementList';
import { useNavigate } from 'react-router-dom';

const SettlementInterface = () => {
  const navigate = useNavigate();

  return (
    <Frame>
      <Title>
        <MembershipTitle>
          정산 내역<div className="dot"></div>
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

const Button = styled.div`
  cursor: pointer;
  padding: 10px 15px;
  background-color: var(--main1-color);
  border-radius: 6px;
  color: var(--white-color);
`;
export default SettlementInterface;
