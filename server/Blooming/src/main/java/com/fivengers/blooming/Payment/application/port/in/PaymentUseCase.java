package com.fivengers.blooming.Payment.application.port.in;

import com.fivengers.blooming.Payment.adapter.in.web.dto.PaymentModifyRequest;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.Payment.domain.Payment;

public interface PaymentUseCase {

    Payment save(TempPaymentCreateRequest request);

    Payment sendRequest(PaymentModifyRequest request);

}
