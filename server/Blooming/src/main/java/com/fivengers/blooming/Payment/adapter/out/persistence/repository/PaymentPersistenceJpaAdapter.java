package com.fivengers.blooming.Payment.adapter.out.persistence.repository;

import com.fivengers.blooming.Payment.adapter.out.persistence.mapper.PaymentMapper;
import com.fivengers.blooming.Payment.application.port.out.RecordPaymentPort;
import com.fivengers.blooming.Payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentPersistenceJpaAdapter implements RecordPaymentPort {

    private final PaymentMapper paymentMapper;
    private final PaymentDataJpaRepository paymentDataJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentMapper.toDomain(
                paymentDataJpaRepository.save(paymentMapper.toEntity(payment)));
    }
}
