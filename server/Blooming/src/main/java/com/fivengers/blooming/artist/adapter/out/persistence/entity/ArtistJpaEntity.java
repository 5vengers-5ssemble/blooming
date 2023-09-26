package com.fivengers.blooming.artist.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
 import org.hibernate.annotations.Where;

@Entity
@Table(name = "artist")
@Getter
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(nullable = false)
    private String stageName;

    @Column
    private String agency;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private String youtubeUrl;

    @Column(nullable = false)
    private String fanCafeUrl;

    @Column(nullable = false)
    private String snsUrl;

    @Column(nullable = false)
    private Boolean deleted;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @Builder
    public ArtistJpaEntity(
            Long id,
            String stageName,
            String agency,
            String description,
            String profileImageUrl,
            String youtubeUrl,
            String fanCafeUrl,
            String snsUrl,
            Boolean deleted,
            MemberJpaEntity memberJpaEntity) {
        this.id = id;
        this.stageName = stageName;
        this.agency = agency;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.youtubeUrl = youtubeUrl;
        this.fanCafeUrl = fanCafeUrl;
        this.snsUrl = snsUrl;
        this.deleted = deleted;
        this.memberJpaEntity = memberJpaEntity;
    }

    public void update(Artist artist) {
        this.stageName = artist.getStageName();
        this.agency = artist.getAgency();
        this.description = artist.getDescription();
        this.profileImageUrl = artist.getProfileImageUrl();
        this.youtubeUrl = artist.getYoutubeUrl();
        this.fanCafeUrl = artist.getFanCafeUrl();
        this.snsUrl = artist.getSnsUrl();
    }
}
