package com.fivengers.blooming.project.adapter.out.persistence.entity;


import com.fivengers.blooming.global.audit.BaseTime;
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

@Entity
@Table(name = "view_count")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewCountJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_count_id")
    private Long id;

    @Column
    private Long viewCount;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    @Builder
    public ViewCountJpaEntity(Long id,
            Long viewCount,
            ProjectJpaEntity project) {
        this.id = id;
        this.viewCount = viewCount;
        this.project = project;
    }
}
