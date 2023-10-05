export interface ArtistRequestInfo {
  name: string;
  agency: string;
  description: string;
  profileImageUrl: string;
  youtubeUrl: string;
  fanCafeUrl: string;
  snsUrl: string;
  artistVideo?: ArtistVideo[];
}

export interface ArtistVideo {
  id: number | null;
  videoUrl: string;
}
