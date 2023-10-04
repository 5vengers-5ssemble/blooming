import styled, { css, keyframes } from 'styled-components';

interface Props {
  score: number;
  total: number;
  background: string;
  height?: string;
}
const ProgressBarFrame = ({ score, total, background, height }: Props) => {
  return (
    <ProgressBar>
      <Progress
        score={score}
        total={total}
        background={background}
        height={height}
      >
        <Bar background={background} height={height}></Bar>
      </Progress>
    </ProgressBar>
  );
};

export const ProgressBar = styled.div`
  margin-top: 15px;
  width: 100%;
  background: var(--gray-color);
  border-radius: 6px;
`;

type BarProps = Omit<Props, 'total' | 'score'>;

export const Progress = styled.div<Props>`
  padding: 1px;
  border-radius: 30px;
  width: ${(props) => (props.score / props.total) * 100}%;
  height: ${(props) => (props.height ? props.height : '9px')};
  background-color: ${(props) => props.background};
  ${({ score, total, background, height }) => css`
    animation: ${progressAnimation(score, total, background, height)} 2s;
  `};
`;

export const progressAnimation = (
  score: number,
  total: number,
  background: string,
  height: string | undefined,
) => keyframes`
  0% {
    width: 5%;
    background-color:${(props) => background};
    height: ${(props) => (height ? height : '9px')}; 
  }
  100% {
    height: ${(props) => (height ? height : '9px')}; 
    width: ${(score / total) * 100}%;
    background-color: ${(props) => background};
  }
`;

export const Bar = styled.div<BarProps>`
  height: ${(props) => (props.height ? props.height : '9px')};
  border-radius: 30px;
  background-color: ${(props) => props.background};
  transition: 0.4s linear;
  transition-property: width, background-color;
`;

export default ProgressBarFrame;
