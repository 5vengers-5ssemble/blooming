package com.fivengers.blooming.payment.adapter.out.persistence.mapper;

import com.fivengers.blooming.payment.adapter.out.persistence.entity.PaymentJpaEntity;
import com.fivengers.blooming.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    public Payment toDomain(PaymentJpaEntity paymentJpaEntity) {
        return Payment.builder()
                .id(paymentJpaEntity.getId())
                .memberId(paymentJpaEntity.getMemberId())
                .projectId(paymentJpaEntity.getProjectId())
                .paymentKey(paymentJpaEntity.getPaymentKey())
                .projectType(paymentJpaEntity.getProjectType())
                .orderId(paymentJpaEntity.getOrderId())
                .amount(paymentJpaEntity.getAmount())
                .done(paymentJpaEntity.getDone())
                .build();
    }

    public PaymentJpaEntity toEntity(Payment payment) {
        return PaymentJpaEntity.builder()
                .paymentKey(payment.getPaymentKey())
                .memberId(payment.getMemberId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .projectId(payment.getProjectId())
                .projectType(payment.getProjectType())
                .done(payment.getDone())
                .build();
    }

}
