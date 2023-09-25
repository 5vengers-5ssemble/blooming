export interface ProcessInfo {
  id: number;
  title: string;
  description: string;
  profileImg: string;
  startDate: string;
  endDate: string;
  totalProcess: number;
  nowProcess: number;
}

export interface LiveInfo {
  id: number;
  title: string;
  sessionId: string;
  artist: {
    id: number;
    profileImageUrl: string | null;
    stageName: string;
  };
}

export interface FundAddInfo {
  projectInfo: ProjectInfoInAdd;
  defaultInfo: DefaultInfoInAdd;
  storyInfo: StoryInfoInAdd;
  policyInfo: PolicyInAdd;
  refresentInfo: RepresentInfo;
}

export type ProjectInfoInAdd = {
  category: string;
  makerInfo: MakerInfo;
  targetAmount: number;
};

export type MakerInfo = {
  makerNum: string;
  makerName: string;
  makerAddFile: string;
  sealCertificate: string;
};

export type DefaultInfoInAdd = {
  image: string;
  startDate: string;
  endDate: string;
  title: string;
};

export type StoryInfoInAdd = {
  summary: string;
  teaser: string;
  moreInfo: MoreInfo;
  budget: number;
};

export type MoreInfo = {
  album_desc: string;
  track_list: string;
  album_img: string;
};

export type PolicyInAdd = {
  service: boolean;
  refund: boolean;
};

export type RepresentInfo = {
  refresentor: string;
  calculateInfo: {
    email: string;
    deposit: string;
    bankbookImage: string;
  };
};
