package com.fivengers.blooming.payment.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.domain.ArtistJpaEntity;
import com.fivengers.blooming.artist.domain.ArtistMapper;
import com.fivengers.blooming.member.domain.MemberJpaEntity;
import com.fivengers.blooming.member.domain.MemberMapper;
import com.fivengers.blooming.payment.adapter.out.persistence.entity.PaymentJpaEntity;
import com.fivengers.blooming.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final MemberMapper memberMapper;
    private final ArtistMapper artistMapper;

    public Payment toDomain(PaymentJpaEntity paymentJpaEntity) {
        return Payment.builder()
                .id(paymentJpaEntity.getId())
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
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .projectId(payment.getProjectId())
                .projectType(payment.getProjectType())
                .done(payment.getDone())
                .build();
    }

}
