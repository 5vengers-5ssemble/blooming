import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { MainTitle } from '@style/common';
import { FundAddInfo, StoryInfoInAdd } from '@type/ProcessInfo';
import { FormForLongText, FormForText, FormForUpload } from './FormComponent';
import {
  validIntroduce,
  validTotalAmount,
} from '@utils/validation/AddFundInfoCheck';

interface Props {
  data: StoryInfoInAdd;
  setData: React.Dispatch<React.SetStateAction<FundAddInfo>>;
}

export interface ValidCheck {
  validIdx: number;
  validValue: string;
  isValid: boolean;
}

const updatestoryInfo = (prevInfo: FundAddInfo, storyInfo: StoryInfoInAdd) => {
  return {
    ...prevInfo,
    storyInfo: storyInfo,
  };
};

const StoryWrite = ({ data, setData }: Props) => {
  const [storyInfo, setstoryInfo] = useState<StoryInfoInAdd>(data);
  const [validInputCheck, setValidInputCheck] = useState<ValidCheck>({
    validIdx: 0,
    validValue: '',
    isValid: false,
  });

  useEffect(() => {
    if (validInputCheck.isValid) {
      switch (validInputCheck.validIdx) {
        case 0:
          //프로젝트 소개
          storyInfo.summary = validInputCheck.validValue;
          setData((prevInfo) => updatestoryInfo(prevInfo, storyInfo));
          break;

        case 1:
          //앨범 정보
          storyInfo.moreInfo.album_desc = validInputCheck.validValue;
          setData((prevInfo) => updatestoryInfo(prevInfo, storyInfo));
          break;

        case 2:
          //트랙리스트
          storyInfo.moreInfo.track_list = validInputCheck.validValue;
          setData((prevInfo) => updatestoryInfo(prevInfo, storyInfo));
          break;

        case 3:
          //구성품
          storyInfo.moreInfo.album_img = validInputCheck.validValue;
          setData((prevInfo) => updatestoryInfo(prevInfo, storyInfo));
          break;

        case 4:
          //프로젝트 티저 영상
          storyInfo.teaser = validInputCheck.validValue;
          setData((prevInfo) => updatestoryInfo(prevInfo, storyInfo));
          break;

        case 5:
          //프로젝트 예산
          storyInfo.budget = parseInt(validInputCheck.validValue);
          setData((prevInfo) => updatestoryInfo(prevInfo, storyInfo));
          break;

        default:
          break;
      }
    }
  }, [validInputCheck]);

  return (
    <div>
      <Title>
        스토리 작성<div className="dot"></div>
      </Title>
      <QuestionFrame>
        <Subtitle>이 펀딩에 대한 소개를 적어주세요</Subtitle>
        <Contents>
          <FormForLongText
            title="내 소중한 펀딩을 마음껏 소개해주세요"
            placeholder="펀딩 소개 글을 입력해주세요"
            validIdx={0}
            setValid={setValidInputCheck}
            errorCheck={validIntroduce}
            initKeyword={storyInfo.summary}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>프로젝트에 대한 정보를 입력해주세요</Subtitle>
        <Contents>
          <FormForLongText
            title="앨범 소개"
            placeholder="앨범 소개 글을 입력해주세요"
            validIdx={1}
            setValid={setValidInputCheck}
            errorCheck={validIntroduce}
            initKeyword={storyInfo.moreInfo.album_desc}
          />
          <FormForUpload
            title="트랙 리스트"
            subInfo={[
              '트랙리스트 이미지 원본을 업로드 해주세요.',
              'JPG, JPEG, PNG / 10MB 이하 파일 1개만 업로드 가능해요.',
            ]}
            setValid={setValidInputCheck}
            validIdx={2}
          />
          <FormForUpload
            title="구성품"
            subInfo={[
              '앨범 구성품 예시 이미지 원본을 업로드 해주세요.',
              'JPG, JPEG, PNG / 10MB 이하 파일 1개만 업로드 가능해요.',
            ]}
            setValid={setValidInputCheck}
            validIdx={3}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>관련 티저 영상이 있으신가요?</Subtitle>
        <Contents>
          <FormForText
            title="펀딩과 관련한 짧은 영상이면 좋을 것같아요 !"
            placeholder="영상을 확인할 수 있는 링크를 입력해주세요"
            validIdx={4}
            setValid={setValidInputCheck}
            errorCheck={validIntroduce}
            initKeyword={storyInfo.teaser}
          />
        </Contents>
      </QuestionFrame>
      <QuestionFrame>
        <Subtitle>마지막으로, 이 프로젝트의 총 예산을 적어주세요</Subtitle>
        <Contents>
          <FormForText
            title="투자자들에게 예산 정보가 공개됩니다"
            placeholder="총 예산을 입력해주세요"
            validIdx={5}
            setValid={setValidInputCheck}
            errorCheck={validTotalAmount}
            initKeyword={storyInfo.budget.toString()}
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

export default StoryWrite;
