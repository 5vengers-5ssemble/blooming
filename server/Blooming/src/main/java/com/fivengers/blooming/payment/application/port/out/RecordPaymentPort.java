package com.fivengers.blooming.payment.application.port.out;

import com.fivengers.blooming.payment.domain.Payment;

public interface RecordPaymentPort {

    Payment save(Payment payment);

}
