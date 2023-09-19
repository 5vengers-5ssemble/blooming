package com.fivengers.blooming.Payment.adapter.out.persistence.entity;

import com.fivengers.blooming.Artist.domain.ArtistJpaEntity;
import com.fivengers.blooming.Member.domain.MemberJpaEntity;
import com.fivengers.blooming.Payment.domain.ProjectType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artistJpaEntity;

    @Column
    private ProjectType projectType;

    @Column
    private Long projectId;

    @Column(nullable = true)
    private String paymentKey;

    @Column
    private String orderId;

    @Column
    private Long amount;

    @Builder
    public PaymentJpaEntity(Long id, MemberJpaEntity memberJpaEntity,
            ArtistJpaEntity artistJpaEntity, ProjectType projectType, Long projectId,
            String paymentKey, String orderId, Long amount){
        this.id = id;
        this.memberJpaEntity = memberJpaEntity;
        this.artistJpaEntity = artistJpaEntity;
        this.projectType = projectType;
        this.projectId = projectId;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
    }

}
