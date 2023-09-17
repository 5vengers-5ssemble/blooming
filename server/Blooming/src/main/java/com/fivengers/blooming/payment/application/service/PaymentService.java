package com.fivengers.blooming.payment.application.service;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentModifyOnSuccessRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {
    @Override
    public Void save(TempPaymentCreateRequest tempPaymentCreateRequest) {
        return null;
    }

    @Override
    public Void modifyOnSuccess(PaymentModifyOnSuccessRequest paymentModifyOnSuccessRequest) {
        return null;
    }
}
