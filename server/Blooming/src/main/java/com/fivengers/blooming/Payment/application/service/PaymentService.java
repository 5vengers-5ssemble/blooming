package com.fivengers.blooming.payment.application.service;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentModifyRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.payment.application.port.out.PaymentPort;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.global.exception.payment.InvalidPaymentRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final PaymentPort paymentPort;

    @Override
    public Payment save(TempPaymentCreateRequest request) {
        return paymentPort.save(request.toDomain());
    }

    @Override
    public Boolean compareToTempPayment(PaymentCompareToTempRequest request) {
        Payment storedPayment = paymentPort.findByOrderId(request.orderId());
        Payment requestPayment = request.toDomain();

        if(!StoredPaymentEqualsRequest(storedPayment, requestPayment)) {
            throw new InvalidPaymentRequestException();
        }

        return true;
    }

    private boolean StoredPaymentEqualsRequest(Payment storedPayment, Payment requestPayment){
        return storedPayment.equals(requestPayment);
    }

    @Override
    public void modifyPaymentDone(PaymentModifyRequest paymentModifyRequest) {
        Payment payment = paymentPort.findByOrderId(paymentModifyRequest.orderId());
        payment.complete();
        paymentPort.update(payment);
    }
}
