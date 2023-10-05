import React, { useRef, useState } from 'react';
import styled from 'styled-components';

import CreditLottie from './CreditLottie';

import { ReactComponent as SuccessSvg } from '@assets/icons/success-check.svg';
import { ReactComponent as ErrorSvg } from '@assets/icons/error-check.svg';

import {
  ClearFileName,
  ContentTitle,
  CorrectCheck,
  EachFormForText,
  ErrorCheck,
  FileName,
  FileNameContainer,
  FormBox,
  HiddenInput,
  UploadButton,
  UploadCondition,
  UploadFileName,
  UploadSection,
} from '@components/AddFundPage/FormComponent';
import uploadFile from '@hooks/useUpload';

interface MoreInfoProps {
  onRegisterLive: (title: string, thumbnail: string | null) => void;
}

const MoreInfo = ({ onRegisterLive }: MoreInfoProps) => {
  const [title, setTitle] = useState<string>('');
  const [thumbnail, setThumbnail] = useState<string | null>(null);
  const [validCheck, setValidCheck] = useState(false);
  const [isUploading, setIsUploading] = useState(false);
  const [uploadedFileName, setUploadedFileName] = useState<string | null>(null);
  const inputRef = useRef<HTMLInputElement | null>(null);

  const handleUploadClick = () => {
    // 파일 업로드 버튼 클릭 시 input 엘리먼트를 클릭
    if (inputRef.current) {
      inputRef.current.click();
    }
  };

  const handleFileChange = async (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const selectedFile = event.target.files?.[0];
    if (selectedFile) {
      setIsUploading(true);

      try {
        const uploadedFileUrl = await uploadFile(
          selectedFile,
          selectedFile.name, // S3 내 파일 경로 및 이름
        );

        setIsUploading(false);
        setUploadedFileName(selectedFile.name);

        // 업로드 성공 시 결과 전달
        console.log('amazon link', uploadedFileUrl);
        setThumbnail(uploadedFileUrl);
      } catch (error) {
        setIsUploading(false);

        // 업로드 실패 시 오류 처리
        console.error('파일 업로드 오류:', error);
      }
    }
  };

  const handleClearFileName = () => {
    // 파일명 삭제 버튼 클릭 시 파일명 초기화
    setUploadedFileName(null);
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
                <HiddenInput
                  type="file"
                  ref={inputRef}
                  onChange={handleFileChange}
                  accept="image/*, .pdf" // 파일 형식 제한을 설정할 수 있습니다.
                />
                {uploadedFileName ? (
                  <FileNameContainer>
                    <FileName>{uploadedFileName}</FileName>
                    <ClearFileName onClick={handleClearFileName}>
                      x
                    </ClearFileName>
                  </FileNameContainer>
                ) : (
                  <UploadButton
                    onClick={handleUploadClick}
                    disabled={isUploading}
                  >
                    {isUploading ? '업로딩 중...' : '파일 업로드'}
                  </UploadButton>
                )}
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
