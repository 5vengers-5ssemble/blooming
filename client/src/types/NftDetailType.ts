export interface nftDetailType {
  id: number;
  title: string;
  thumbnailUrl: string;
  description: string;
  artist: {
    id: number;
    profileImg: string;
    name: string;
    desc: string;
    youtubeUrl: string;
    fanCafeUrl: string;
    snsUrl: string;
  };
  saleCount: number;
  nftSale: {
    totalNftCount: number;
    soldNftCount: number;
    totalNftAmount: number;
    soldNftAmount: number;
  };
  salePrice: number;
  purchaseStart: string;
  purchaseEnd: string;
  contractAddress: string;
}
