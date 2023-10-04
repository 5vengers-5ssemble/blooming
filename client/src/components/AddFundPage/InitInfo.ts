import { CONCERT } from '@components/common/constant';
import { FundAddInfo } from '@type/ProcessInfo';

export const InitInfo: FundAddInfo = {
  projectInfo: {
    category: CONCERT,
    makerInfo: {
      licenseNumber: '',
      companyName: '',
      licenseImage: '',
      sealCertificate: '',
    },
    targetAmount: 0,
  },
  basicInfo: {
    thumbnail: '',
    title: '',
    endDate: '',
    startDate: '',
  },
  policyInfo: {
    service: false,
    refund: false,
  },
  settlementInfo: {
    representative: '',
    email: '',
    accountNumber: '',
    bankbookImage: '',
  },
  storyInfo: {
    budget: 0,
    moreInfo: {
      description: '',
      listImage: '',
      compositionImage: '',
    },
    introduction: '',
    teaser: '',
  },
};
