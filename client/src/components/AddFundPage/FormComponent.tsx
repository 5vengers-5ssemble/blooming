import { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import { ReactComponent as SuccessSvg } from '@assets/icons/success-check.svg';
import { ReactComponent as ErrorSvg } from '@assets/icons/error-check.svg';
import { ValidCheck } from './ProjectInfo';
import uploadFile from '@hooks/useUpload';

interface TextProps {
  title: string;
  placeholder: string;
  validIdx: number;
  setValid: React.Dispatch<React.SetStateAction<ValidCheck>>;
  errorCheck: (keyword: string) => boolean;
  initKeyword: string;
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
  initKeyword,
}: TextProps) => {
  const [keyword, setKeyword] = useState(initKeyword);
  const [validCheck, setValidCheck] = useState(false);

  useEffect(() => {
    setKeyword(initKeyword);
    const isValid = errorCheck(initKeyword); // 검증 결과 저장
    setValidCheck(isValid);
    setValid({ validIdx, validValue: initKeyword, isValid }); //상위 페이지에 결과 전달
  }, []);

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

export const FormForLongText = ({
  title,
  placeholder,
  errorCheck,
  setValid,
  validIdx,
  initKeyword,
}: TextProps) => {
  const [keyword, setKeyword] = useState(initKeyword);
  const [validCheck, setValidCheck] = useState(false);

  useEffect(() => {
    setKeyword(initKeyword);
    const isValid = errorCheck(initKeyword); // 검증 결과 저장
    setValidCheck(isValid);
    setValid({ validIdx, validValue: initKeyword, isValid }); //상위 페이지에 결과 전달
  }, []);

  const handleLongErrorCheck = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
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
        <LongFormBox
          placeholder={placeholder}
          value={keyword}
          onChange={handleLongErrorCheck}
          isLong={true}
        ></LongFormBox>
      </FormWithValid>
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
    </EachFormForText>
  );
};

export const FormForUpload = ({
  title,
  subInfo,
  setValid,
  validIdx,
}: UploadProps) => {
  const [isUploading, setIsUploading] = useState(false);
  const [uploadedFileName, setUploadedFileName] = useState<string | null>(null);
  const inputRef = useRef<HTMLInputElement | null>(null);

  const handleUploadClick = () => {
    // 파일 업로드 버튼 클릭 시 input 엘리먼트를 클릭
    if (inputRef.current) {
      inputRef.current.click();
    }

    setValid({ validIdx, validValue: 'url-upload', isValid: true });
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
        const isValid = true;
        setValid({ validIdx, validValue: uploadedFileUrl, isValid });
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

  return (
    <EachFormForText>
      <ContentTitle>{title}</ContentTitle>
      <div>
        <UploadCondition>
          {subInfo.map((info, idx) => (
            <div key={idx}>💁‍♀️ {info}</div>
          ))}
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
              <ClearFileName onClick={handleClearFileName}>x</ClearFileName>
            </FileNameContainer>
          ) : (
            <UploadButton onClick={handleUploadClick} disabled={isUploading}>
              {isUploading ? '업로딩 중...' : '파일 업로드'}
            </UploadButton>
          )}
        </UploadSection>
      </div>
    </EachFormForText>
  );
};

interface StyleProps {
  placeholder?: string;
  isLong?: boolean;
}

export const FileNameContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 3px;
`;

export const FileName = styled.span`
  margin-right: 5px;
`;

export const ClearFileName = styled.button`
  background: none;
  border: none;
  cursor: pointer;
`;

export const HiddenInput = styled.input`
  display: none;
`;

export const EachFormForText = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

export const ContentTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
`;

const FormWithValid = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;
export const FormBox = styled.input<StyleProps>`
  padding: 10px 5px 10px;
  border: none;
  width: 300px;
  background: none;
  height: ${(props) => props.isLong && '400px'};
  border-bottom: 1px solid var(--main2-color);
  &::placeholder {
    color: var(--gray-color);
    font-weight: 300;
  }

  &:focus {
    outline: none !important;
  }
`;
const LongFormBox = styled.textarea<StyleProps>`
  padding: 10px 5px 30px;
  border: none;
  width: 800px;
  background: none;
  height: max-content;
  border: none;
  resize: none;
  border-bottom: 1px solid var(--main2-color);
  &::placeholder {
    color: var(--gray-color);
    font-weight: 300;
  }
  &:focus {
    outline: none !important;
  }
`;

export const CorrectCheck = styled.div`
  display: flex;
  align-items: center;
  color: var(--success-color);
  gap: 5px;
  font-size: 13px;
  padding: 10px 5px 10px;
  border: none;
  width: fit-content;
`;
export const ErrorCheck = styled.div`
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

export const UploadCondition = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 12px;
  gap: 5px;
  margin-left: 10px;
  font-weight: 300;
`;

export const UploadSection = styled.div`
  display: flex;
  align-items: center;
  margin-top: 30px;
  margin-left: 5px;
  font-size: 14px;
  color: var(--main1-color);
`;

export const UploadFileName = styled.div`
  width: 50%;
`;
export const UploadButton = styled.div<{ disabled?: boolean }>`
  width: fit-content;
  color: var(--white-color);
  background-color: var(--main2-color);
  padding: 7px 15px;
  border-radius: 6px;
  cursor: pointer;
  opacity: ${(props) => (props.disabled ? 0.5 : 1)};

  &:hover {
    background-color: var(--main1-color);
  }
`;
