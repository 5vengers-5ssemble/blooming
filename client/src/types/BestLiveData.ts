export interface bestLive {
  id: number;
  title: string;
  sessionId: string;
  numberOfViewers: number;
  thumbnailUrl: string;
  artist: {
    id: number;
    stageName: string;
    profileImageUrl: string;
  };
}

export interface onairLive {
  id: number;
  title: string;
  sessionId: string;
  thumbnailUrl: string;
  artist: {
    id: number;
    stageName: string;
    profileImageUrl: string;
  };
}
