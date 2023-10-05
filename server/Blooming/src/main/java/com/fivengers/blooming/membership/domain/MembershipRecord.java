package com.fivengers.blooming.membership.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MembershipRecord {

    private Long id;
    private Integer saleCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime startDateOnWeek;
    private LocalDateTime endDateOnWeek;
    private Membership membership;

    @Builder
    public MembershipRecord(Long id,
                            Integer saleCount,
                            LocalDateTime createdAt,
                            LocalDateTime modifiedAt,
                            LocalDateTime startDateOnWeek,
                            LocalDateTime endDateOnWeek,
                            Membership membership) {
        this.id = id;
        this.saleCount = saleCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.startDateOnWeek = startDateOnWeek;
        this.endDateOnWeek = endDateOnWeek;
        this.membership = membership;
    }

    public void upCount() {
        this.saleCount++;
    }

    public void downCount() {
        this.saleCount--;
    }
}
