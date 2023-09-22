import PostSuccessAnimation from '@components/common/PostSuccessAnimation';
import { POST_CATEGORY } from '@components/common/constant';
import React from 'react';
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';

const PostSuccess = () => {
  const { category } = useParams<{ category: string }>();
  const navigate = useNavigate();

  switch (category) {
    case POST_CATEGORY.artistRegister:
    case POST_CATEGORY.fundRegister:
      return (
        <CenterFrame>
          <PostSuccessAnimation />
          <div className="title">등록 신청이 완료되었어요 😊</div>
          <div className="sub-title">신청이 승인되면 다시 알려드릴게요!</div>
          <div className="route" onClick={() => navigate('/mypage')}>
            마이페이지로 이동하기
          </div>
        </CenterFrame>
      );
  }

  return <div></div>;
};

const CenterFrame = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 10px;

  .title {
    margin-top: -80px;
    font-weight: 700;
    font-size: 30px;
    color: var(--main1-color);
  }

  .sub-title {
    margin-top: 10px;
    font-size: 20px;
    font-weight: 500;
  }

  .route {
    margin-top: 50px;
    width: max-content;
    padding: 10px 15px;
    background-color: var(--main4-color);
    color: var(--white-color);
    border-radius: 6px;
  }
`;
export default PostSuccess;
