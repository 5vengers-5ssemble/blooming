import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

import { Frame } from '../MyMembershipInfo/MembershipInterface';
import { MainTitle } from '@style/common';
import Loading from '@components/Animation/Loading';
import PostSuccessAnimation from '@components/Animation/PostSuccessAnimation';
import MoreInfo, { BodyFrame } from './MoreInfo';
import { ReactComponent as ArrowSvg } from '@assets/icons/angle-right.svg';

import axiosTemp from '@api/apiControllerTemp';
import axios from '@api/apiController';
import {
  setLiveId,
  setLiveNickName,
  setLiveSessionId,
  setLiveTitle,
} from '@hooks/useLiveAuth';

//추후 쿠키에서 가져옴
const artistId = 2;

const liveGenerate = async (
  title: string,
  thumbnail: string,
): Promise<{ sessionId: string; liveId: number }> => {
  try {
    //back 코드로 바꾸면
    const response = await axios.post('/lives', {
      liveTitle: title,
      artistId, //artistId 변경
      //thumnail 추가
    });

    if (!response) {
      throw new Error('라이브 생성 요청에 실패했습니다.');
    }
    const data = response.data.results;
    const sessionId = '2'; //! [ 추후 변경 ] data.sessionId;
    const liveId = data.id;
    return { sessionId, liveId };
  } catch (error) {
    throw error;
  }
};

const OnLive = () => {
  const navigate = useNavigate();

  const [isLiveAvailable, setLiveAvailable] = useState<boolean>(false);
  const [registLoading, setRegistLoading] = useState<boolean>(false);
  const [sessionId, setSessionId] = useState<string>('');
  const [artistName, setArtistName] = useState<string>('');

  useEffect(() => {
    //임시
    axiosTemp.get('/application-funding-inprogress').then((res) => {
      setLiveAvailable(true);
    });
  }, []);

  const handleRegisterLive = async (
    title: string,
    thumbnail: string | null,
  ) => {
    setRegistLoading(true);

    try {
      const response = await liveGenerate(
        title,
        thumbnail ? thumbnail : 'imgfile',
      );
      setSessionId(response.sessionId);
      setLiveId(response.liveId);
      setLiveTitle(title);
      setArtistName('나중에바꿔야됨아티스트명'); //stageName response 에서 받아서 사용
    } catch (error) {
    } finally {
      setRegistLoading(false);
    }
  };

  const handleOnLive = () => {
    setLiveSessionId(sessionId);
    setLiveNickName(artistName);
    navigate(`/meeting-artist`);
  };

  return (
    <Frame>
      <Title>
        <MembershipTitle>
          LIVE ON<div className="dot"></div>
        </MembershipTitle>
        {!isLiveAvailable && (
          <div className="subInfo">라이브를 진행할 수 없어요</div>
        )}
      </Title>
      {isLiveAvailable ? (
        // isLiveAvailable이 true인 경우에만 아래 컴포넌트들을 렌더링
        <>
          {registLoading && <Loading />}
          {sessionId ? (
            <BodyFrame>
              <Animation>
                <PostSuccessAnimation />
              </Animation>

              <MeetingLink>
                준비가 전부 끝났습니다! 라이브를 시작해주세요
              </MeetingLink>
              <Button onClick={handleOnLive}>
                라이브 시작하기
                <ArrowSvg />
              </Button>
            </BodyFrame>
          ) : (
            <MoreInfo onRegisterLive={handleRegisterLive}></MoreInfo>
          )}
        </>
      ) : null}
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

const Animation = styled.div`
  width: 400px;
`;

const MeetingLink = styled.div`
  font-size: 20px;
  font-weight: bold;
  color: var(--main1-color);
`;

const Button = styled.div`
  width: fit-content;
  margin-top: 20px;
  padding: 10px 20px;
  border-radius: 6px;
  background-color: var(--main1-color);
  color: var(--white-color);
  display: flex;
  gap: 10px;
  align-items: center;
  justify-content: center;
  svg g path {
    fill: var(--white-color);
  }
`;

export default OnLive;
