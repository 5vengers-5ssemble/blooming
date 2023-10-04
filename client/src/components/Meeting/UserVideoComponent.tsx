import React from 'react';
import styled from 'styled-components';
import OpenViduVideoComponent from './OvVideo';
import { Publisher, Subscriber } from 'openvidu-browser'; // Import the Publisher type from openvidu-browser

interface UserVideoComponentProps {
  nickname: string;
  streamManager?: Subscriber | Publisher | null; // Define the streamManager as optional
  $isMain?: boolean;
}

const UserVideoComponent: React.FC<UserVideoComponentProps> = ({
  nickname,
  streamManager,
  $isMain,
}) => {
  /**
   * Get the nickname from the streamManager's connection data.
   * @returns {string}
   */
  function getNicknameTag(): string {
    if (streamManager) {
      const connectionData = streamManager.stream.connection?.data;
      if (connectionData) {
        return JSON.parse(connectionData).clientData;
      }
    }
    return nickname;
  }

  return (
    <div>
      {streamManager !== undefined ? (
        <MainVideoContainer $isMain={$isMain ? $isMain : false}>
          <OpenViduVideoComponent streamManager={streamManager} />
          <NicknameBox>{getNicknameTag()}</NicknameBox>
        </MainVideoContainer>
      ) : null}
    </div>
  );
};

interface StyleProps {
  $isMain: boolean;
}

const MainVideoContainer = styled.div<StyleProps>`
  position: relative;
  margin: 0 auto;

  video {
    width: 100%;
    height: ${(props) => (props.$isMain ? '100vh' : 'auto')};
    cursor: pointer;
    background-color: var(--black-color);
  }
`;

const NicknameBox = styled.div`
  position: absolute;
  background: #00000064;
  padding: 6px 15px;
  color: var(--white-color);
  font-weight: 400;
  border-radius: 2px;
  top: 0;
  left: 0;
`;

export default UserVideoComponent;
