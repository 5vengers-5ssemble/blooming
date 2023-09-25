import {
  Session,
  Publisher,
  Subscriber,
  StreamManager,
} from 'openvidu-browser'; // Import Session, Publisher, Subscriber

export interface MeetingInfo {
  mySessionId: string;
  myUserName: string;
  session: null | Session;
  mainStreamManager: Publisher | undefined;
  publisher: Publisher | undefined;
  prevPublisher: Publisher | undefined;
  subscribers: Subscriber[];
  isArtist: boolean;
}

export interface Emotion {
  key: string;
  value: number;
}
