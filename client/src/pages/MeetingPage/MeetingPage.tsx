import { useEffect, useRef, useState } from 'react';
import styled, { keyframes } from 'styled-components';
import { useNavigate } from 'react-router-dom';

import { ARTIST, EMOTION_LIST } from '@components/common/constant';
import UserVideoComponent from '@components/Meeting/UserVideoComponent';
import { useMeeting } from '@hooks/useMeeting';
import { Emotion } from '@type/MeetingInfo';
import { ReactComponent as CameraOff } from '@assets/icons/camera-off.svg';
import { ReactComponent as CameraOn } from '@assets/icons/camera-on.svg';
import { ReactComponent as HideCamera } from '@assets/icons/eye-slash.svg';
import { ReactComponent as ShowCamera } from '@assets/icons/eye.svg';
import { ReactComponent as NoticeSvg } from '@assets/icons/megaphone.svg';
import { ReactComponent as LiveSvg } from '@assets/icons/youtube-logo.svg';
import { ReactComponent as ArrowLeft } from '@assets/icons/arrow-left.svg';
import { ReactComponent as ExitSvg } from '@assets/icons/sign-out.svg';

const MeetingName = '나 김아무개 아티스트가 여는 콘서트다!';
const MAX_EMOTIONS_COUNT = 20; // 최대 Emotion 갯수

const MeetingPage = ({ isArtist }: { isArtist: boolean }) => {
  const navigate = useNavigate();
  const {
    webcam,
    setWebcam,
    initWebcam,
    meetingInfo,
    videoOption,
    isTokenRequested,
    handleCameraOnOff,
    handleStreamCreated,
    handleStreamDestroyed,
    handleException,
    getToken,
    prediction,
  } = useMeeting(isArtist);

  const [notArtistCamera, setNotArtistCamera] = useState<boolean>(false);
  const [onMyCamera, setMyCamera] = useState<boolean>(true);
  const [showNotice, setShowNotice] = useState<boolean>(true);
  const [nowEmotion, setNowEmotion] = useState<string>('');

  const prevEmotionRef = useRef<string[]>([]);
  const [showEmotions, setShowEmotions] = useState<string[]>([]);

  useEffect(() => {
    if (prediction.length !== 0) {
      console.log('PREDICTION👩👩👩 : ', prediction);

      const max = findMaxEmotion(prediction);
      let newEmotion = '';
      if (max.key === EMOTION_LIST.SHAKE) {
        newEmotion = 'src/assets/reaction/heart.png';
      } else {
        newEmotion = 'src/assets/reaction/thumb.png';
      }
      // showEmotions 리스트에 현재 Emotion 추가
      setShowEmotions((prevEmotions) => {
        const updatedEmotions = [...prevEmotions, newEmotion].slice(
          -MAX_EMOTIONS_COUNT,
        );
        // 이전 Emotion 저장 업데이트
        prevEmotionRef.current = updatedEmotions;
        return updatedEmotions;
      });

      setNowEmotion(newEmotion);
    }
  }, [prediction]);

  // 이전 Emotion 중 가장 오래된 것을 삭제
  useEffect(() => {
    if (prevEmotionRef.current.length > MAX_EMOTIONS_COUNT) {
      prevEmotionRef.current.shift();
    }
  }, [nowEmotion]);

  const findMaxEmotion = (
    arr: Array<Emotion>,
  ): {
    key: string;
    value: number;
  } => {
    return arr.reduce((prev, curr) => (curr.value > prev.value ? curr : prev));
  };

  const handleVisibleMyCamera = () => {
    setNotArtistCamera(!notArtistCamera);
  };

  const handleCamera = () => {
    // if (onMyCamera) {
    //   //끄는거
    //   setWebcam(null);
    // } else {
    //   console.log(webcam);
    //   initWebcam();
    // }
    handleCameraOnOff({ onMyCamera: !onMyCamera });
    setMyCamera(!onMyCamera);
  };
  const handlePageOut = () => {
    navigate(-1);
  };

  const handleNoticeInfo = () => {
    setShowNotice(!showNotice);
  };

  const handleExit = () => {
    //나가기 처리
  };

  // 아티스트일 경우!!
  if (meetingInfo.isArtist) {
    return (
      <MeetingFrame>
        <UserVideoComponent
          nickname={meetingInfo.myUserName}
          streamManager={meetingInfo.publisher}
          isMain={true}
        />
        <Buttons>
          <Button className="exit" onClick={handleExit}>
            <ExitSvg />
            종료하기
          </Button>
        </Buttons>
        <NoticeBoard>
          {showNotice ? (
            <span>
              <LiveSvg />
              {MeetingName}
            </span>
          ) : (
            `현재 ${meetingInfo.subscribers.length}명이 시청 중입니다`
          )}
          <NoticeSvg onClick={handleNoticeInfo} />
        </NoticeBoard>
        {/* 애니메이션을 적용한 이미지 */}
        {showEmotions.map((emotion, index) => (
          <FloatingImage
            key={index}
            left={Math.random() * 80} // 랜덤한 가로 위치 설정
          >
            <img
              src={emotion}
              alt="Emotion"
              style={{ width: '50px', height: '50px' }}
            />
          </FloatingImage>
        ))}

        {/* 현재 Emotion 표시 */}
        {nowEmotion && (
          <FloatingImage
            left={Math.random() * 70} // 랜덤한 가로 위치 설정
          >
            <img
              src={nowEmotion}
              alt="Emotion"
              style={{ width: '40px', height: '40px' }}
            />
          </FloatingImage>
        )}
      </MeetingFrame>
    );
  }

  //일반 사용자의 경우
  return (
    <MeetingFrame>
      <div className="navigateBtn" onClick={handlePageOut}>
        <ArrowLeft />
      </div>
      {/* 아티스트만 띄우기 */}
      {meetingInfo.subscribers
        .filter((sub) => {
          if (sub.stream.connection?.data) {
            return JSON.parse(sub.stream.connection.data).clientData === ARTIST;
          }
          return false;
        })
        .slice(0, 1)
        .map((sub, idx) => (
          <div
            key={idx}
            className=""
            onClick={() => {
              console.log('>> print sub, ', sub);
            }}
          >
            <UserVideoComponent
              nickname={sub.stream.streamId}
              streamManager={sub}
              isMain={true}
            />
          </div>
        ))}
      <NoticeBoard>
        {showNotice ? (
          <span>
            <LiveSvg />
            {MeetingName}
          </span>
        ) : (
          `현재 ${meetingInfo.subscribers.length}명이 시청 중입니다`
        )}
        <NoticeSvg onClick={handleNoticeInfo} />
      </NoticeBoard>
      {notArtistCamera ? (
        <MyCamera>
          <div className="buttons">
            <Button className="hideCamera" onClick={handleCamera}>
              <CameraOff />
              {onMyCamera ? '카메라 끄기' : '카메라 키기'}
            </Button>
            <Button className="offCamera" onClick={handleVisibleMyCamera}>
              <HideCamera />
              숨기기
            </Button>
          </div>
          <UserVideoComponent
            nickname={meetingInfo.myUserName}
            streamManager={meetingInfo.publisher}
          />
        </MyCamera>
      ) : (
        <Buttons>
          <div className="settingBtn" onClick={handleVisibleMyCamera}>
            <span className="text">내 화면 보기</span>
            <div className="settingBackgroud">
              <ShowCamera />
            </div>
          </div>
          <div className="settingBtn" onClick={handleCamera}>
            <span className="text">
              {onMyCamera ? '카메라 끄기' : '카메라 키기'}
            </span>
            <div className="settingBackgroud">
              {onMyCamera ? <CameraOff /> : <CameraOn />}
            </div>
          </div>
        </Buttons>
      )}
      {/* 애니메이션을 적용한 이미지 */}
      {showEmotions.map((emotion, index) => (
        <FloatingImage
          key={index}
          left={Math.random() * 80} // 랜덤한 가로 위치 설정
        >
          <img
            src={emotion}
            alt="Emotion"
            style={{ width: '50px', height: '50px' }}
          />
        </FloatingImage>
      ))}

      {/* 현재 Emotion 표시 */}
      {nowEmotion && (
        <FloatingImage
          left={Math.random() * 70} // 랜덤한 가로 위치 설정
        >
          <img
            src={nowEmotion}
            alt="Emotion"
            style={{ width: '40px', height: '40px' }}
          />
        </FloatingImage>
      )}
    </MeetingFrame>
  );
};

const NoticeBoard = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  z-index: 1;
  position: absolute;
  top: 15px;
  right: 20px;
  color: var(--white-color);
  font-size: 15px;
  span {
    width: 280px;
    height: fit-content;
    padding: 5px 14px;
    background-color: #ffffffba;
    border-radius: 6px;
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    font-weight: 600;
    color: var(--black-color);
  }

  svg {
    cursor: pointer;
  }
`;
const MeetingFrame = styled.div`
  margin: 0px -280px -100px;
  display: flex;
  justify-content: center;
  min-height: 100dvh;
  background-color: var(--black-color);

  .navigateBtn {
    cursor: pointer;
    position: absolute;
    top: 10px;
    left: 10px;
  }
`;
const Buttons = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
  gap: 10px;
  z-index: 1;
  position: absolute;
  bottom: 20px;
  right: 20px;

  .settingBackgroud {
    background-color: var(--main3-color);
    border-radius: 50%;
    width: 40px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .isNotShow {
    display: flex;
    gap: 6px;
    flex-direction: column;
  }

  .settingBtn {
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 10px;
    color: var(--white-color);
    font-size: 12px;
  }

  .exit {
    background-color: var(--error-color);
    color: white;
    font-weight: 600;
    font-size: 15px;
  }
`;
const Button = styled.button`
  cursor: pointer;
  border: none;
  background-color: var(--main3-color);
  padding: 10px 10px;
  width: 160px;
  border-radius: 6px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  font-weight: 600;
`;

const MyCamera = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
  position: absolute;
  bottom: 0;
  right: 0;
  width: 280px;

  .buttons {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: none; /* 기본적으로 숨겨짐 */
  }

  &:hover .buttons {
    display: flex; /* hover 시 버튼 표시 */
    flex-direction: column;
    gap: 10px;
    z-index: 2;
  }
`;
const moveUp = keyframes`
  0% {
    transform: translateY(0%);
    opacity: 0;
  }
  10% {
    opacity: 1;
  }
  90% {
    opacity: 1;
  }
  100% {
    transform: translateY(100%);
    opacity: 1;
  }
`;

interface FloatingImageProps {
  left: number;
}

const FloatingImage = styled.div<FloatingImageProps>`
  position: absolute;
  bottom: 80px;
  left: ${({ left }) => `${left}%`};
  transform: translateY(100%);
  opacity: 0;
  animation: ${moveUp} 3s ease-in-out forwards;

  & + & {
    animation-delay: 3s; // 애니메이션 딜레이 추가
  }
`;

export default MeetingPage;
