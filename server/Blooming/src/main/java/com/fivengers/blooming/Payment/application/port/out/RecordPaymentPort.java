package com.fivengers.blooming.Payment.application.port.out;

import com.fivengers.blooming.Payment.domain.Payment;

public interface RecordPaymentPort {

    Payment save(Payment payment);

    Payment findByOrderId(String orderId);

}
