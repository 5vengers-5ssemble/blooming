import PaymentFailureAnimation from '@components/Animation/PaymentFailureAnimation';
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';

const PaymentSuccess = () => {
  const navigate = useNavigate();

  return (
    <>
      <PaymentFailureAnimation />
      <CenterFrame>
        <div className="title">결제에 실패했습니다.</div>
        {/* 주소 수정 필요 */}
        <NaviBtnBox>
          <div className="go_back_page" onClick={() => navigate('/')}>
            뒤로가기
          </div>
        </NaviBtnBox>
      </CenterFrame>
    </>
  );
};

const NaviBtnBox = styled.div`
  display: flex;
  margin-top: 25px;
`;

const CenterFrame = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 20px;

  .title {
    margin-top: ;
    font-weight: 700;
    font-size: 30px;
    color: var(--main1-color);
  }

  .go_mypage {
    cursor: pointer;

    width: max-content;
    padding: 10px 15px;
    background-color: var(--main4-color);
    color: var(--white-color);
    border-radius: 6px;
  }
  .go_back_page {
    cursor: pointer;

    width: max-content;
    padding: 10px 15px;
    background-color: #c7c7c7;
    color: var(--white-color);
    border-radius: 6px;
    margin-right: 25px;
  }
`;
export default PaymentSuccess;
