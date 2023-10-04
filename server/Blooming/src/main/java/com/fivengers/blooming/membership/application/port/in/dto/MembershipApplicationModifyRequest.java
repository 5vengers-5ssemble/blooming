package com.fivengers.blooming.membership.application.port.in.dto;

import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import jakarta.validation.constraints.NotNull;

public record MembershipApplicationModifyRequest(@NotNull
                                                 MembershipApplicationState applicationState) {

}
