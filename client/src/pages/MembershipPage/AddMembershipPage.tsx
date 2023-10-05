import styled from 'styled-components';
import { ReactComponent as LogoutSvg } from '@assets/icons/logout.svg';
import { useNavigate } from 'react-router-dom';
import AddMembership from '@components/AddMembershipPage/MembershipInfo';

const subtitleData = [' ', ' ', ' ', ' ', ' ', ' ', ' '];

const AddMembershipPage = () => {
  const navigate = useNavigate();

  return (
    <BackgroundGrad>
      <AddFrame>
        <TopInfoFrame>
          <Title>아티스트 멤버쉽 등록</Title>
        </TopInfoFrame>
        <ContextFrame>
          <LeftContext>
            {subtitleData.map((subtitle, index) => (
              <Subtitle
                key={index}
                onClick={() => console.log()}
                $active={false}
              >
                {subtitle}
              </Subtitle>
            ))}
            <Exit onClick={() => navigate('/mypage/3')}>
              나가기 <LogoutSvg />
            </Exit>
          </LeftContext>
          <RightContext>
            <AddMembership/>
          </RightContext>
        </ContextFrame>
      </AddFrame>
    </BackgroundGrad>
  );
};

const BackgroundGrad = styled.div`
  background-color: var(--background2-color);
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
  $active: boolean;
}

const Subtitle = styled.div<Props>`
  color: ${(props) =>
    props.$active ? 'var(--main1-color)' : 'var(--main2-color)'};
  font-size: 16px;
  font-weight: ${(props) => (props.$active ? '700' : '600')};
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
  $isSubmit: boolean;
}
const AddButton = styled.div<StyleProps>`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  background-color: ${(props) =>
    props.$isSubmit ? `var(--main1-color)` : `var(--white-color)`};
  color: ${(props) =>
    props.$isSubmit ? `var(--white-color)` : `var(--main1-color)`};
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

export default AddMembershipPage;
