package com.fivengers.blooming.payment.application.port.out;

import com.fivengers.blooming.payment.domain.Payment;

public interface PaymentPort {

    Payment save(Payment payment);

    Payment findByOrderId(String orderId);

    void update(Payment payment);

}
