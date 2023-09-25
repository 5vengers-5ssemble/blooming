import { useState, useEffect } from 'react';
import styled from 'styled-components';
import DefaultInfo from '@components/AddFundPage/DefaultInfo';
import Policy from '@components/AddFundPage/Policy';
import ProjectInfo from '@components/AddFundPage/ProjectInfo';
import RepresentInfo from '@components/AddFundPage/RepresentInfo';
import StoryWrite from '@components/AddFundPage/StoryWrite';
import { ReactComponent as LogoutSvg } from '@assets/icons/logout.svg';
import { FundAddInfo } from '@type/ProcessInfo';
import { validateFundAddInfo } from '@utils/validation/AddFundInfoCheck';
import { InitInfo } from '@components/AddFundPage/InitInfo';

const subtitleData = [
  '프로젝트 정보',
  '기본 정보',
  '스토리 작성',
  '정책',
  '대표자 및 정산 정보',
];

const AddFund = () => {
  const [activeIndex, setActiveIndex] = useState<number>(0);
  const [totalInfo, setTotalInfo] = useState<FundAddInfo>(InitInfo);
  const [canSubmit, setIsSubmit] = useState<boolean>(false);

  const components = [
    <ProjectInfo data={totalInfo.projectInfo} setData={setTotalInfo} />,
    <DefaultInfo data={totalInfo.defaultInfo} setData={setTotalInfo} />,
    <StoryWrite data={totalInfo.storyInfo} setData={setTotalInfo} />,
    <Policy data={totalInfo.policyInfo} setData={setTotalInfo} />,
    <RepresentInfo data={totalInfo.refresentInfo} setData={setTotalInfo} />,
  ];

  const handleSubtitleClick = (index: number) => {
    setActiveIndex(index);
  };

  const handleTemporarySave = () => {
    sessionStorage.setItem('add-fund', JSON.stringify(totalInfo));
  };

  useEffect(() => {
    const temporaryData = sessionStorage.getItem('add-fund');
    if (temporaryData) {
      if (confirm('임시 저장된 데이터가 있습니다')) {
        setTotalInfo(JSON.parse(temporaryData));
      }
    }
  }, []);

  useEffect(() => {
    console.log('상위페이지 데이터 체크 > ', totalInfo);
    if (validateFundAddInfo(totalInfo)) {
      setIsSubmit(true);
    }
  }, [totalInfo]);

  return (
    <BackgroundGrad>
      <AddFrame>
        <TopInfoFrame>
          <Title>내 펀딩 등록</Title>
          <div className="button">
            <TemporaryButton onClick={handleTemporarySave}>
              임시저장
            </TemporaryButton>
            <AddButton isSubmit={canSubmit}>등록하기</AddButton>
          </div>
        </TopInfoFrame>
        <ContextFrame>
          <LeftContext>
            {subtitleData.map((subtitle, index) => (
              <Subtitle
                key={index}
                onClick={() => handleSubtitleClick(index)}
                active={index === activeIndex}
              >
                {subtitle}
              </Subtitle>
            ))}
            <Exit>
              나가기 <LogoutSvg />
            </Exit>
          </LeftContext>
          <RightContext>{components[activeIndex]}</RightContext>
        </ContextFrame>
      </AddFrame>
    </BackgroundGrad>
  );
};

const BackgroundGrad = styled.div`
  background-color: var(--main5-color);
  /* background-image: url('src/assets/images/AddBackground.jpg'); */
  /* background: linear-gradient(180deg, #35ebdf 0%, #ba8ffb 100%); */
  background-repeat: no-repeat;
  background-size: cover;
  margin: 0 -280px -100px;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const AddFrame = styled.div`
  /* background-color: var(--main3-color); */
  background-color: rgb(255 255 255 / 40%);
  max-width: 1400px;
  max-height: 860px;
  height: 90vh;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  position: relative;
  width: 100%;
  border-radius: 14px;
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  font-size: 15px;
  font-weight: 500;
`;

const Title = styled.div`
  padding: 30px 0 20px 40px;
  font-size: 20px;
  font-weight: 700;
  color: var(--main4-color);
`;

const ContextFrame = styled.div`
  display: flex;
  overflow-y: hidden;
`;
const LeftContext = styled.div`
  width: 20%;
  height: 100vh;
  padding: 50px 0 0 40px;
  display: flex;
  flex-direction: column;
  gap: 35px;
`;

interface Props {
  active: boolean;
}

const Subtitle = styled.div<Props>`
  color: ${(props) =>
    props.active ? 'var(--main1-color)' : 'var(--main2-color)'};
  font-size: 16px;
  font-weight: ${(props) => (props.active ? '700' : '600')};
  margin-bottom: 10px;
  cursor: pointer;

  &:hover {
    color: var(--main1-color);
  }
`;

const RightContext = styled.div`
  flex-grow: 1; /* 이 부분을 추가합니다. */
  overflow-y: auto; /* 내용이 넘칠 경우 스크롤이 생기도록 합니다. */
  width: 80%;
  /* overflow-y: scroll; */
  padding: 40px;
  background-color: #ffffff69;
  &::-webkit-scrollbar {
    width: 10px;
    height: 100vh;
  }
  &::-webkit-scrollbar-thumb {
    height: 3px;
    background-color: var(--main2-color);
  }
  &::-webkit-scrollbar-track {
    background-color: var(--main3-color);
  }
`;

const Exit = styled.div`
  display: flex;
  align-items: center;
  gap: 6px;
  margin-top: 55%;
  color: var(--error-color);
  font-size: 14px;
  font-weight: 700;
  cursor: pointer;
`;

const TopInfoFrame = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;

  border-bottom: 0.1px solid var(--white-color);

  .button {
    display: flex;
    align-items: center;
  }
`;

interface StyleProps {
  isSubmit: boolean;
}
const AddButton = styled.div<StyleProps>`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  background-color: ${(props) =>
    props.isSubmit ? `var(--main1-color)` : `var(--white-color)`};
  color: ${(props) =>
    props.isSubmit ? `var(--white-color)` : `var(--main1-color)`};
  font-weight: 700;
  margin-right: 30px;
  border-radius: 6px;
  cursor: pointer;
`;

const TemporaryButton = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  /* background-color: var(--white-color); */
  color: var(--main1-color);
  font-weight: 700;
  margin-right: 30px;
  border-radius: 6px;
  cursor: pointer;
`;

export default AddFund;
