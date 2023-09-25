export interface Artist {
  id: number;
  profileImg: string;
  name: string;
  desc: string;
  youtubeUrl: string;
  fancafeUrl: string;
  snsUrl: string;
}

export interface Activity {
  albumImg: string;
  name: string;
  desc: string;
  startedAt: string;
  endedAt: string;
  targetAmount: number;
  fundingAmount: number;
  tracklistImg: string;
  teaserVideoUrl: string;
  albumCompositionImg: string;
}

export interface Investment {
  overview: {
    publisher: string;
    type: string;
    redemptionType: string;
    financingPurpose: string;
    pricePerAccount: number;
    minimumPrice: number;
    minimumFundingAmount: number;
    maximumFundingAmount: number;
    fundingStartDate: string;
    fundingEndDate: string;
    investmentPublishedDate: string;
    investmentMaturedDate: string;
  };
}

export interface PastActivities {
  PastActivity: {
    id: number;
    name: string;
    albumImg: string;
    publishedDate: string;
    revenuePercent: number;
    targetAmount: number;
    fundingAmount: number;
  };
}
