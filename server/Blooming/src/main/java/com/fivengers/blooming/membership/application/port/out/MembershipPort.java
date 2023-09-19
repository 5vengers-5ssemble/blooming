package com.fivengers.blooming.membership.application.port.out;

import com.fivengers.blooming.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MembershipPort {

    Page<Membership> findLatestSeasons(Pageable pageable);
}
