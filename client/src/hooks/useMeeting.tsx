import { useEffect, useState, useRef } from 'react';
import {
  OpenVidu,
  Session,
  Publisher,
  StreamManager,
  PublisherProperties,
} from 'openvidu-browser';
import CONSOLE from '@utils/consoleColors';
import axios from '@api/openViduController';
import { Emotion, MeetingInfo } from '@type/MeetingInfo';
import { ARTIST } from '@components/common/constant';

const tmPose = window.tmPose;
import * as tmtype from '@teachablemachine/pose';

const OV = new OpenVidu();
const sessionId = 'SessionA';
const loggedInUserNickname = 'ksm';
const baseURL = 'https://teachablemachine.withgoogle.com/models/HwtR6uvJk/';
const modelURL = baseURL + 'model.json';
const metadataURL = baseURL + 'metadata.json';

async function createSession(sessionId: string) {
  const response = await axios.post(`/api/sessions/`, {
    customSessionId: sessionId,
  });
  return response.data;
}

async function createToken(sessionId: string) {
  const response = await axios.post(
    `/api/sessions/${sessionId}/connections`,
    {},
  );
  return response.data;
}

export function useMeeting(isArtist: boolean) {
  const [model, setModel] = useState<tmtype.CustomPoseNet | null>(null);
  const [webcam, setWebcam] = useState<tmtype.Webcam | null>(null);
  const [prediction, setPrediction] = useState<Emotion[]>([]);
  const videoRef = useRef<HTMLVideoElement | null>(null);

  const [meetingInfo, setMeetingInfo] = useState<MeetingInfo>({
    mySessionId: sessionId,
    myUserName: isArtist ? ARTIST : loggedInUserNickname,
    session: null,
    mainStreamManager: undefined,
    publisher: undefined,
    prevPublisher: undefined,
    subscribers: [],
    isArtist: isArtist,
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
      CONSOLE.info('loop 실행#)(*(');
      webcam.update();
      await predict();
      window.requestAnimationFrame(loop);
    }
  }

  async function predict() {
    if (model && webcam) {
      CONSOLE.info('predict- model 출력');

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

  useEffect(() => {
    // CONSOLE.info('나 업데이트중@!!!!');
  }, [prediction]);

  useEffect(() => {
    CONSOLE.info('세션을 시작합니다.');
    setMeetingInfo((prevState) => ({
      ...prevState,
      session: OV.initSession(),
    }));

    tmPose
      .load(modelURL, metadataURL)
      .then((model: tmtype.CustomPoseNet) => {
        CONSOLE.info('load 완료');
        console.log(model);
        setModel(model);
      })
      .catch((error: Error) => {
        CONSOLE.error('로드중 에러발생');
        console.log(error);
      });
  }, []);

  useEffect(() => {
    if (model) {
      CONSOLE.useEffectIn('model!!!!!!!');
      initWebcam();
    }
  }, [model]);

  useEffect(() => {
    if (webcam) {
      loop();
    }
  }, [webcam]);

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
            clientData: isArtist ? ARTIST : loggedInUserNickname,
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
    }
  }, [meetingInfo]);

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

  const getToken = async (newSessionId: string) => {
    const sessionId = await createSession(newSessionId).catch((error) => {
      processError(error, '세션 생성 중 오류 발생!');
    });
    return await createToken(sessionId).catch((error) => {
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
  };
}
