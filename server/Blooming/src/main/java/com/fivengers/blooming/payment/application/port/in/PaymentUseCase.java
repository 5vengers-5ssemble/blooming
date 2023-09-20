package com.fivengers.blooming.payment.application.port.in;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentModifyRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.domain.Payment;

public interface PaymentUseCase {

    Payment save(TempPaymentCreateRequest request);

    Boolean compareToTempPayment(PaymentCompareToTempRequest request);

    void modifyPaymentDone(PaymentModifyRequest paymentModifyRequest);

}
