import { stringList } from "aws-sdk/clients/datapipeline";

export interface MembershipInfo {
    title: string;
    description: string;
    seasonStart: string;
    seasonEnd: string;
    purchaseStart: string;
    purchaseEnd: string;
    saleCount: number | string;
    salePrice: number | string;
    thumbnailUrl: string;
    baseUri: string;
    privateKey: string;
}