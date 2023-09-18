package com.fivengers.blooming.Payment.application.port.in;

import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateResponse;

public interface PaymentUseCase {

    TempPaymentCreateResponse save(TempPaymentCreateRequest request);

}
