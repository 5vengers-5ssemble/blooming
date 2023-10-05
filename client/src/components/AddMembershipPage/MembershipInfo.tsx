import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { ko } from 'date-fns/esm/locale'; //한국어 설정
import { useState, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router';
import { POST_CATEGORY } from '@components/common/constant';
import styled from 'styled-components';
import {
  ClearFileName,
  ContentTitle,
  FileName,
  FileNameContainer,
  FormBox,
  HiddenInput,
  UploadButton,
} from '@components/AddFundPage/FormComponent';
import uploadFile from '@hooks/useUpload';
import { MainTitle } from '@style/common';
import { MembershipInfo } from '@type/MembershipInfo';
import { ReactComponent as DateSvg } from '@assets/icons/date.svg';
import { ReactComponent as ArrowSvg } from '@assets/icons/arrow-right.svg';
import axios from '@api/apiController';

const InitInfo: MembershipInfo = {
  title: '',
  description: '',
  seasonStart: '',
  seasonEnd: '',
  purchaseStart: '',
  purchaseEnd: '',
  saleCount: 0,
  salePrice: 0,
  thumbnailUrl: '',
  baseUri: '',
  privateKey: '',
};

const AddMembership = () => {

  const navigate = useNavigate();

  const [membershipInfo, setMembershipInfo] =
    useState<MembershipInfo>(InitInfo);
  const today = new Date();
  const [seasonStart, setSeasonStart] = useState<Date>(
    membershipInfo?.seasonStart.length === 0
      ? today
      : new Date(membershipInfo.seasonStart),
  );
  const [seasonEnd, setSeasonEnd] = useState<Date>(
    membershipInfo?.seasonEnd.length === 0
      ? today
      : new Date(membershipInfo.seasonEnd),
  );
  const [purchaseStart, setPurchaseStart] = useState<Date>(
    membershipInfo?.purchaseStart.length === 0
      ? today
      : new Date(membershipInfo.purchaseStart),
  );

  const [purchaseEnd, setPurchaseEnd] = useState<Date>(
    membershipInfo?.purchaseEnd.length === 0
      ? today
      : new Date(membershipInfo.purchaseEnd),
  );
  

  useEffect(() => {
    if (seasonStart <= seasonEnd) {
      setMembershipInfo((prevState) => {
        return {
          ...prevState,
          seasonStart: dateFormatter(seasonStart),
          seasonEnd: dateFormatter(seasonEnd),
        };
      });
    }
  }, [seasonStart, seasonEnd]);

  useEffect(() => {
    if (purchaseStart <= purchaseEnd) {
      setMembershipInfo((prevState) => {
        return {
          ...prevState,
          purchaseStart: dateFormatter(purchaseStart),
          purchaseEnd: dateFormatter(purchaseEnd),
        };
      });
    }
  }, [purchaseStart, purchaseEnd]);

  const [isUploading, setIsUploading] = useState(false);
  const [nftImageName, setNftImageName] = useState<string | null>(null);
  const [nftImageUrl, setNftImageUrl] = useState<string | null>(null);
  const inputRef = useRef<HTMLInputElement | null>(null);

  const handleUploadClick = () => {
    // 파일 업로드 버튼 클릭 시 input 엘리먼트를 클릭
    if (inputRef.current) {
      inputRef.current.click();
    }
  };

  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMembershipInfo((prevState) => {
      return {
        ...prevState,
        title: event.target.value,
        baseUri: `https://${import.meta.env.VITE_BUCKET_NAME}.s3.${
          import.meta.env.VITE_BUCKET_REGION
        }.amazonaws.com/uploads/nft/json/${event.target.value}/`,
      };
    });
  };

  const handleDescChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMembershipInfo((prevState) => {
      return { ...prevState, description: event.target.value };
    });
  };

  const handleSeasonChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMembershipInfo((prevState) => {
      return { ...prevState, season: event.target.value };
    });
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
          'uploads/nft/image/' + selectedFile.name, // S3 내 파일 경로 및 이름
        );

        setIsUploading(false);
        setNftImageName(selectedFile.name);
        setNftImageUrl(uploadedFileUrl);
        setMembershipInfo((prevState) => {
          return { ...prevState, thumbnailUrl: uploadedFileUrl };
        });

        // 업로드 성공 시 결과 전달
        console.log('amazon link', uploadedFileUrl);
      } catch (error) {
        setIsUploading(false);

        // 업로드 실패 시 오류 처리
        console.error('파일 업로드 오류:', error);
      }
    }
  };

  const handleClearFileName = () => {
    // 파일명 삭제 버튼 클릭 시 파일명 초기화
    setNftImageName(null);
  };

  const handleSaleCountChange = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setMembershipInfo((prevState) => {
      return { ...prevState, saleCount: event.target.value };
    });
  };

  const handleSalePriceChange = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setMembershipInfo((prevState) => {
      return { ...prevState, salePrice: event.target.value };
    });
  };

  const handleKeyChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMembershipInfo((prevState) => {
      return { ...prevState, privateKey: event.target.value };
    });
  };

  return (
    <div>
      <div>
        <Title>
          멤버쉽 등록 정보 <div className="dot"></div>
        </Title>

        <QuestionFrame>
          <Subtitle>멤버쉽에 대한 기본적인 정보를 알려주세요!</Subtitle>
          <Contents>
            <ContentTitle>
              멤버쉽의 이름을 입력해주세요(영문 10자 이하)
            </ContentTitle>
            <FormBox onChange={handleTitleChange}></FormBox>
            <ContentTitle>
              멤버쉽에 대한 간단한 설명을 입력해주세요
            </ContentTitle>
            <FormBox onChange={handleDescChange}></FormBox>
          </Contents>
        </QuestionFrame>
        <QuestionFrame>
          <Subtitle>시즌 시작일과 종료일을 알려주세요!</Subtitle>
          <DateFrame>
            <DateBox1>
              <DateSvg />
              <StyledDatePicker
                locale={ko}
                dateFormat="yyyy-MM-dd"
                selected={seasonStart}
                closeOnScroll={true}
                onChange={(date: Date) => setSeasonStart(date)}
              />
            </DateBox1>
            <ArrowSvg />
            <DateBox2>
              <DateSvg />
              <StyledDatePicker
                locale={ko}
                dateFormat="yyyy-MM-dd"
                selected={seasonEnd}
                closeOnScroll={true}
                onChange={(date: Date) => setSeasonEnd(date)}
              />
            </DateBox2>
          </DateFrame>
        </QuestionFrame>
        <QuestionFrame>
          <Subtitle>NFT로 발행할 개성넘치는 이미지를 등록해주세요</Subtitle>
          <ContentTitle>
            JPG, JPEG, PNG, PDF / 10MB 이하 파일 1개만 업로드 가능해요. 🙏
          </ContentTitle>
          <Contents>
            <HiddenInput
              type="file"
              ref={inputRef}
              onChange={handleFileChange}
              accept="image/*" // 파일 형식 제한을 설정할 수 있습니다.
            />
            {nftImageName ? (
              <FileNameContainer>
                <FileName>{nftImageName}</FileName>
                <ClearFileName onClick={handleClearFileName}>x</ClearFileName>
              </FileNameContainer>
            ) : (
              <UploadButton onClick={handleUploadClick} disabled={isUploading}>
                {isUploading ? '업로딩 중...' : '파일 업로드'}
              </UploadButton>
            )}
          </Contents>
        </QuestionFrame>
        <QuestionFrame>
          <Subtitle>NFT 발행 시작일과 종료일을 알려주세요!</Subtitle>
          <DateFrame>
            <DateBox1>
              <DateSvg />
              <StyledDatePicker
                locale={ko}
                dateFormat="yyyy-MM-dd"
                selected={purchaseStart}
                closeOnScroll={true}
                onChange={(date: Date) => setPurchaseStart(date)}
              />
            </DateBox1>
            <ArrowSvg />
            <DateBox2>
              <DateSvg />
              <StyledDatePicker
                locale={ko}
                dateFormat="yyyy-MM-dd"
                selected={purchaseEnd}
                closeOnScroll={true}
                onChange={(date: Date) => setPurchaseEnd(date)}
              />
            </DateBox2>
          </DateFrame>
        </QuestionFrame>
        <QuestionFrame>
          <Subtitle>총 몇 개의 NFT를 발행할 건가요?</Subtitle>
          <FormBox
            type="number"
            defaultValue="1"
            min="1"
            onChange={handleSaleCountChange}
          ></FormBox>
        </QuestionFrame>
        <QuestionFrame>
          <Subtitle>NFT Minting 가격을 정해주세요</Subtitle>
          <FormBox
            type="number"
            defaultValue="0"
            min="1"
            onChange={handleSalePriceChange}
          ></FormBox>
        </QuestionFrame>
        <QuestionFrame>
          <Subtitle>
            🔐보유하고 있는 Kaikas 지갑의 Private Key를 입력해주세요.
          </Subtitle>
          <Contents>
            <FormBox type="password" onChange={handleKeyChange}></FormBox>
          </Contents>
        </QuestionFrame>
        <UploadButton 
          onClick={async () => {
            const res = await axios.post(
              '/membership-applications',
              membershipInfo
            );
            if (res) {
              navigate(`/post-success/${POST_CATEGORY.fundRegister}`);
            } else {
              alert('멤버쉽 등록 실패!!!');
            }
          }}
        >제출하기</UploadButton>
      </div>
    </div>
  );
};

const dateFormatter = (date: Date) => {
  return date.toISOString().slice(0, -5);
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

const DateFrame = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
  margin-top: -20px;
  margin-left: 10px;
`;

const DateBox1 = styled.div`
  display: flex;
  align-items: center;
`;

const DateBox2 = styled.div`
  display: flex;
  align-items: center;
  margin-left: 50px;
`;

const StyledDatePicker = styled(DatePicker)`
  width: 100px;
  height: fit-content;
  border: none;
  font-weight: 400;
  font-size: 15px;
  line-height: 100%;
  padding: 20px 15px;
  background-color: transparent;

  &:focus {
    outline: none !important;
  }
`;

const AddButton = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  background-color: var(--main1-color);
  color: var(--white-color);
  font-weight: 700;
  margin-right: 30px;
  border-radius: 6px;
  cursor: pointer;
`;

export default AddMembership;
