import { MainTitle } from '@style/common';
import styled from 'styled-components';
import MembershipList from './MembershipList';

const MembershipInterface = () => {
  return (
    <Frame>
      <Title>
        <MembershipTitle>
          멤버쉽 승인 내역<div className="dot"></div>
        </MembershipTitle>
      </Title>

      <MembershipList />
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

export default MembershipInterface;
