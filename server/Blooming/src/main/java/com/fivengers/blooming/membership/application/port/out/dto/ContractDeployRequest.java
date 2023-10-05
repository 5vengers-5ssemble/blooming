package com.fivengers.blooming.membership.application.port.out.dto;

import com.fivengers.blooming.membership.domain.MembershipApplication;
import lombok.Builder;

@Builder
public record ContractDeployRequest(String privateKey,
                                    String title,
                                    String symbol,
                                    String baseUri,
                                    Integer saleCount,
                                    Long salePrice) {

    public static ContractDeployRequest from(MembershipApplication application) {

        return ContractDeployRequest.builder()
                .privateKey(application.getPrivateKey())
                .title(application.getTitle())
                .symbol(application.getTitle().toUpperCase())
                .baseUri(application.getBaseUri())
                .saleCount(application.getSaleCount())
                .salePrice(application.getSalePrice())
                .build();
    }
}
