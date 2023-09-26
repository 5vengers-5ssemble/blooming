package com.fivengers.blooming.artistscrap.adapter.in.web;

import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapRecordUseCase;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import com.fivengers.blooming.support.RestDocsTest;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ArtistScrapRecordController.class)
class ArtistScrapRecordControllerTest extends RestDocsTest {

    @MockBean ArtistScrapRecordUseCase artistScrapRecordUseCase;
    Artist artist;

    @BeforeEach
    void initObjects() {
        LocalDateTime now = LocalDateTime.now();
        artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .build();
    }

    @Test
    @DisplayName("최근 4주의 아티스트 스크랩 기록 목록을 가져온다.")
    void artistScrapRecordList() throws Exception {
        List<ArtistScrapRecord> records = List.of(ArtistScrapRecord.builder()
                        .id(1L)
                        .scrapCount(2)
                        .startDateOnWeek(
                                getThisWeekDateTime(Calendar.SUNDAY, 0, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, 0, 23, 59, 59, 999_999_999))
                        .artist(artist)
                        .build(),
                ArtistScrapRecord.builder()
                        .id(2L)
                        .scrapCount(4)
                        .startDateOnWeek(
                                getThisWeekDateTime(Calendar.SUNDAY, 1, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, 1, 23, 59, 59, 999_999_999))
                        .artist(artist)
                        .build(),
                ArtistScrapRecord.builder()
                        .id(3L)
                        .scrapCount(5)
                        .startDateOnWeek(
                                getThisWeekDateTime(Calendar.SUNDAY, 2, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, 2, 23, 59, 59, 999_999_999))
                        .artist(artist)
                        .build(),
                ArtistScrapRecord.builder()
                        .id(4L)
                        .scrapCount(5)
                        .startDateOnWeek(
                                getThisWeekDateTime(Calendar.SUNDAY, 3, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, 3, 23, 59, 59, 999_999_999))
                        .artist(artist)
                        .build());
        given(artistScrapRecordUseCase.findOnLatestFourWeek(any(Long.class)))
                .willReturn(records);

        ResultActions perform = mockMvc.perform(get("/api/v1/artists/{artistId}/scrap-records", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].startDateOnWeek").value(
                        toJson(records.get(0).getStartDateOnWeek()).replace("\"", "")))
                .andExpect(jsonPath("$.results[1].startDateOnWeek").value(
                        toJson(records.get(1).getStartDateOnWeek()).replace("\"", "")))
                .andExpect(jsonPath("$.results[2].startDateOnWeek").value(
                        toJson(records.get(2).getStartDateOnWeek()).replace("\"", "")))
                .andExpect(jsonPath("$.results[3].startDateOnWeek").value(
                        toJson(records.get(3).getStartDateOnWeek()).replace("\"", "")));

        perform.andDo(print())
                .andDo(document("artist-scrap-record-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("artistId").description("아티스트 ID"))));
    }

    private LocalDateTime getThisWeekDateTime(int dayOfWeek, int prevWeek, int hour, int minute, int second,
            int nanoOfSecond) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.add(Calendar.DATE, 7);
        return LocalDateTime.ofInstant(calendar.getTime().toInstant(),
                        calendar.getTimeZone().toZoneId())
                .minusWeeks(1 + prevWeek)
                .toLocalDate().atTime(hour, minute, second, nanoOfSecond);
    }
}