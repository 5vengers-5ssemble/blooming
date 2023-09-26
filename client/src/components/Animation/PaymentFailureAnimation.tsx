import Lottie from 'lottie-react';
import paymentFailureLottie from '@assets/lottie/payment-failure.json';
import styled from 'styled-components';

const PaymentFailureAnimation = () => {
  return (
    <LottieFrame>
      <Lottie
        className="lottie"
        animationData={paymentFailureLottie}
        height={200}
        width={200}
        loop={false}
      />
    </LottieFrame>
  );
};

const LottieFrame = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;

  .lottie {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 38%;
    margin-top: 45px;
  }
`;
export default PaymentFailureAnimation;
