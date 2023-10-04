export interface ongoingActivity {
  isExists: boolean;
  activity: {
    endedAt: string;
    fundingAmount: number;
    id: number;
    introduction: string;
    startedAt: string;
    targetAmount: number;
    thumbnail: string;
    title: string;
  };
}

export interface ongoingConcert {
  isExists: boolean;
  concert: {
    endedAt: string;
    fundingAmount: number;
    id: number;
    introduction: string;
    startedAt: string;
    targetAmount: number;
    thumbnail: string;
    title: string;
  };
}
