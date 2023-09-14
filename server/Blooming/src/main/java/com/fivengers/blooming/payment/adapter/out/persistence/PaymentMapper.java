package com.fivengers.blooming.payment.adapter.out.persistence;

import com.fivengers.blooming.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    Payment toDomain(PaymentJpaEntity paymentJpaEntity) {
        return Payment.builder()
                .paymentKey(paymentJpaEntity.getPaymentKey())
                .orderId(paymentJpaEntity.getOrderId())
                .amount(paymentJpaEntity.getAmount()).
                build();
    }

    PaymentJpaEntity toEntity(Payment payment) {
        return PaymentJpaEntity.builder()
                .paymentKey(payment.getPaymentKey())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .build();
    }

}
