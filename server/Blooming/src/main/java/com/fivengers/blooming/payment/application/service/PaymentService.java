package com.fivengers.blooming.payment.application.service;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCreateRequest;
import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {
    @Override
    public Payment save(PaymentCreateRequest paymentCreateRequest) {
        return new Payment();
    }
}
