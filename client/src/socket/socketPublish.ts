import { CompatClient } from '@stomp/stompjs';
import CONSOLE from '@utils/consoleColors';

// interface socketPubHeader {
//   Authorization: string;
//   sessionId: string;
//   liveUserName: string;
//   [key: string]: string;
// }

const emojiPuB = (
  client: React.MutableRefObject<any>,
  motionModelId: number | string | undefined,
  motionName: string,
  header: any,
) => {
  client.current.publish({
    destination: `/pub/lives/emoji`,
    body: JSON.stringify({
      motionModelId: motionModelId,
      motionName: motionName,
    }),
    headers: header,
  });
  CONSOLE.socket(`emoji ${motionName} published!!`)
};

export { emojiPuB };
