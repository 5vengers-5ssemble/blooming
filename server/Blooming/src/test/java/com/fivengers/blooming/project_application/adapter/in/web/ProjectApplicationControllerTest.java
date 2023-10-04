package com.fivengers.blooming.project_application.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.project.domain.ProjectType;
import com.fivengers.blooming.project_application.application.port.in.ProjectApplicationUseCase;
import com.fivengers.blooming.project_application.application.port.in.dto.BasicInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.MakerInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.MoreInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.PolicyInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.ProjectApplicationRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.ProjectInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.SettlementInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.StoryInfoRequest;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ProjectApplicationController.class)
public class ProjectApplicationControllerTest extends RestDocsTest {

    @MockBean
    ProjectApplicationUseCase projectApplicationUseCase;

    @Test
    @DisplayName("콘서트 펀딩 프로젝트 신청서를 등록한다.")
    void projectApplicationAddTest() throws Exception {

        ProjectApplicationRequest request = createRequest();

        ResultActions perform = mockMvc.perform(post("/api/v1/project-applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.NO_CONTENT.value()));

        perform.andDo(print())
                .andDo(document("project-application-add",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    private ProjectApplicationRequest createRequest() {

        LocalDateTime now = LocalDateTime.now();

        ProjectInfoRequest projectInfoRequest = new ProjectInfoRequest(
                ProjectType.CONCERT.getValue(),
                MakerInfoRequest.builder()
                        .licenseNumber("1234567890")
                        .companyName("무명 엔터테인먼트")
                        .licenseImage("/licenseImg1")
                        .sealCertificate("/seal_certificate1")
                        .build(),
                50_000_000L);

        BasicInfoRequest basicInfoRequest = BasicInfoRequest.builder()
                .title("김싸피 데뷔 1주년 기념 콘서트")
                .thumbnail("/thumbnail1")
                .startDate(now)  // Validation에 걸리지 않기 위해 1분을 더해줌 (현재-미래만 가능)
                .endDate(now.plusMonths(3))
                .build();

        StoryInfoRequest storyInfoRequest = StoryInfoRequest.builder()
                .introduction("김싸피의 데뷔 1주년을 기념하는 성대한 콘서트")
                .moreInfo(new MoreInfoRequest(
                        "많이많이 와주세용",
                        "/setlist1",
                        "/composition1"))
                .teaser("www.youtube.com/kimssafy")
                .budget(100_000_000L)
                .build();

        SettlementInfoRequest settlementInfoRequest = SettlementInfoRequest.builder()
                .representative("박싸피")
                .email("parkssafy@ssafy.co.kr")
                .accountNumber("1234567890123")
                .bankbookImage("/bankbook1")
                .build();

        PolicyInfoRequest policyInfoRequest = new PolicyInfoRequest(true, true);

        return ProjectApplicationRequest.builder()
                .projectInfo(projectInfoRequest)
                .basicInfo(basicInfoRequest)
                .storyInfo(storyInfoRequest)
                .settlementInfo(settlementInfoRequest)
                .policyInfo(policyInfoRequest)
                .build();
    }
}
