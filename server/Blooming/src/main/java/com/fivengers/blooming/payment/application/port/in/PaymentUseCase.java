package com.fivengers.blooming.payment.application.port.in;

import com.fivengers.blooming.payment.application.port.in.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentModifyRequest;
import com.fivengers.blooming.payment.application.port.in.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.domain.Payment;

public interface PaymentUseCase {

    Payment save(TempPaymentCreateRequest request, Long memberId);

    Boolean compareToTempPayment(PaymentCompareToTempRequest request);

    void modifyPaymentDone(PaymentModifyRequest paymentModifyRequest);

}
