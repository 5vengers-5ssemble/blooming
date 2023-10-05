package com.fivengers.blooming.membership.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.membership.domain.MembershipRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership_record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipRecordJpaEntity extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_record_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer saleCount;

    @Column(nullable = false)
    private LocalDateTime startDateOnWeek;

    @Column(nullable = false)
    private LocalDateTime endDateOnWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id")
    private MembershipJpaEntity membershipJpaEntity;

    @Builder
    public MembershipRecordJpaEntity(Long id,
                                     Integer saleCount,
                                     LocalDateTime startDateOnWeek,
                                     LocalDateTime endDateOnWeek,
                                     MembershipJpaEntity membershipJpaEntity) {
        this.id = id;
        this.saleCount = saleCount;
        this.startDateOnWeek = startDateOnWeek;
        this.endDateOnWeek = endDateOnWeek;
        this.membershipJpaEntity = membershipJpaEntity;
    }

    public void update(MembershipRecord membershipRecord) {
        this.saleCount = membershipRecord.getSaleCount();
    }
}
