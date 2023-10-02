import React, { useState } from 'react';
import styled from 'styled-components';

import CreditLottie from './CreditLottie';

import { ReactComponent as SuccessSvg } from '@assets/icons/success-check.svg';
import { ReactComponent as ErrorSvg } from '@assets/icons/error-check.svg';

import {
  ContentTitle,
  CorrectCheck,
  EachFormForText,
  ErrorCheck,
  FormBox,
  UploadButton,
  UploadCondition,
  UploadFileName,
  UploadSection,
} from '@components/AddFundPage/FormComponent';

interface MoreInfoProps {
  onRegisterLive: (title: string, thumbnail: string | null) => void;
}

const MoreInfo = ({ onRegisterLive }: MoreInfoProps) => {
  const [title, setTitle] = useState<string>('');
  const [thumbnail, setThumbnail] = useState<string | null>(null);
  const [validCheck, setValidCheck] = useState(false);

  const handleUpload = () => {
    const value = 'url-upload';
    setThumbnail(value);
  };

  const handleErrorCheck = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setTitle(value);
    const isValid = value.length > 0; // 검증 결과 저장
    setValidCheck(isValid);
  };

  return (
    <BodyFrame>
      <MembershipFrame>
        <Container>
          <EachFormForText>
            <ContainerTitle>라이브 제목을 입력해주세요</ContainerTitle>
            <FormWithValid>
              <FormBox
                placeholder={'타이틀을 입력해주세요'}
                value={title}
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
          <EachFormForText>
            <ContainerTitle>라이브 대표 이미지를 등록해주세요</ContainerTitle>
            <div>
              <UploadCondition>
                <div>💁‍♀️ 썸네일 이미지를 업로드해주세요</div>
                <div>
                  💁‍♀️ 이미지를 업로드 하지 않을시 아티스트 기본 이미지가
                  설정됩니다
                </div>
              </UploadCondition>
              <UploadSection>
                <UploadFileName>첨부 파일을 업로드 해주세요</UploadFileName>
                <UploadButton onClick={handleUpload}>업로드</UploadButton>
              </UploadSection>
            </div>
          </EachFormForText>
        </Container>
        <div className="isMembership">
          <CreditLottie />
          <Button onClick={() => onRegisterLive(title, thumbnail)}>
            등록 요청하기
          </Button>
        </div>
      </MembershipFrame>
    </BodyFrame>
  );
};

const MembershipFrame = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: space-around;

  .isMembership {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width: 400px;
  }
`;
export const BodyFrame = styled.div`
  margin-top: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 40px;
`;

const ContainerTitle = styled(ContentTitle)`
  font-size: 18px;
  font-weight: 600;
`;

const FormWithValid = styled.div`
  display: flex;
  flex-direction: column;
`;

const Button = styled.button`
  cursor: pointer;
  margin-top: 20px;
  padding: 10px 15px;
  width: 100%;
  background-color: var(--white-color);
  border-radius: 6px;
  border: 1px solid var(--main2-color);
  color: var(--main1-color);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  font-weight: 600;
`;

export default MoreInfo;
