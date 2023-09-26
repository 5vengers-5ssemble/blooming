import Lottie from 'lottie-react';
import paymentSuccessLottie from '@assets/lottie/payment-success.json';
import styled from 'styled-components';

const PaymentSuccessAnimation = () => {
  return (
    <LottieFrame>
      <Lottie
        className="lottie"
        animationData={paymentSuccessLottie}
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
    width: 50%;
  }
`;
export default PaymentSuccessAnimation;
