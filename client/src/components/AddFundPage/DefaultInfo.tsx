import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { ko } from 'date-fns/esm/locale'; //한국어 설정
import styled from 'styled-components';
import { MainTitle } from '@style/common';
import { useEffect, useState } from 'react';
import { DefaultInfoInAdd, FundAddInfo } from '@type/ProcessInfo';
import { FormForText, FormForUpload } from './FormComponent';
import { validFundingTitleCheck } from '@utils/validation/AddFundInfoCheck';
import { ReactComponent as DateSvg } from '@assets/icons/date.svg';
import { ReactComponent as ArrowSvg } from '@assets/icons/arrow-right.svg';

interface Props {
  data: DefaultInfoInAdd;
  setData: React.Dispatch<React.SetStateAction<FundAddInfo>>;
}

export interface ValidCheck {
  validIdx: number;
  validValue: string;
  isValid: boolean;
}

const updatedefaultInfo = (
  prevInfo: FundAddInfo,
  defaultInfo: DefaultInfoInAdd,
) => {
  return {
    ...prevInfo,
    basicInfo: defaultInfo,
  };
};

const formatDate = (date: Date) => {
  const today = new Date();
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(today.getHours()).padStart(2, '0');
  const minutes = String(today.getMinutes()).padStart(2, '0');
  const seconds = String(today.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
};

const today = new Date();
const DefaultInfo = ({ data, setData }: Props) => {
  const [defaultInfo, setDefaultInfo] = useState<DefaultInfoInAdd>(data);
  const [validInputCheck, setValidInputCheck] = useState<ValidCheck>({
    validIdx: 0,
    validValue: '',
    isValid: false,
  });
  const [startDate, setStartDate] = useState<Date>(
    defaultInfo.startDate.length === 0
      ? today
      : new Date(defaultInfo.startDate),
  );
  const [endDate, setEndDate] = useState<Date>(
    defaultInfo.endDate.length === 0 ? today : new Date(defaultInfo.endDate),
  );

  useEffect(() => {
    if (validInputCheck.isValid) {
      switch (validInputCheck.validIdx) {
        case 0:
          // 대표 이미지 등록
          defaultInfo.thumbnail = validInputCheck.validValue;
          setData((prevInfo) => updatedefaultInfo(prevInfo, defaultInfo));
          break;
        case 1:
          // 프로젝트 타이틀
          defaultInfo.title = validInputCheck.validValue;
          setData((prevInfo) => updatedefaultInfo(prevInfo, defaultInfo));
          break;
        default:
          break;
      }
    }
  }, [validInputCheck]);

  useEffect(() => {
    if (startDate < endDate) {
      defaultInfo.startDate = formatDate(startDate).toString();
      defaultInfo.endDate = formatDate(endDate).toString();
      setData((prevInfo) => updatedefaultInfo(prevInfo, defaultInfo));
    }
  }, [startDate, endDate]);

  return (
    <div>
      <Title>
        기본 정보 <div className="dot"></div>
      </Title>

      <QuestionFrame>
        <Subtitle>펀딩 대표 이미지를 등록해주세요!</Subtitle>
        <Contents>
          <FormForUpload
            title="대표 이미지는 상단에 노출됩니다"
            subInfo={[
              'JPG, JPEG, PNG, PDF / 10MB 이하 파일 1개만 업로드 가능해요.',
            ]}
            setValid={setValidInputCheck}
            validIdx={0}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>펀딩 타이틀을 입력해주세요!</Subtitle>
        <Contents>
          <FormForText
            title="최대 30글자만 가능합니다"
            placeholder="펀딩 타이틀을 입력해주세요."
            validIdx={1}
            setValid={setValidInputCheck}
            errorCheck={validFundingTitleCheck}
            initKeyword={defaultInfo.title}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>펀딩 시작일과 종료일을 알려주세요</Subtitle>
        <DateFrame>
          <DateBox1>
            <DateSvg />
            <StyledDatePicker // DatePicker의 styled-component명
              dateFormat="yyyy-MM-dd"
              selected={startDate}
              closeOnScroll={true} // 스크롤을 움직였을 때 자동으로 닫히도록 설정 기본값 false
              onChange={(date: Date) => setStartDate(date)}
            />
          </DateBox1>
          <ArrowSvg />
          <DateBox2>
            <DateSvg />
            <StyledDatePicker
              dateFormat="yyyy-MM-dd"
              selected={endDate}
              closeOnScroll={true} // 스크롤을 움직였을 때 자동으로 닫히도록 설정 기본값 false
              onChange={(date: Date) => setEndDate(date)}
            />
          </DateBox2>
        </DateFrame>
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

const DateBox1 = styled.div`
  display: flex;
  align-items: center;
`;
const DateBox2 = styled.div`
  display: flex;
  align-items: center;
  margin-left: 50px;
`;

const DateFrame = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
  margin-top: -20px;
  margin-left: 10px;
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
export default DefaultInfo;
