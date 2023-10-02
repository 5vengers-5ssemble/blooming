package com.fivengers.blooming.membership.application.port.in.dto;

import java.time.LocalDateTime;

public record MembershipModifyRequest(Long id,
                                      String title,
                                      String description,
                                      LocalDateTime seasonStart,
                                      LocalDateTime seasonEnd,
                                      LocalDateTime purchaseStart,
                                      LocalDateTime purchaseEnd,
                                      String thumbnailUrl) {

}
