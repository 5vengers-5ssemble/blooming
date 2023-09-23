package com.fivengers.blooming.live.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fivengers.blooming.global.exception.live.InvalidSessionIdException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class SessionIdTest {

    @DisplayName("유효한 세션 ID에서 Exception이 발생하지 않는다.")
    @ParameterizedTest(name = "{index}) sessionId = {0}")
    @ValueSource(strings = {"blooming1", "blooming2", "blooming100"})
    public void 유효한_세션_ID에서_Exception이_발생하지_않는다(String sessionId) {
        assertThatNoException().isThrownBy(() -> SessionId.validate(sessionId));
    }

    @DisplayName("잘못된 세션 ID에서 InvalidSessionIdException이 발생한다.")
    @ParameterizedTest(name = "{index}) sessionId = {0}")
    @ValueSource(strings = {"", " ", "abc", "blooming", "abcblooming", "bloomingabc", "bloomingasd1", "blooming2sf", "blooming3 "})
    public void 잘못된_세션_ID에서_InvalidSessionIdException이_발생한다(String sessionId) {
        assertThatThrownBy(() -> SessionId.validate(sessionId))
                .isInstanceOf(InvalidSessionIdException.class);
    }

    @DisplayName("SessionId에서 LiveId를 가져올 수 있다.")
    @ParameterizedTest(name = "{index}) sessionId = {0}, liveId = {1}")
    @MethodSource("providedSessionIdAndLiveId")
    public void SessionId에서_LiveId를_가져올_수_있다(String sessionIdInput, Long liveId) {
        SessionId sessionId = new SessionId(sessionIdInput);
        assertThat(sessionId.getLiveId()).isEqualTo(liveId);
    }

    private static Stream<Arguments> providedSessionIdAndLiveId() {
        return Stream.of(
                Arguments.of("blooming1", 1L),
                Arguments.of("blooming2", 2L),
                Arguments.of("blooming100", 100L)

        );
    }

    @DisplayName("SessionId에서 LiveId를 가져올 때 유효한 SessionId가 아니라면 InvalidSessionIdException이 발생한다.")
    @ParameterizedTest(name = "{index}) sessionId = {0}")
    @ValueSource(strings = {"", " ", "abc", "blooming", "abcblooming", "bloomingabc", "bloomingasd1", "blooming2sf", "blooming3 "})
    public void SessionId에서_LiveId를_가져올_때_유효한_SessionId가_아니라면_InvalidSessionIdException이_발생한다(String sessionIdInput) {
        SessionId sessionId = new SessionId(sessionIdInput);
        assertThatThrownBy(() -> sessionId.getLiveId())
                .isInstanceOf(InvalidSessionIdException.class);
    }


}