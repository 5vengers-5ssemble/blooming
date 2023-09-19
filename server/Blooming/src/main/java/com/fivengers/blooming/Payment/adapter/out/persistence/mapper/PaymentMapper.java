package com.fivengers.blooming.Payment.adapter.out.persistence.mapper;

import com.fivengers.blooming.Artist.domain.ArtistJpaEntity;
import com.fivengers.blooming.Artist.domain.ArtistMapper;
import com.fivengers.blooming.Member.domain.MemberJpaEntity;
import com.fivengers.blooming.Member.domain.MemberMapper;
import com.fivengers.blooming.Payment.adapter.out.persistence.entity.PaymentJpaEntity;
import com.fivengers.blooming.Payment.domain.Payment;
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
                .member(memberMapper.toDomain(paymentJpaEntity.getMemberJpaEntity()))
                .artist(artistMapper.toDomain(paymentJpaEntity.getArtistJpaEntity()))
                .projectId(paymentJpaEntity.getProjectId())
                .paymentKey(paymentJpaEntity.getPaymentKey())
                .projectType(paymentJpaEntity.getProjectType())
                .orderId(paymentJpaEntity.getOrderId())
                .amount(paymentJpaEntity.getAmount())
                .build();
    }

    public PaymentJpaEntity toEntity(Payment payment) {
        return PaymentJpaEntity.builder()
                .paymentKey(payment.getPaymentKey())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .artistJpaEntity(new ArtistJpaEntity(payment.getArtist().getId(),
                        payment.getArtist().getName()))
                .memberJpaEntity(new MemberJpaEntity(payment.getMember().getId(),
                        payment.getMember().getName()))
                .projectId(payment.getProjectId())
                .projectType(payment.getProjectType())
                .build();
    }

}
