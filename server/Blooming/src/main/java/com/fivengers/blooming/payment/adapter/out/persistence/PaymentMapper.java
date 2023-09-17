package com.fivengers.blooming.payment.adapter.out.persistence;

import com.fivengers.blooming.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

  Payment toDomain(PaymentJpaEntity paymentJpaEntity) {
    return Payment.builder()
        .id(paymentJpaEntity.getId())
        .member(paymentJpaEntity.getMemberJpaEntity().toDomain())
        .artist(paymentJpaEntity.getArtistJpaEntity().toDomain())
        .projectId(paymentJpaEntity.getProjectId())
        .paymentKey(paymentJpaEntity.getPaymentKey())
        .orderId(paymentJpaEntity.getOrderId())
        .amount(paymentJpaEntity.getAmount())
        .build();
  }

  PaymentJpaEntity toEntity(Payment payment) {
    return PaymentJpaEntity.builder()
        .paymentKey(payment.getPaymentKey())
        .orderId(payment.getOrderId())
        .amount(payment.getAmount())
        .build();
  }

}
