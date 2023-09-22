package com.fivengers.blooming.project.adapter.out.persistence.entity;


import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
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
    @Column
    private String name;
    @Column
    private Long fundingAmount;
    @Column
    private Long targetAmount;
    @Column
    private LocalDateTime startedAt;
    @Column
    private LocalDateTime endedAt;
    @Column
    private String introduction;
    @Column
    private String description;
    @Column
    private String teaserVideoUrl;
    @Column
    private Integer revenuePercent;
    @Column
    private Boolean deleted;

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
            Boolean deleted,
            ArtistJpaEntity artist) {
        this.id = id;
        this.name = name;
        this.fundingAmount = fundingAmount;
        this.targetAmount = targetAmount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.introduction = introduction;
        this.description = description;
        this.revenuePercent = revenuePercent;
        this.teaserVideoUrl = teaserVideoUrl;
        this.deleted = deleted;
        this.artist = artist;
    }
}
