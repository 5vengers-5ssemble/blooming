import { ACTIVE, CONCERT, FUNDING_CATEGORY } from '@components/common/constant';
import { MainTitle } from '@style/common';
import { FundAddInfo, MakerInfo, ProjectInfoInAdd } from '@type/ProcessInfo';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { FormForText, FormForUpload } from './FormComponent';
import {
  validCompanyName,
  validCompanyRegistrationNumber,
  validTargetAmount,
} from '@utils/validation/AddFundInfoCheck';

interface Props {
  data: ProjectInfoInAdd;
  setData: React.Dispatch<React.SetStateAction<FundAddInfo>>;
}

export interface ValidCheck {
  validIdx: number;
  validValue: string;
  isValid: boolean;
}

const updateProjectInfo = (
  prevInfo: FundAddInfo,
  projectInfo: ProjectInfoInAdd,
) => {
  return {
    ...prevInfo,
    projectInfo: projectInfo,
  };
};

const ProjectInfo = ({ data, setData }: Props) => {
  const [categoryIdx, setCategoryIdx] = useState(0);
  const [projectInfo, setProjectInfo] = useState<ProjectInfoInAdd>(data);
  const [validInputCheck, setValidInputCheck] = useState<ValidCheck>({
    validIdx: 0,
    validValue: '',
    isValid: false,
  });

  useEffect(() => {
    if (data) {
      setCategoryIdx(data.category === CONCERT ? 0 : 1);
      setProjectInfo(data);
    }
  }, [data]);

  useEffect(() => {
    if (validInputCheck.isValid) {
      switch (validInputCheck.validIdx) {
        case 0:
          //사업자 등록 번호
          projectInfo.makerInfo.licenseNumber = validInputCheck.validValue;
          setData((prevInfo) => updateProjectInfo(prevInfo, projectInfo));
          break;

        case 1:
          //사업자 이름
          projectInfo.makerInfo.companyName = validInputCheck.validValue;
          setData((prevInfo) => updateProjectInfo(prevInfo, projectInfo));
          break;

        case 2:
          //사업자 등록증
          projectInfo.makerInfo.licenseImage = validInputCheck.validValue;
          setData((prevInfo) => updateProjectInfo(prevInfo, projectInfo));
          break;

        case 3:
          //법인인감증명서
          projectInfo.makerInfo.sealCertificate = validInputCheck.validValue;
          setData((prevInfo) => updateProjectInfo(prevInfo, projectInfo));
          break;
        case 4:
          //목표금액
          projectInfo.targetAmount = parseInt(validInputCheck.validValue, 10);
          setData((prevInfo) => updateProjectInfo(prevInfo, projectInfo));
          break;

        default:
          break;
      }
    }
  }, [validInputCheck]);

  const handleCategoryChange = () => {
    setCategoryIdx(Math.abs(1 - categoryIdx));
    categoryIdx === 1
      ? (projectInfo.category = CONCERT)
      : (projectInfo.category = ACTIVE);
    setData((prevInfo) => updateProjectInfo(prevInfo, projectInfo));
  };

  return (
    <div>
      <Title>
        프로젝트 정보 <div className="dot"></div>
      </Title>

      <QuestionFrame>
        <Subtitle>무엇을 위한 펀딩인가요?</Subtitle>
        <Contents>
          <div className="rows">
            <Chip $active={categoryIdx === 0} onClick={handleCategoryChange}>
              {FUNDING_CATEGORY[0]}
            </Chip>
            <Chip $active={categoryIdx === 1} onClick={handleCategoryChange}>
              {FUNDING_CATEGORY[1]}
            </Chip>
          </div>
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>사업자 인증이 필요해요!</Subtitle>
        <Contents>
          <FormForText
            title="사업자 등록 번호 (10자리)"
            placeholder="사업자 등록 번호를 입력해주세요"
            validIdx={0}
            setValid={setValidInputCheck}
            errorCheck={validCompanyRegistrationNumber}
            initKeyword={projectInfo.makerInfo.licenseNumber}
          />
          <FormForText
            title="상호 법인명"
            placeholder="상호 또는 법인명을 입력해주세요"
            validIdx={1}
            setValid={setValidInputCheck}
            errorCheck={validCompanyName}
            initKeyword={projectInfo.makerInfo.companyName}
          />
          <FormForUpload
            title="사업자 등록증"
            subInfo={[
              '가장 최근에 발급한 사업자 등록증을 업로드 해주세요.',
              'JPG, JPEG, PNG, PDF / 10MB 이하 파일 1개만 업로드 가능해요.',
            ]}
            setValid={setValidInputCheck}
            validIdx={2}
          />
          <FormForUpload
            title="법인 인감 증명서"
            subInfo={[
              '발급 후 3개월이 지나지 않은 법인 인감증명서만 업로드할 수 있어요.',
              '공동 대표라면 모든 공동 대표자의 법인 인감증명서를 업로드해야해요.',
              '주민등록번호 뒷자리는 노출되지 않도록 가려주세요.',
              'JPG, JPEG, PNG, PDF / 10MB 이하 파일 1개만 업로드 가능해요.',
            ]}
            setValid={setValidInputCheck}
            validIdx={3}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>목표금액을 설정해주세요!</Subtitle>
        <Contents>
          <FormForText
            title="최소 50만원 ~ 최대 1억원 사이에서 설정해주세요"
            placeholder="목표 금액을 입력해주세요."
            validIdx={4}
            setValid={setValidInputCheck}
            errorCheck={validTargetAmount}
            initKeyword={projectInfo.targetAmount.toString()}
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

interface styleProps {
  $active?: boolean;
}

const Chip = styled.div<styleProps>`
  cursor: pointer;
  width: fit-content;
  height: fit-content;
  padding: 10px 20px;
  background-color: ${(props) =>
    props.$active ? 'var(--main1-color)' : 'var(--gray-color)'};
  color: var(--white-color);
  font-size: 14px;
  font-weight: 400;
  border-radius: 6px;

  &:hover {
    background-color: var(--main1-color);
  }
`;

export default ProjectInfo;
