package com.fivengers.blooming.artist.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
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

@Entity
@Table(name = "artist_application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistApplicationJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "artist_application_id")
    private Long id;

    @Column(nullable = false)
    private String stageName;

    @Column(nullable = false)
    private String agency;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ArtistApplicationState applicationState;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private String youtubeUrl;

    @Column(nullable = false)
    private String fanCafeUrl;

    @Column(nullable = false)
    private String snsUrl;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @Builder
    public ArtistApplicationJpaEntity(Long id, String stageName, String agency, String description,
            ArtistApplicationState applicationState, String profileImageUrl, String youtubeUrl,
            String fanCafeUrl, String snsUrl, MemberJpaEntity memberJpaEntity) {
        this.id = id;
        this.stageName = stageName;
        this.agency = agency;
        this.description = description;
        this.applicationState = applicationState;
        this.profileImageUrl = profileImageUrl;
        this.youtubeUrl = youtubeUrl;
        this.fanCafeUrl = fanCafeUrl;
        this.snsUrl = snsUrl;
        this.memberJpaEntity = memberJpaEntity;
    }

    public void update(ArtistApplication application) {
        this.stageName = application.getStageName();
        this.agency = application.getAgency();
        this.description = application.getDescription();
        this.applicationState = application.getApplicationState();
        this.profileImageUrl = application.getProfileImageUrl();
        this.youtubeUrl = application.getYoutubeUrl();
        this.fanCafeUrl = application.getFanCafeUrl();
        this.snsUrl = application.getSnsUrl();
    }
}
