package com.fivengers.blooming.project.adapter.out.persistence.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectImageJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.ConcertMapper;
import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.project.domain.Project;
import com.fivengers.blooming.project.domain.ProjectImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
public class ProjectImagePersistenceAdapterTest {

    @Autowired
    ConcertSpringDataRepository concertSpringDataRepository;
    @Autowired
    ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired
    ProjectImageSpringDataRepository projectImageSpringDataRepository;
    @Autowired
    ProjectImagePersistenceAdapter projectImagePersistenceAdapter;

    @Test
    @DisplayName("하나의 콘서트 펀딩 프로젝트에 속한 사진목록을 조회한다.")
    void searchConcertImage() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ConcertJpaEntity concert = ConcertJpaEntity.builder()
                .name("아이유 데뷔 20주년 콘서트")
                .startedAt(now)
                .endedAt(now.plusMonths(3))
                .description("아이유 데뷔 20주년을 기념하는 콘서트")
                .build();
        concertSpringDataRepository.save(concert);
        List<ProjectImageJpaEntity> images =  new ArrayList<>();
        LongStream.range(1, 11).forEach(idx -> {
            ProjectImageJpaEntity image = ProjectImageJpaEntity.builder()
                    .id(idx)
                    .imageUrl("image/" + idx)
                    .project(concert)
                    .deleted(false)
                    .build();
            images.add(image);
            projectImageSpringDataRepository.save(image);
        });

        // when
        List<ProjectImage> found = projectImagePersistenceAdapter.findAllByProjectId(concert.getId());

        //then
        assertThat(found).hasSize(10);
    }
}
