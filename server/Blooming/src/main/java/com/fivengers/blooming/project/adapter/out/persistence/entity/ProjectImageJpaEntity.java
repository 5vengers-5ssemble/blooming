package com.fivengers.blooming.project.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project Image 테이블
 * 사용하지 않음!!
 */
@Entity
@Table(name = "project_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectImageJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imageUrl;
    @Column
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    @Builder
    public ProjectImageJpaEntity(Long id, String imageUrl, Boolean deleted,
            ProjectJpaEntity project) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.deleted = deleted;
        this.project = project;
    }
}
