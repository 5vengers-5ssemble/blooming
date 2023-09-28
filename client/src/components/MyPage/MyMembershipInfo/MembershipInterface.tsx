import { MainTitle } from '@style/common';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import MembershipList from './MembershipList';
import axiosTemp from '@api/apiControllerTemp';
import { NFTProcessApplication } from '@type/ApplicationList';
import Loading from '@components/Animation/Loading';

const MembershipInterface = () => {
  const [isMembership, setMembershipStatus] = useState<boolean>(false);
  const [membershipData, setMembershipData] =
    useState<NFTProcessApplication[]>();
  useEffect(() => {
    axiosTemp.get('/application-nft-inprogress').then((res) => {
      setMembershipData(res.data.applicationList);
      setMembershipStatus(res.data.canGenerateNewNft);
    });
  }, []);

  if (!membershipData) {
    return (
      <>
        <Loading />
      </>
    );
  }
  return (
    <Frame>
      <Title>
        <MembershipTitle>
          멤버쉽 신청 내역<div className="dot"></div>
        </MembershipTitle>
        {isMembership ? (
          <div className="subInfo">현재 진행중인 멤버쉽이 있어요</div>
        ) : (
          <>
            <Button>신청하기</Button>
          </>
        )}
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

const Button = styled.div`
  cursor: pointer;
  padding: 10px 15px;
  background-color: var(--main1-color);
  border-radius: 6px;
  color: var(--white-color);
`;
export default MembershipInterface;
