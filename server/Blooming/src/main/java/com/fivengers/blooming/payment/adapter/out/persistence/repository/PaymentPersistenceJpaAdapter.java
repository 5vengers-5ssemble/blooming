package com.fivengers.blooming.payment.adapter.out.persistence.repository;

import com.fivengers.blooming.payment.adapter.out.persistence.entity.PaymentJpaEntity;
import com.fivengers.blooming.payment.adapter.out.persistence.mapper.PaymentMapper;
import com.fivengers.blooming.payment.application.port.out.PaymentPort;
import com.fivengers.blooming.payment.domain.Payment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentPersistenceJpaAdapter implements PaymentPort {

    private final PaymentMapper paymentMapper;
    private final PaymentDataJpaRepository paymentDataJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentMapper.toDomain(
                paymentDataJpaRepository.save(paymentMapper.toEntity(payment)));
    }

    @Override
    public Payment findByOrderId(String orderId) {
        return paymentMapper.toDomain(paymentDataJpaRepository.findByOrderId(orderId));
    }

    @Override
    public void update(Payment payment) {
        PaymentJpaEntity paymentJpaEntity = paymentDataJpaRepository.findById(payment.getId())
                .orElseThrow(EntityNotFoundException::new);
        paymentJpaEntity.update(payment.getDone());
    }

}
