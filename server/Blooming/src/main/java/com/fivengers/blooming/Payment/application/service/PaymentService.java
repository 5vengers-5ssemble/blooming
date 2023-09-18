package com.fivengers.blooming.Payment.application.service;

import com.fivengers.blooming.Artist.domain.Artist;
import com.fivengers.blooming.Member.domain.Member;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateResponse;
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
    public TempPaymentCreateResponse save(TempPaymentCreateRequest tempPaymentCreateRequest) {
        Payment dtoToDomain = Payment.builder()
                .orderId(tempPaymentCreateRequest.orderId())
                .amount(tempPaymentCreateRequest.amount())
                .projectId(tempPaymentCreateRequest.projectId())
                .projectType(tempPaymentCreateRequest.projectType())
                .paymentKey(tempPaymentCreateRequest.paymentKey())
                .member(new Member(tempPaymentCreateRequest.memberId(), "게스트"))
                .artist(new Artist(tempPaymentCreateRequest.artistId(), "아티스트"))
                .build();

        Payment savedTempPayment = recordPaymentPort.save(dtoToDomain);

        return TempPaymentCreateResponse.builder()
                .memberId(savedTempPayment.getMember().getId())
                .paymentKey(savedTempPayment.getPaymentKey())
                .artistId(savedTempPayment.getArtist().getId())
                .amount(savedTempPayment.getAmount())
                .projectType(savedTempPayment.getProjectType())
                .projectId(savedTempPayment.getProjectId())
                .build();
    }

}
