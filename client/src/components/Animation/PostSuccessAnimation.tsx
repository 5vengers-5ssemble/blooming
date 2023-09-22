import Lottie from 'lottie-react';
import postSuccessLottie from '@assets/lottie/post-success.json';
import styled from 'styled-components';

const PostSuccessAnimation = () => {
  return (
    <LottieFrame>
      <Lottie
        className="lottie"
        animationData={postSuccessLottie}
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
export default PostSuccessAnimation;
