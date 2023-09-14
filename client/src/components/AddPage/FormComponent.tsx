import { useState } from 'react';
import styled from 'styled-components';
import { ReactComponent as SuccessSvg } from '@assets/icons/success-check.svg';
import { ReactComponent as ErrorSvg } from '@assets/icons/error-check.svg';
import { ValidCheck } from './ProjectInfo';

interface TextProps {
  title: string;
  placeholder: string;
  validIdx: number;
  setValid: React.Dispatch<React.SetStateAction<ValidCheck>>;
  errorCheck: (keyword: string) => boolean;
}

interface UploadProps {
  title: string;
  subInfo: string[];
  validIdx: number;
  setValid: React.Dispatch<React.SetStateAction<ValidCheck>>;
}

export const FormForText = ({
  title,
  placeholder,
  errorCheck,
  setValid,
  validIdx,
}: TextProps) => {
  const [keyword, setKeyword] = useState('');
  const [validCheck, setValidCheck] = useState(false);

  const handleErrorCheck = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setKeyword(value);
    const isValid = errorCheck(value); // 검증 결과 저장
    setValidCheck(isValid);
    setValid({ validIdx, validValue: value, isValid }); //상위 페이지에 결과 전달
  };

  return (
    <EachFormForText>
      <ContentTitle>{title}</ContentTitle>
      <FormWithValid>
        <FormBox
          placeholder={placeholder}
          value={keyword}
          onChange={handleErrorCheck}
        ></FormBox>
        {validCheck ? (
          <CorrectCheck>
            <SuccessSvg />
            확인되었습니다
          </CorrectCheck>
        ) : (
          <ErrorCheck>
            <ErrorSvg />
            입력을 확인해주세요
          </ErrorCheck>
        )}
      </FormWithValid>
    </EachFormForText>
  );
};

export const FormForUpload = ({
  title,
  subInfo,
  setValid,
  validIdx,
}: UploadProps) => {
  return (
    <EachFormForText>
      <ContentTitle>{title}</ContentTitle>
      <div>
        <UploadCondition>
          {subInfo.map((info) => (
            <div>💁‍♀️ {info}</div>
          ))}
        </UploadCondition>
        <UploadSection>
          <UploadFileName>첨부 파일을 업로드 해주세요</UploadFileName>
          <UploadButton>업로드</UploadButton>
        </UploadSection>
      </div>
    </EachFormForText>
  );
};

interface StyleProps {
  placeholder?: string;
}

const EachFormForText = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

const ContentTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
`;

const FormWithValid = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;
const FormBox = styled.input<StyleProps>`
  padding: 10px 5px 10px;
  border: none;
  width: 300px;
  background: none;
  border-bottom: 1px solid var(--main2-color);
  &::placeholder {
    color: var(--gray-color);
    font-weight: 300;
  }

  &:focus {
    outline: none !important;
  }
`;

const CorrectCheck = styled.div`
  display: flex;
  align-items: center;
  color: var(--success-color);
  gap: 5px;
  font-size: 13px;
  padding: 10px 5px 10px;
  border: none;
  width: fit-content;
`;
const ErrorCheck = styled.div`
  display: flex;
  align-items: center;
  color: var(--error-color);
  gap: 5px;
  font-size: 13px;
  padding: 10px 5px 10px;
  border: none;
  width: fit-content;
  /* border-bottom: 1px solid var(--main1-color); */
`;

const UploadCondition = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 12px;
  gap: 5px;
  margin-left: 10px;
  font-weight: 300;
`;

const UploadSection = styled.div`
  display: flex;
  align-items: center;
  margin-top: 30px;
  margin-left: 5px;
  font-size: 14px;
  color: var(--main1-color);
`;

const UploadFileName = styled.div`
  width: 50%;
`;
const UploadButton = styled.div`
  width: fit-content;
  color: var(--white-color);
  background-color: var(--main2-color);
  padding: 7px 15px;
  border-radius: 6px;
  cursor: pointer;
`;
