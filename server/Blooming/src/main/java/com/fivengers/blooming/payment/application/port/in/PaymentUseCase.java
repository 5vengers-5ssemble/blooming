package com.fivengers.blooming.payment.application.port.in;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentModifyOnSuccessRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateRequest;

public interface PaymentUseCase {

    Void save(TempPaymentCreateRequest request);

    Void modifyOnSuccess(PaymentModifyOnSuccessRequest paymentModifyOnSuccessRequest);
}
