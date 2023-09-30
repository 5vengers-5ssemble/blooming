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
  basicInfo: DefaultInfoInAdd;
  storyInfo: StoryInfoInAdd;
  policyInfo: PolicyInAdd;
  settlementInfo: RepresentInfoInAdd;
}

export type ProjectInfoInAdd = {
  category: string;
  makerInfo: MakerInfo;
  targetAmount: number;
};

export type MakerInfo = {
  licenseNumber: string;
  companyName: string;
  licenseImage: string;
  sealCertificate: string;
};

export type DefaultInfoInAdd = {
  thumbnail: string;
  startDate: string;
  endDate: string;
  title: string;
};

export type StoryInfoInAdd = {
  introduction: string;
  teaser: string;
  moreInfo: MoreInfo;
  budget: number;
};

export type MoreInfo = {
  description: string;
  listImage: string;
  compositionImage: string;
};

export type PolicyInAdd = {
  service: boolean;
  refund: boolean;
};

export type RepresentInfoInAdd = {
  representative: string;
  email: string;
  accountNumber: string;
  bankbookImage: string;
};
