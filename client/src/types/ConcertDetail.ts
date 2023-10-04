export interface concertDetail {
  artist: artist;
  concert: concert;
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

export interface concert {
  id: number;
  posterImg: string;
  name: string;
  intro: string;
  desc: string;
  startedAt: string;
  endedAt: string;
  targetAmount: number;
  fundingAmount: number;
  setlistImg: string;
  teaserVideoUrl: string;
  concertGoodsImg: string;
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

export interface pastConcert {
  id: number;
  name: string;
  posterImg: string;
  publishedDate: string;
  revenuePercent: number;
  targetAmount: number;
  fundingAmount: number;
}
