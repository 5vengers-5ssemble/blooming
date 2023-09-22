import ErrorAnimation from '@components/Animation/ErrorAnimation';
import { useNavigate } from 'react-router';
import styled from 'styled-components';

const PageNotFound = () => {
  const navigate = useNavigate();
  return (
    <CenterFrame>
      <ErrorAnimation />
      <div className="title">404 NOT FOUND</div>
      <div className="sub-title">페이지 정보가 없어요!</div>
      <div className="route" onClick={() => navigate('/')}>
        메인으로 이동하기
      </div>
    </CenterFrame>
  );
};

const CenterFrame = styled.div`
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .title {
    margin-top: 40px;
    font-weight: 700;
    font-size: 40px;
    color: var(--main1-color);
  }

  .sub-title {
    margin-top: 10px;
    font-size: 20px;
    font-weight: 500;
  }

  .route {
    cursor: pointer;
    margin-top: 50px;
    width: max-content;
    padding: 10px 15px;
    background-color: var(--main4-color);
    color: var(--white-color);
    border-radius: 6px;
  }
`;

export default PageNotFound;
