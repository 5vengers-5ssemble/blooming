package com.fivengers.blooming.payment.application.port.in;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCreateRequest;
import com.fivengers.blooming.payment.domain.Payment;

public interface PaymentUseCase {

    Payment save(PaymentCreateRequest request);

}
