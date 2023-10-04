import Lottie from 'lottie-react';
import creditLottie from '@assets/lottie/credit-card.json';
import styled from 'styled-components';

const CreditLottie = () => {
  return (
    <LottieFrame>
      <Lottie
        className="lottie"
        animationData={creditLottie}
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
    /* width: 40%; */
  }
`;
export default CreditLottie;
