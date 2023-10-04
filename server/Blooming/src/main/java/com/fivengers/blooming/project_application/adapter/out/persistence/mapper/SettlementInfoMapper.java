package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;


import com.fivengers.blooming.project_application.adapter.out.persistence.entity.SettlementInfoInJpaEntity;
import com.fivengers.blooming.project_application.domain.SettlementInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettlementInfoMapper {

    public SettlementInfo toDomain(SettlementInfoInJpaEntity settlementInfo) {
        return SettlementInfo.builder()
                .representative(settlementInfo.getRepresentative())
                .email(settlementInfo.getEmail())
                .accountNumber(settlementInfo.getAccountNumber())
                .bankbookImage(settlementInfo.getBankbookImage())
                .build();
    }

    public SettlementInfoInJpaEntity toInJpaEntity(SettlementInfo settlementInfo) {
        return SettlementInfoInJpaEntity.builder()
                .representative(settlementInfo.getRepresentative())
                .email(settlementInfo.getEmail())
                .accountNumber(settlementInfo.getAccountNumber())
                .bankbookImage(settlementInfo.getBankbookImage())
                .build();
    }
}
