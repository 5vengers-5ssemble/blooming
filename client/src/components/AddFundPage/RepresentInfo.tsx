import styled from 'styled-components';
import { MainTitle } from '@style/common';
import { useEffect, useState } from 'react';
import { FundAddInfo, RepresentInfoInAdd } from '@type/ProcessInfo';
import { FormForText, FormForUpload } from './FormComponent';
import {
  validCompanyName,
  validDepositCheck,
  validEmailCheck,
} from '@utils/validation/AddFundInfoCheck';

interface Props {
  data: RepresentInfoInAdd;
  setData: React.Dispatch<React.SetStateAction<FundAddInfo>>;
}

export interface ValidCheck {
  validIdx: number;
  validValue: string;
  isValid: boolean;
}

const updaterepresentInfo = (
  prevInfo: FundAddInfo,
  representInfo: RepresentInfoInAdd,
) => {
  return {
    ...prevInfo,
    settlementInfo: representInfo,
  };
};

const RepresentInfo = ({ data, setData }: Props) => {
  const [representInfo, setrepresentInfo] = useState<RepresentInfoInAdd>(data);
  const [validInputCheck, setValidInputCheck] = useState<ValidCheck>({
    validIdx: 0,
    validValue: '',
    isValid: false,
  });

  useEffect(() => {
    if (validInputCheck.isValid) {
      switch (validInputCheck.validIdx) {
        case 0:
          // 대표자 이름
          representInfo.representative = validInputCheck.validValue;
          setData((prevInfo) => updaterepresentInfo(prevInfo, representInfo));
          break;
        case 1:
          // email
          representInfo.email = validInputCheck.validValue;
          setData((prevInfo) => updaterepresentInfo(prevInfo, representInfo));
          break;
        case 2:
          //deposit
          representInfo.accountNumber = validInputCheck.validValue;
          setData((prevInfo) => updaterepresentInfo(prevInfo, representInfo));
          break;
        case 3:
          // bankbookImage
          representInfo.bankbookImage = validInputCheck.validValue;
          setData((prevInfo) => updaterepresentInfo(prevInfo, representInfo));
          break;

        default:
          break;
      }
    }
  }, [validInputCheck]);

  return (
    <div>
      <Title>
        대표자 및 정산 정보<div className="dot"></div>
      </Title>

      <QuestionFrame>
        <Subtitle>대표자 정보를 입력해주세요</Subtitle>
        <Contents>
          <FormForText
            title="대표자 명을 입력해주세요"
            placeholder="개인은 주민등록증 속 성명을 써주세요"
            validIdx={0}
            setValid={setValidInputCheck}
            errorCheck={validCompanyName}
            initKeyword={representInfo.representative}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>정산 정보를 입력해주세요.</Subtitle>
        <MoreTitle>🙆‍♂️ 세금 계산서 발행정보용으로 이용됩니다.</MoreTitle>
        <Contents>
          <FormForText
            title="이메일을 입력해주세요"
            placeholder="이메일을 입력해주세요"
            validIdx={1}
            setValid={setValidInputCheck}
            errorCheck={validEmailCheck}
            initKeyword={representInfo.email}
          />
          <FormForText
            title="계좌번호를 입력해주세요"
            placeholder="계좌번호를 입력해주세요"
            validIdx={2}
            setValid={setValidInputCheck}
            errorCheck={validDepositCheck}
            initKeyword={representInfo.accountNumber}
          />
          <FormForUpload
            title="통장사본을 업로드해주세요"
            subInfo={[
              'JPG, JPEG, PNG, PDF / 10MB 이하 파일 1개만 업로드 가능해요.',
            ]}
            setValid={setValidInputCheck}
            validIdx={3}
          />
        </Contents>
      </QuestionFrame>
    </div>
  );
};

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

const MoreTitle = styled.div`
  margin-top: -18px;
  margin-bottom: 30px;
  font-weight: 700;
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

export default RepresentInfo;
