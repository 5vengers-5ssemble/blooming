import {
  Session,
  Publisher,
  Subscriber,
  StreamManager,
} from 'openvidu-browser'; // Import Session, Publisher, Subscriber

export interface MeetingInfo {
  mySessionId: null | string;
  myUserName: string;
  motionModelUrl: null | string;
  session: null | Session;
  mainStreamManager: Publisher | undefined;
  publisher: Publisher | undefined;
  prevPublisher: Publisher | undefined;
  subscribers: Subscriber[];
  isArtist: boolean;
  meetingTitle: string;
  liveId: number;
}

export interface Emotion {
  key: string;
  value: number;
}
