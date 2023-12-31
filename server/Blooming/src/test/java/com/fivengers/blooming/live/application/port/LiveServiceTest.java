package com.fivengers.blooming.live.application.port;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.live.application.LiveService;
import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class LiveServiceTest {
    @Mock
    LivePort livePort;

    @InjectMocks
    LiveService liveService;
    Pageable pageable;
    List<Live> lives;

    @BeforeEach
    void initObjects() {
        initData();
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("키워드로 검색된 라이브 목록을 불러온다.")
    void 키워드로_검색된_라이브_목록을_불러온다() {
        // given
        given(livePort.findByKeyword(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(
                        lives,
                        pageable,
                        2
                ));
        // when
        Page<Live> searchedLives = liveService.searchByKeyword("찹쌀", pageable);

        // then
        assertThat(searchedLives).hasSize(5);
    }

    private void initData() {
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();

        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .member(member)
                .build();

        String[] times = {null, "2023-09-19T13:00:00", null, "2023-09-19T13:00:00", null };
        String[] titles = {"헬로우", "김태우의 낙하산", "아이유의 통통찹쌀볼", "Summary의 김찹쌀", "맛있는 찹쌀떡 무료 공유"};
        lives = IntStream.range(0, 5).mapToObj((i) -> Live.builder()
                .id(1L)
                .title(titles[i])
                .artist(artist)
                .createdAt(now)
                .modifiedAt(now)
                .endedAt(times[i] == null ? null : LocalDateTime.parse(times[i]))
                .build()).toList();
    }
}