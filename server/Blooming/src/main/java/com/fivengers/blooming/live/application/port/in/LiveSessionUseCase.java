package com.fivengers.blooming.live.application.port.in;

import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailRequest;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;

public interface LiveSessionUseCase {
    String createSession(SessionDetailRequest sessionDetailRequest)
            throws OpenViduJavaClientException, OpenViduHttpException;

    String createConnection(ConnectionTokenDetailRequest connectionTokenDetailRequest)
            throws OpenViduJavaClientException, OpenViduHttpException;

    String searchSessionId(Long liveId);

}
