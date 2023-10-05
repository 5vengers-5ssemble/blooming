import { useEffect, useState, useRef } from 'react';
import {
  OpenVidu,
  Session,
  Publisher,
  StreamManager,
  PublisherProperties,
} from 'openvidu-browser';
import CONSOLE from '@utils/consoleColors';
import axios from '@api/apiController';
import { Emotion, MeetingInfo } from '@type/MeetingInfo';
import {
  ACCESS_KEY,
  ARTIST,
  LIVE_ID,
  LIVE_NICKNAME,
  SESSION_ID,
} from '@components/common/constant';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { emojiSUB, errorSUB } from '../socket/socketSubscribe';
import { CompatClient } from '@stomp/stompjs';

const tmPose = window.tmPose;
import * as tmtype from '@teachablemachine/pose';
import { getCookie } from './useLiveAuth';
import { EmojiMessage } from '@type/SocketEmoji';
import { useNavigate } from 'react-router-dom';

const OV = new OpenVidu();
// const loggedInUserNickname = 'ksm';

async function createSession(sessionId: null | string) {
  const response = await axios.post(`/lives/sessions`, {
    customSessionId: sessionId,
  });
  return response.data;
}

async function createToken(sessionId: string) {
  const response = await axios.post(
    `/lives/sessions/${sessionId}/connections`,
    {},
  );
  return response.data;
}

// ================================

export function useMeeting(isArtist: boolean, liveId: string | undefined) {
  const navigate = useNavigate();

  const [model, setModel] = useState<tmtype.CustomPoseNet | null>(null);
  const [webcam, setWebcam] = useState<tmtype.Webcam | null>(null);
  const [prediction, setPrediction] = useState<Emotion[]>([]);
  const [emoji, setEmoji] = useState<EmojiMessage | null>(null);

  const [meetingInfo, setMeetingInfo] = useState<MeetingInfo>({
    mySessionId: null,
    myUserName: '',
    motionModelUrl: null,
    session: null,
    mainStreamManager: undefined,
    publisher: undefined,
    prevPublisher: undefined,
    subscribers: [],
    isArtist: isArtist,
    meetingTitle: '',
    liveId: -1,
  });

  const [videoOption, setVideoOption] = useState<PublisherProperties>({
    audioSource: undefined,
    videoSource: undefined,
    publishAudio: false, // Set to !isArtist as needed
    publishVideo: true,
    resolution: '640x480',
    frameRate: 30,
    insertMode: 'APPEND',
    mirror: true,
  });

  // ============== [useState END] ==============

  const isTokenRequested = useRef(false);

  async function initWebcam() {
    // Convenience function to setup a webcam
    const size = 200;
    const flip = true; // whether to flip the webcam
    const newWebcam = new tmPose.Webcam(size, size, flip); // width, height, flip
    await newWebcam.setup(); // request access to the webcam
    await newWebcam.play();

    setWebcam(newWebcam);
  }

  async function loop() {
    if (webcam) {
      // CONSOLE.motion('loop 실행');
      webcam.update();
      await predict();
      window.requestAnimationFrame(loop);
    }
  }

  async function predict() {
    if (model && webcam) {
      // CONSOLE.motion('predict- model 출력');

      const maxPredictions = model.getTotalClasses();
      const accumulatedPredictions: { [key: string]: number[] } = {};

      // 3초 동안 예측값을 누적
      const startTime = Date.now();
      while (Date.now() - startTime < 3000) {
        const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
        const prediction = await model.predict(posenetOutput);

        for (let i = 0; i < maxPredictions; i++) {
          const key = prediction[i].className;
          const probability = prediction[i].probability;

          if (!accumulatedPredictions[key]) {
            accumulatedPredictions[key] = [];
          }
          accumulatedPredictions[key].push(probability);
        }
      }

      // 누적된 예측값을 평균 계산
      const averagedPredictions: Emotion[] = [];
      for (const key in accumulatedPredictions) {
        const values = accumulatedPredictions[key];
        const average =
          values.reduce((sum, value) => sum + value, 0) / values.length;

        averagedPredictions.push({
          key,
          value: average,
        });
      }

      // 예측 결과 저장
      setPrediction(averagedPredictions);
    }
  }

  // ==================== Socket Connect START ====================

  const apiURL = import.meta.env.VITE_APP_SERVER;
  const wsUrl = `${apiURL}/ws/blooming`;
  const accessToken = getCookie(ACCESS_KEY);

  const socketClient = useRef<CompatClient | null>(null);

  const socketConnectHandler = () => {
    socketClient.current = Stomp.over(() => {
      const socket = new SockJS(wsUrl);
      return socket;
    });

    socketClient.current.connect(
      {
        Authorization: `Bearer ${accessToken}`,
        sessionId: meetingInfo.mySessionId,
        liveUserName: meetingInfo.myUserName,
      },
      () => {
        CONSOLE.socket('connected!!');
        emojiSUB(socketClient, setEmoji, meetingInfo.mySessionId);
        errorSUB(socketClient, (error: any) => {
          console.log(error);
        });
      },
      (error: any) => {
        console.log(error);
      },
    );
  };

  // ==================== Socket Connect END ====================

  // ********** [START] INIT COMPONENT **********

  useEffect(() => {
    CONSOLE.info('세션을 시작합니다.');

    // 1. Openvidu initSession
    setMeetingInfo((prevState) => ({
      ...prevState,
      session: OV.initSession(),
    }));

    // 2. 입장 정보 (motionModelUrl, liveId) 가져오기
    axios
      .get(`/lives/${liveId}/enter`)
      .then(({ data }) => {
        CONSOLE.axios(`GET /lives/${liveId}/enter`);
        console.log(data);
        setMeetingInfo((prev) => ({
          ...prev,
          mySessionId: data.results.sessionId,
          motionModelUrl: data.results.motionModelUrl,
          myUserName: data.results.liveUserName,
          liveId: Number(liveId),
        }));
      })
      .catch((error) => {
        alert('접근 권한이 없습니다.');
        navigate('/live'); // 페이지 이동
      });
  }, []);

  // ********** [END] INIT COMPONENT **********

  // ********** [useEffect] meetingInfo.motionModelUrl **********
  useEffect(() => {
    const modelURL = meetingInfo.motionModelUrl + 'model.json';
    const metadataURL = meetingInfo.motionModelUrl + 'metadata.json';

    tmPose
      .load(modelURL, metadataURL)
      .then((model: tmtype.CustomPoseNet) => {
        CONSOLE.motion('load 완료');
        // console.log(model);
        setModel(model);
      })
      .catch((error: Error) => {
        CONSOLE.error('로드중 에러발생');
        console.log(error);
      });
  }, [meetingInfo.motionModelUrl]);

  // ********** [useEffect] model **********
  useEffect(() => {
    if (model) {
      // CONSOLE.useEffectIn('model!!!!!!!');
      initWebcam();
    }
  }, [model]);

  // ********** [useEffect] webcam **********
  useEffect(() => {
    if (webcam) {
      loop();
    }
  }, [webcam]);

  // useEffect(() => {
  //   CONSOLE.info('나 업데이트중@!!!!');
  // }, [prediction]);

  // ********** [useEffect] videoOption **********
  useEffect(() => {
    CONSOLE.useEffectIn('MeetingPage_videoOption');
    const newPublisher = OV.initPublisher(undefined, videoOption);
    CONSOLE.info('newPublisher!');
    console.log(newPublisher);
    CONSOLE.setCalled('meetingInfo');
    setMeetingInfo((prevState) => ({
      ...prevState,
      mainStreamManager: newPublisher,
      publisher: newPublisher,
    }));
  }, [videoOption]);

  // ********** [useEffect] meetingInfo.mySessionId **********
  useEffect(() => {
    if (meetingInfo.session && !isTokenRequested.current) {
      CONSOLE.info('서버에 토큰을 요청합니다.');
      isTokenRequested.current = true;
      const mySession = meetingInfo.session;

      mySession.on('streamCreated', handleStreamCreated);
      mySession.on('streamDestroyed', handleStreamDestroyed);
      mySession.on('exception', handleException);

      getToken(meetingInfo.mySessionId).then((token) => {
        mySession
          .connect(token, {
            clientData: meetingInfo.myUserName,
            isArtist: isArtist,
          })
          .then(async () => {
            const publisher = await OV.initPublisherAsync(
              undefined,
              videoOption,
            );

            mySession && mySession.publish(publisher);
            setMeetingInfo((prevState) => ({
              ...prevState,
              mainStreamManager: publisher,
              publisher: publisher,
              prevPublisher: undefined,
            }));
          });
      });

      // 3. 소켓 연결
      socketConnectHandler();
    }
  }, [meetingInfo.mySessionId]);

  // ==================== Function Definitions ====================

  const handleCameraOnOff = ({ onMyCamera }: { onMyCamera: boolean }) => {
    setVideoOption((prevState) => ({
      ...prevState,
      publishVideo: onMyCamera,
    }));
    console.log('camera!!', videoOption, onMyCamera);
  };

  const handleStreamCreated = (event: any) => {
    CONSOLE.event('누군가 참여했습니다!!!!!');
    const subscriber = meetingInfo.session?.subscribe(event.stream, undefined);
    const subscribers = meetingInfo.subscribers;
    if (subscriber) {
      subscribers.push(subscriber);
    }

    setMeetingInfo((prevState) => ({
      ...prevState,
      subscribers,
    }));
  };

  const handleStreamDestroyed = (event: any) => {
    CONSOLE.event('누군가 나갔습니다!');
    deleteSubscriber(
      event.stream.streamManager,
      meetingInfo.subscribers,
      setMeetingInfo,
    );
  };

  const handleException = (exception: any) => {
    CONSOLE.event('비동기적 에러가 발생했습니다!');
    console.warn(exception);
  };

  const getToken = async (newSessionId: null | string) => {
    const data = await createSession(newSessionId).catch((error) => {
      processError(error, '세션 생성 중 오류 발생!');
    });
    CONSOLE.info('create Seesion 결과 -->');
    return await createToken(data.results.sessionId).catch((error) => {
      processError(error, '커넥션 생성 중 오류 발생!');
    });
  };

  const processError = (error: any, message: string) => {
    CONSOLE.error('==== ERROR OCCURED!! ====');
    CONSOLE.error(message);
    console.log(error);
  };

  const deleteSubscriber = (
    streamManager: StreamManager,
    subscribers: StreamManager[],
    setMeetingInfo: React.Dispatch<React.SetStateAction<any>>,
  ) => {
    const index = subscribers.indexOf(streamManager);
    if (index > -1) {
      subscribers.splice(index, 1);
      setMeetingInfo((prevState: MeetingInfo) => ({
        ...prevState,
        subscribers: subscribers,
      }));
    }
  };

  return {
    meetingInfo,
    videoOption,
    isTokenRequested,
    handleCameraOnOff,
    handleStreamCreated,
    handleStreamDestroyed,
    handleException,
    getToken,
    prediction,
    webcam,
    setWebcam,
    initWebcam,
    emoji,
    setEmoji,
    socketClient,
  };
}
