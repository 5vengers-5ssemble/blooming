export interface activeDetail {
  artist: artist;
  concert: activity;
  investment: investment;
  viewCounts: number[];
}

export interface artist {
  id: number;
  profileImg: string;
  name: string;
  desc: string;
  youtubeUrl: string;
  fancafeUrl: string;
  snsUrl: string;
}

export interface activity {
  id: number;
  albumImg: string;
  name: string;
  intro: string;
  desc: string;
  startedAt: string;
  endedAt: string;
  targetAmount: number;
  fundingAmount: number;
  tracklistImg: string;
  teaserVideoUrl: string;
  albumCompositionImg: string;
}

export interface investment {
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
  structure: string;
  goods: string;
}

export interface pastActivity {
  id: number;
  name: string;
  albumImg: string;
  publishedDate: string;
  revenuePercent: number;
  targetAmount: number;
  fundingAmount: number;
}
