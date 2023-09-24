package com.fivengers.blooming.live.domain;

import com.fivengers.blooming.global.exception.global.UnknownServerLogicException;
import com.fivengers.blooming.global.exception.live.InvalidSessionIdException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionId {
    private static final String PREFIX = "blooming";
    private static final String VALIDATE_REGEX = "^" + PREFIX +"[0-9]+$";

    private String sessionId;

    public static String makeSessionId(Long liveId) {
        return PREFIX + liveId;
    }

    public static void validate(String sessionId) {
        Pattern pattern = Pattern.compile(VALIDATE_REGEX);
        Matcher matcher = pattern.matcher(sessionId);
        if (!matcher.matches()) {
            throw new InvalidSessionIdException();
        }
    }

    public Long getLiveId() {
        SessionId.validate(sessionId);

        Pattern pattern = Pattern.compile(PREFIX);
        Matcher matcher = pattern.matcher(sessionId);

        if (matcher.find()) {
            return Long.parseLong(sessionId.substring(matcher.end()));
        }

        throw new UnknownServerLogicException();
    }
}
