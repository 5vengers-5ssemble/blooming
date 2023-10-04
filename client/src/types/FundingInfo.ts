export interface ArtistInfo {
  artistImg: string;
  artistName: string;
  artistDesc: string;
}

export interface FundingInfo {
  title: string;
  desc: string;
  proifleImg: string;
  startDate: string;
  endDate: string;
  totalProcess: number;
  nowProcess: number;
}

export interface AlbumDetailInFunding {
  albumDesc: string;
  tracklistImg: string;
  teaserVideoUrl: string;
  // videoViewCnt??
  albumCompositionImg: string;
}

export interface RevenueAnalysisInFunding {
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
}

export interface OtherActionInFunding {
  actionVideoUrl1?: string;
  actionVideoUrl2?: string;
  actionVideoUrl3?: string;
  actionVideoUrl4?: string;
  actionVideoUrl5?: string;

  actionProgramUrl1?: string;
  actionProgramImg1?: string;
  actionProgramUrl2?: string;
  actionProgramImg2?: string;
  actionProgramUrl3?: string;
  actionProgramImg3?: string;
  actionProgramUrl4?: string;
  actionProgramImg4?: string;
  actionProgramUrl5?: string;
  actionProgramImg5?: string;
  actionProgramUrl6?: string;
  actionProgramImg6?: string;
  actionProgramUrl7?: string;
  actionProgramImg7?: string;
  actionProgramUrl8?: string;
  actionProgramImg8?: string;
  actionProgramUrl9?: string;
  actionProgramImg9?: string;
  actionProgramUrl10?: string;
  actionProgramImg10?: string;
}

export interface ArtistPortfolioInFunding {
  youtubeUrl?: string;
  youtubeUrl2?: string;
  fancafeUrl?: string;
  snsUrl1?: string;
  snsUrl2?: string;
  snsUrl3?: string;
}
