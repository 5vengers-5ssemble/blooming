import Lottie from 'lottie-react';
import loadingLottie from '@assets/lottie/loadingLottie.json';
import styled from 'styled-components';

const Loading = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: loadingLottie,
    rendererSettings: {
      preserveAspectRatio: 'xMidYMid slice',
    },
  };

  return (
    <LottieFrame>
      <Lottie
        className="lottie"
        animationData={loadingLottie}
        height={200}
        width={200}
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
    height: 50%;
  }
`;
export default Loading;
