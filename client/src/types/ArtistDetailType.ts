export interface ArtistDetailType {
  stageName: string;
  agency: string;
  description: string;
  profileImageUrl: string;
  youtubeUrl: string;
  fanCafeUrl: string;
  snsUrl: string;
  artistVideo: videoUrls[];
}

export interface videoUrls {
  videoUrl: string;
}
