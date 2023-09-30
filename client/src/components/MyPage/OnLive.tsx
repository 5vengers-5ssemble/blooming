import { MainTitle } from '@style/common';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import axiosTemp from '@api/apiControllerTemp';

import { useNavigate } from 'react-router-dom';
import { Frame } from './MyMembershipInfo/MembershipInterface';

const OnLive = () => {
  const navigate = useNavigate();

  const [isLiveAvailable, setLiveAvailable] = useState<boolean>(false);

  useEffect(() => {
    axiosTemp.get('/application-funding-inprogress').then((res) => {
      setLiveAvailable(res.data.canGenerateNewFunding);
    });
  }, []);

  return (
    <Frame>
      <Title>
        <MembershipTitle>
          LIVE ON<div className="dot"></div>
        </MembershipTitle>
        {isLiveAvailable ? (
          <div className="subInfo">라이브를 진행할 수 없어요</div>
        ) : (
          <>
            <Button>신청하기</Button>
          </>
        )}
      </Title>
    </Frame>
  );
};

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
export default OnLive;
