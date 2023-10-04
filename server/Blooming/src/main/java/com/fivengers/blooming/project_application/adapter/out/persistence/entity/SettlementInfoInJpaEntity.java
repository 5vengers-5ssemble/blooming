package com.fivengers.blooming.project_application.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementInfoInJpaEntity {

    private String representative;
    private String email;
    private String accountNumber;
    private String bankbookImage;

    @Builder
    public SettlementInfoInJpaEntity(String representative, String email, String accountNumber,
            String bankbookImage) {
        this.representative = representative;
        this.email = email;
        this.accountNumber = accountNumber;
        this.bankbookImage = bankbookImage;
    }
}
