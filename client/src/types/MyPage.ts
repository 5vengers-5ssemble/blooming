export interface MyPageInfo {
  profileInfo: ProfileInfo;
  profitInfo: ProfitInfo;
  settlementInfo: SettlementInfo;
}

export interface ProfileInfo {
  memberId: string;
  nickname: string;
  profileImg: string;
  isArtist: boolean;
}

export interface ProfitInfo {
  totalProfit: number;
  investForMonth: number;
  totalInvest: number;
}

export interface SettlementInfo {
  totalFundingCnt: number;
  settlementCompleteCnt: number;
}

export interface MyNftInfo {
  membershipId: string;
  name: string;
  artistName: string;
  seasonNum: number;
  profileImg: string;
}

export interface MyFundingInfo {
  fundingId: string;
  name: string;
  artistName: string;
  fundingImg: string;
}

export interface MySettleFundingInfo extends MyFundingInfo {
  profitRate: number;
}

export interface MyUnSettleFundingInfo extends MyFundingInfo {
  achiveRate: number;
}
