package com.fivengers.blooming.project.adapter.out.persistence.entity;


import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.ProjectTypeConverter;
import com.fivengers.blooming.project.domain.ProjectType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "project")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted = false")
public class ProjectJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long fundingAmount;
    @Column(nullable = false)
    private Long targetAmount;
    @Column(nullable = false)
    private LocalDateTime startedAt;
    @Column(nullable = false)
    private LocalDateTime endedAt;
    @Column(nullable = false)
    private String introduction;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String teaserVideoUrl;
    @Column
    private Integer revenuePercent;
    @Column(nullable = false)
    private String profileImg;
    @Column(nullable = false)
    private Boolean deleted;
    @Convert(converter = ProjectTypeConverter.class)
    private ProjectType dtype;


    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artist;

    public ProjectJpaEntity(Long id,
            String name,
            Long fundingAmount,
            Long targetAmount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String introduction,
            String description,
            String teaserVideoUrl,
            Integer revenuePercent,
            String profileImg,
            Boolean deleted,
            ProjectType dtype,
            ArtistJpaEntity artist) {
        this.id = id;
        this.name = name;
        this.fundingAmount = fundingAmount;
        this.targetAmount = targetAmount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.introduction = introduction;
        this.description = description;
        this.teaserVideoUrl = teaserVideoUrl;
        this.revenuePercent = revenuePercent;
        this.profileImg = profileImg;
        this.deleted = deleted;
        this.dtype = dtype;
        this.artist = artist;
    }
}
