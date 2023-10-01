package com.fivengers.blooming.project_application.application.port.in.dto;

import com.fivengers.blooming.project_application.domain.SettlementInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SettlementInfoRequest(@NotBlank String representative,
                                    @Email String email,
                                    @NotBlank String accountNumber,
                                    @NotBlank String bankbookImage) {

    public SettlementInfo toDomain() {
        return SettlementInfo.builder()
                .representative(representative)
                .email(email)
                .accountNumber(accountNumber)
                .bankbookImage(bankbookImage)
                .build();
    }
}
