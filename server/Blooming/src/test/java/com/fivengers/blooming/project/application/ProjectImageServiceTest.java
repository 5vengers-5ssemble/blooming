package com.fivengers.blooming.project.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.application.service.ProjectImageService;
import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.project.domain.ProjectImage;
import com.fivengers.blooming.fixture.project.adapter.out.persistence.FakeProjectImagePersistenceAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectImageServiceTest {

    FakeProjectImagePersistenceAdapter projectImagePersistenceAdapter;
    ProjectImageService projectImageService;

    @BeforeEach
    void beforeEach() {
        this.projectImagePersistenceAdapter = new FakeProjectImagePersistenceAdapter();
        this.projectImageService = new ProjectImageService(projectImagePersistenceAdapter);
    }

    @Test
    @DisplayName("하나의 콘서트 펀딩 프로젝트에 속한 사진들을 조회한다.")
    void searchConcertImage() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Concert concert = Concert.builder()
                .id(1L)
                .name("아이유 데뷔 20주년 콘서트")
                .startedAt(now)
                .endedAt(now)
                .description("아이유 데뷔 20주년을 기념하는 콘서트")
                .createdAt(now)
                .modifiedAt(now)
                .artist(Artist.builder().build())
                .build();
        List<ProjectImage> images = new ArrayList<>();
        LongStream.range(1, 11).forEach(idx -> {
            ProjectImage image = ProjectImage.builder()
                    .id(idx)
                    .imageUrl("image/" + idx)
                    .project(concert)
                    .build();
            images.add(image);
            projectImagePersistenceAdapter.save(image);
        });

        // when
        List<ProjectImage> found = projectImageService.findAllByProjectId(concert.getId());

        //then
        assertThat(found).hasSize(10);
        images.forEach(image -> assertThat(found).contains(image));
    }
}
