package com.fivengers.blooming.Payment.application.service;

import com.fivengers.blooming.Artist.domain.Artist;
import com.fivengers.blooming.Member.domain.Member;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.Payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.Payment.application.port.out.RecordPaymentPort;
import com.fivengers.blooming.Payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final RecordPaymentPort recordPaymentPort;

    @Override
    public Payment save(TempPaymentCreateRequest request) {
        Member member = new Member(1L, "홍길동");
        Artist artist = new Artist(1L, "아이유");
        return recordPaymentPort.save(request.toDomain(member, artist));
    }

}
