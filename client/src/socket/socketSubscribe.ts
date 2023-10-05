import CONSOLE from '@utils/consoleColors';

const emojiSUB = (
  client: React.MutableRefObject<any>,
  callback: Function,
  sessionId: string | null,
) => {
  client.current.subscribe(
    `/topic/lives/${sessionId}/emoji`,
    (message: any) => {
      console.log(message);
      const emojiResponse = new TextDecoder().decode(message._binaryBody);
      const emojiData = JSON.parse(emojiResponse);
      CONSOLE.socket(`RECEIVED emoji`);
      console.log(emojiData);
      callback({
        emojiCode: emojiData.emojiCode,
        messageId: message.headers["message-id"],
      });
    },
  );
};

const errorSUB = (client: React.MutableRefObject<any>, callback: Function) => {
  client.current.subscribe(`/user/queue/error`, (message: any) => {
    CONSOLE.errorWithPrefix('socket', '소켓통신 중 에러 발생');
    const errorInfo = new TextDecoder().decode(message._binaryBody);
    console.log(JSON.parse(errorInfo));
    const error = JSON.parse(message);
    callback(error);
  });
};
export { emojiSUB, errorSUB };
