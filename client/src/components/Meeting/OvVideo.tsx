import React, { useEffect, useRef } from 'react';
import { Publisher, Subscriber } from 'openvidu-browser'; // Import the Publisher type from openvidu-browser

interface OpenViduVideoComponentProps {
  streamManager?: Subscriber | Publisher | null; // Define the streamManager as optional
}

const OpenViduVideoComponent: React.FC<OpenViduVideoComponentProps> = ({
  streamManager,
}) => {
  const videoRef = useRef<HTMLVideoElement | null>(null); // Add the type for videoRef

  useEffect(() => {
    if (videoRef.current && streamManager) {
      // video 태그 src 설정
      streamManager.addVideoElement(videoRef.current);
    }
  }, [streamManager]);

  return <video autoPlay ref={videoRef} />;
};

export default OpenViduVideoComponent;
