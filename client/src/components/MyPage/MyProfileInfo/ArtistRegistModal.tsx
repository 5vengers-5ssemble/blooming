import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';

import axios from '@api/apiController';
import {
  isNonEmptyString,
  validCompanyName,
  validNoneCheck,
} from '@utils/validation/AddFundInfoCheck';

import {
  FormForLongText,
  FormForText,
  FormForUpload,
} from '@components/AddFundPage/FormComponent';
import { ValidCheck } from '@components/AddFundPage/ProjectInfo';
import { POST_CATEGORY } from '@components/common/constant';

import { ArtistRequestInfo } from '@type/ArtistRequest';

const ArtistRegistModal = ({
  isOpen,
  closeModal,
}: {
  isOpen: boolean;
  closeModal: () => void;
}) => {
  const navigate = useNavigate();
  const [registInfo, setRegistInfo] = useState<ArtistRequestInfo>({
    stageName: '',
    agency: '',
    description: '',
    fanCafeUrl: '',
    profileImageUrl: '',
    snsUrl: '',
  });

  const [validInputCheck, setValidInputCheck] = useState<ValidCheck>({
    validIdx: 0,
    validValue: '',
    isValid: false,
  });

  useEffect(() => {
    if (validInputCheck.isValid) {
      const updatedInfo = { ...registInfo };

      switch (validInputCheck.validIdx) {
        case 0:
          updatedInfo.stageName = validInputCheck.validValue;
          break;
        case 1:
          updatedInfo.agency = validInputCheck.validValue;
          break;
        case 2:
          updatedInfo.description = validInputCheck.validValue;
          break;
        case 3:
          updatedInfo.profileImageUrl = validInputCheck.validValue;
          break;
        case 4:
          break;
        case 5:
          updatedInfo.fanCafeUrl = validInputCheck.validValue;
          break;
        case 6:
          updatedInfo.snsUrl = validInputCheck.validValue;
          break;

        default:
          break;
      }
      // console.log(updatedInfo);
      setRegistInfo(updatedInfo);
    } else {
      switch (validInputCheck.validIdx) {
        case 0:
          registInfo.stageName = '';
          break;
        case 1:
          registInfo.agency = '';
          break;
        case 2:
          registInfo.description = '';
          break;
        case 3:
          registInfo.profileImageUrl = '';
          break;
      }
    }
  }, [validInputCheck]);

  const handleRegister = () => {
    if (validArtistRegistInfo()) {
      // console.log(registInfo);
      axios.post('/artist-regist', registInfo).then((res) => {
        // console.log(res.data);
        navigate(`/post-success/${POST_CATEGORY.artistRegister}`);
      });
    } else {
      alert('입력이 완성되지 않았어요 😥');
    }
  };

  const validArtistRegistInfo = (): boolean => {
    return (
      isNonEmptyString(registInfo.stageName) &&
      isNonEmptyString(registInfo.agency) &&
      isNonEmptyString(registInfo.description) &&
      isNonEmptyString(registInfo.profileImageUrl)
    );
  };

  if (!isOpen) return null;

  return (
    <ModalOverlay>
      <ModalWrapper>
        <ModalHeader>
          <h2>아티스트 등록 신청</h2>
          <button onClick={closeModal}>닫기</button>
        </ModalHeader>
        <ModalContent>
          <div className="form">
            <QuestionFrame>
              <Subtitle>아티스트 정보를 입력해주세요</Subtitle>
              <Contents>
                <FormForText
                  title="아티스트 활동명을 입력해주세요"
                  placeholder="정확한 활동명을 써주세요"
                  validIdx={0}
                  setValid={setValidInputCheck}
                  errorCheck={validCompanyName}
                  initKeyword={''}
                />
                <FormForText
                  title="소속사명을 입력해주세요"
                  placeholder="정확한 소속사명을 써주세요"
                  validIdx={1}
                  setValid={setValidInputCheck}
                  errorCheck={validCompanyName}
                  initKeyword={''}
                />
                <FormForLongText
                  title="활동 소개를 입력해주세요"
                  placeholder="어떤 소개든 좋아요"
                  validIdx={2}
                  setValid={setValidInputCheck}
                  errorCheck={validCompanyName}
                  initKeyword={''}
                />
                <FormForUpload
                  title="프로필 이미지를 업로드해주세요"
                  subInfo={[
                    'JPG, JPEG, PNG, PDF / 10MB 이하 파일 1개만 업로드 가능해요.',
                  ]}
                  setValid={setValidInputCheck}
                  validIdx={3}
                />
              </Contents>
            </QuestionFrame>
            <QuestionFrame>
              <Subtitle>아티스트님의 다양한 활약을 보여주세요</Subtitle>
              <Contents>
                <FormForText
                  title="팬카페 링크가 있으신가요?"
                  placeholder="팬카페 링크를 입력해주세요"
                  validIdx={5}
                  setValid={setValidInputCheck}
                  errorCheck={validNoneCheck}
                  initKeyword={''}
                />
                <FormForText
                  title="SNS가 있으신가요?"
                  placeholder="SNS 링크를 입력해주세요"
                  validIdx={6}
                  setValid={setValidInputCheck}
                  errorCheck={validNoneCheck}
                  initKeyword={''}
                />
              </Contents>
            </QuestionFrame>
          </div>
          <div className="register">
            <ButtonForRegist onClick={handleRegister}>등록하기</ButtonForRegist>
          </div>
        </ModalContent>
      </ModalWrapper>
    </ModalOverlay>
  );
};

const QuestionFrame = styled.div`
  margin-top: 50px;
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
    font-weight: 600;
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

  .formlist {
    display: flex;
    flex-direction: column;

    > .rows {
      margin-bottom: 15px;
    }
  }
`;

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

const ModalWrapper = styled.div`
  background: white;
  border-radius: 4px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  max-width: 600px;
  width: 100%;
  height: 90vh;

  overflow-y: scroll;
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

const ModalHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 40px 30px 0px 50px;

  h2 {
    margin: 0;
  }

  button {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 16px;
    color: var(--main1-color);
    margin-top: -10px;
    font-weight: 600;
  }
`;

const ModalContent = styled.div`
  padding: 0 50px 30px;

  .register {
    display: flex;
    justify-content: center;
  }
`;

const ButtonForRegist = styled.div`
  cursor: pointer;
  width: max-content;
  margin-top: 50px;
  align-items: center;
  justify-content: center;
  text-align: center;
  background-color: var(--main1-color);
  color: var(--white-color);
  padding: 8px 20px;
  font-weight: 400;
  border-radius: 6px;
`;

//

const ContentTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
`;

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const InputBox = styled.div`
  display: flex;
  gap: 15px;
  align-items: center;
`;

const InputField = styled.input`
  padding: 5px;
  border: none;
  border-bottom: 1px solid var(--main2-color);
  width: 380px;
  &::placeholder {
    color: var(--gray-color);
    font-weight: 300;
  }
  &:focus {
    outline: none !important;
  }
`;

const AddButton = styled.div`
  margin: -5px 0 10px;
  color: var(--main1-color);
  font-weight: 600;
  font-size: 14px;
  width: fit-content;
  display: flex;
  align-items: center;
  gap: 6px;
  cursor: pointer;
  svg {
    width: 16px;
    height: 16px;
  }
`;

const RemoveButton = styled.button`
  color: var(--error-color);
  align-items: center;
  gap: 4px;
  display: flex;
  border: none;
  background-color: white;
  cursor: pointer;

  svg {
    width: 18px;
    height: 18px;
    color: var(--error-color);
  }
`;

export default ArtistRegistModal;
