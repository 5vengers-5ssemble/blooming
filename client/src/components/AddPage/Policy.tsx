import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { MainTitle } from '@style/common';
import { FundAddInfo, PolicyInAdd } from '@type/ProcessInfo';
import { FormForLongText, FormForText, FormForUpload } from './FormComponent';
import { POLICY_SERVICE } from '@components/common/constant';

interface Props {
  data: PolicyInAdd;
  setData: React.Dispatch<React.SetStateAction<FundAddInfo>>;
}

export interface ValidCheck {
  validIdx: number;
  validValue: boolean;
  isValid: boolean;
}

const updatepolicyInfo = (prevInfo: FundAddInfo, policyInfo: PolicyInAdd) => {
  return {
    ...prevInfo,
    policyInfo: policyInfo,
  };
};

const Policy = ({ data, setData }: Props) => {
  const [policyInfo, setPolicyInfo] = useState<PolicyInAdd>(data);
  const [serviceValid, setServiceValid] = useState<boolean>(data.service);
  const [refundValid, setRefundValid] = useState<boolean>(data.refund);

  // serviceValid를 토글하는 함수
  const toggleServiceValid = () => {
    setServiceValid(!serviceValid);
    policyInfo.service = !serviceValid;
    policyInfo.refund = refundValid;
    if (!serviceValid && refundValid) {
      setData((prevInfo) => updatepolicyInfo(prevInfo, policyInfo));
    }
  };
  // refundValid 로 토글하는 함수
  const toggleRefundValid = () => {
    setRefundValid(!refundValid);
    policyInfo.service = serviceValid;
    policyInfo.refund = !refundValid;
    if (serviceValid && !refundValid) {
      setData((prevInfo) => updatepolicyInfo(prevInfo, policyInfo));
    }
  };

  return (
    <div>
      <Title>
        정책<div className="dot"></div>
      </Title>
      <QuestionFrame>
        <Subtitle>서비스 이용동의</Subtitle>
        <Contents>
          <AgreeInfo>아래 내용을 반드시 확인해주세요</AgreeInfo>
          <PolicyInfo>{POLICY_SERVICE}</PolicyInfo>
          <StyledLabel htmlFor={'동의합니다'}>
            <StyledInput
              type="checkbox"
              id="service"
              name={'동의합니다'}
              checked={serviceValid}
              onChange={toggleServiceValid}
            />
            <StyledP>{'위 사실을 모두 확인하였고 동의합니다'}</StyledP>
          </StyledLabel>
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>환불 정책 동의</Subtitle>
        <Contents>
          <AgreeInfo>아래 내용을 반드시 확인해주세요</AgreeInfo>
          <PolicyInfo>{POLICY_SERVICE}</PolicyInfo>
          <StyledLabel htmlFor={'동의합니다'}>
            <StyledInput
              type="checkbox"
              id="refund"
              name={'동의합니다'}
              checked={refundValid}
              onChange={toggleRefundValid}
            />
            <StyledP>{'위 사실을 모두 확인하였고 동의합니다'}</StyledP>
          </StyledLabel>
        </Contents>
      </QuestionFrame>
    </div>
  );
};

const StyledInput = styled.input`
  appearance: none;
  border: 1.5px solid gainsboro;
  border-radius: 0.35rem;
  width: 1.5rem;
  height: 1.5rem;

  &:checked {
    border-color: transparent;
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='white' xmlns='http://www.w3.org/2000/svg'%3e%3cpath d='M5.707 7.293a1 1 0 0 0-1.414 1.414l2 2a1 1 0 0 0 1.414 0l4-4a1 1 0 0 0-1.414-1.414L7 8.586 5.707 7.293z'/%3e%3c/svg%3e");
    background-size: 100% 100%;
    background-position: 50%;
    background-repeat: no-repeat;
    background-color: var(--main1-color);
  }
`;
const StyledLabel = styled.label`
  display: flex;
  align-items: center;
  user-select: none;
`;

const StyledP = styled.p`
  margin-left: 0.25rem;
`;

const Title = styled(MainTitle)`
  font-size: 25px;
  display: flex;
  margin-bottom: 30px;
`;

const QuestionFrame = styled.div`
  margin-bottom: 60px;
`;

const Subtitle = styled.div`
  font-weight: 700;
  font-size: 18px;
  display: flex;
  align-items: center;
  margin-bottom: 30px;

  span {
    margin-top: 3px;
    margin-left: 5px;
    color: var(--error-color);
    font-size: 18px;
  }
`;
const Contents = styled.div`
  display: flex;
  flex-direction: column;
  gap: 30px;
  margin-top: 20px;

  .rows {
    display: flex;
    align-items: center;
    gap: 20px;
  }
`;

const AgreeInfo = styled.div``;

const PolicyInfo = styled.div`
  height: 100px;
  overflow-y: scroll;
  overflow-x: hidden;
  font-size: 12px;
  font-weight: 400;
  &::-webkit-scrollbar {
    width: 10px;
    height: 100vh;
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 3px;
    height: 15px;
    background-color: var(--main2-color);
  }
  &::-webkit-scrollbar-track {
    background-color: var(--main3-color);
  }
`;
export default Policy;
