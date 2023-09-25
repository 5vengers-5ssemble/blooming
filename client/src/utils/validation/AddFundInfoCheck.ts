import { FundAddInfo } from '@type/ProcessInfo';

const validNoneCheck = (keyword: string) => {
  return true;
};

const validFundingTitleCheck = (keyword: string) => {
  return 0 < keyword.length && keyword.length <= 30;
};

const validEmailCheck = (keyword: string) => {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailRegex.test(keyword);
};

const validDepositCheck = (keyword: string) => {
  const isNumeric = /^[0-9]+$/.test(keyword);
  const hasCorrectLength = keyword.length === 10;
  return isNumeric && hasCorrectLength;
};

const validCompanyRegistrationNumber = (keyword: string) => {
  const numberPattern = /^[0-9]{10}$/; // 0에서 9까지의 숫자로 이루어진 10자리 문자열 패턴
  return numberPattern.test(keyword);
};

const validCompanyName = (keyword: string) => {
  return keyword.length >= 1;
};

const validTargetAmount = (keyword: string) => {
  const numericPart = keyword.replace(/\D/g, '');
  if (keyword.length !== numericPart.length) {
    return false;
  }
  const amount = parseInt(numericPart, 10);
  if (isNaN(amount) || amount < 500000 || amount > 100000000) {
    return false;
  }

  return true;
};

const validIntroduce = (keyword: string) => {
  return keyword.length >= 1;
};

const validTotalAmount = (keyword: string) => {
  const numericPart = keyword.replace(/\D/g, '');
  if (keyword.length !== numericPart.length) {
    return false;
  }

  return true;
};

// 문자열 필드의 검증 함수
const isNonEmptyString = (value: string): boolean => {
  return value.length > 0;
};

// 숫자 필드의 검증 함수
const isPositiveNumber = (value: number): boolean => {
  return value > 0;
};

// FundAddInfo 객체의 필드 검증 함수
const validateFundAddInfo = (info: FundAddInfo): boolean => {
  const { projectInfo, defaultInfo, policyInfo, refresentInfo, storyInfo } =
    info;

  return (
    isNonEmptyString(projectInfo.category) &&
    isNonEmptyString(projectInfo.makerInfo.makerName) &&
    isNonEmptyString(projectInfo.makerInfo.makerAddFile) &&
    isNonEmptyString(projectInfo.makerInfo.makerNum) &&
    isNonEmptyString(projectInfo.makerInfo.sealCertificate) &&
    isPositiveNumber(projectInfo.targetAmount) &&
    isNonEmptyString(defaultInfo.title) &&
    isNonEmptyString(defaultInfo.image) &&
    isNonEmptyString(defaultInfo.endDate) &&
    isNonEmptyString(defaultInfo.startDate) &&
    policyInfo.service &&
    policyInfo.refund &&
    isNonEmptyString(refresentInfo.refresentor) &&
    isNonEmptyString(refresentInfo.calculateInfo.email) &&
    isNonEmptyString(refresentInfo.calculateInfo.deposit) &&
    isNonEmptyString(refresentInfo.calculateInfo.bankbookImage) &&
    isPositiveNumber(storyInfo.budget) &&
    isNonEmptyString(storyInfo.moreInfo.album_desc) &&
    isNonEmptyString(storyInfo.moreInfo.album_img) &&
    isNonEmptyString(storyInfo.moreInfo.track_list) &&
    isNonEmptyString(storyInfo.summary) &&
    isNonEmptyString(storyInfo.teaser)
  );
};

export {
  isNonEmptyString,
  isPositiveNumber,
  validNoneCheck,
  validFundingTitleCheck,
  validEmailCheck,
  validDepositCheck,
  validCompanyRegistrationNumber,
  validCompanyName,
  validTargetAmount,
  validIntroduce,
  validTotalAmount,
  validateFundAddInfo,
};
