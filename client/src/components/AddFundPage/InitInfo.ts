import { CONCERT } from '@components/common/constant';
import { FundAddInfo } from '@type/ProcessInfo';

export const InitInfo: FundAddInfo = {
  projectInfo: {
    category: CONCERT,
    makerInfo: {
      makerName: '',
      makerAddFile: '',
      makerNum: '',
      sealCertificate: '',
    },
    targetAmount: 0,
  },
  defaultInfo: {
    title: '',
    image: '',
    endDate: '',
    startDate: '',
  },
  policyInfo: {
    service: false,
    refund: false,
  },
  refresentInfo: {
    refresentor: '',
    calculateInfo: {
      email: '',
      deposit: '',
      bankbookImage: '',
    },
  },
  storyInfo: {
    budget: 0,
    moreInfo: {
      album_desc: '',
      album_img: '',
      track_list: '',
    },
    summary: '',
    teaser: '',
  },
};
