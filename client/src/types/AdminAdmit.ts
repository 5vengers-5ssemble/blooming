import { ArtistRequestInfo } from './ArtistRequest';
import { FundAddInfo } from './ProcessInfo';

export interface ArtistInMembershipAdmit extends ArtistRequestInfo {
  id: number;
}
export interface MembershipAdmit {
  id: number;
  title: string;
  description: string;
  seasonStart: string;
  seasonEnd: string;
  purchaseStart: string;
  purchaseEnd: string;
  saleCount: number;
  salePrice: number;
  thumbnailUrl: string;
  applicationState: string;
  createdAt: string;
  modifiedAt: string;
  artist: ArtistInMembershipAdmit;
}

export interface ArtistAdmit {
  id: number;
  stageName: string;
  profileImageUrl: string;
  agency: string;
  applicationState: string;
  createdAt: string;
  modifiedAt: string;
  member: MemberInfo;
}

export interface MemberInfo {
  id: number | null;
  name: string | null;
}

export interface FundingAdmit extends FundAddInfo {
  id: number;
}
