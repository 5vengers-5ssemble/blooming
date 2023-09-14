package com.fivengers.blooming.payment.adapter.out.persistence;

import com.fivengers.blooming.payment.application.port.out.RecordPaymentPort;
import com.fivengers.blooming.payment.domain.Payment;
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
