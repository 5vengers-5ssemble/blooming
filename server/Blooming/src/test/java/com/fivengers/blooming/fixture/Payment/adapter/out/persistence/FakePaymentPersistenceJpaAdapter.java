package com.fivengers.blooming.fixture.Payment.adapter.out.persistence;

import com.fivengers.blooming.payment.application.port.out.PaymentPort;
import com.fivengers.blooming.payment.domain.Payment;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakePaymentPersistenceJpaAdapter implements PaymentPort {

    private final Map<Long, Payment> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
    public Payment save(Payment payment) {
        if (isPersistenceObject(payment)) {
            store.put(payment.getId(), payment);
            return payment;
        }
        return persist(payment);
    }

    private boolean isPersistenceObject(Payment payment) {
        return payment.getId() != null;
    }

    private Payment persist(Payment payment) {
        LocalDateTime now = LocalDateTime.now();
        Payment persistedPayment = Payment.builder()
                .id(autoIncrementId)
                .paymentKey(payment.getPaymentKey())
                .artist(payment.getArtist())
                .member(payment.getMember())
                .projectId(payment.getProjectId())
                .projectType(payment.getProjectType())
                .amount(payment.getAmount())
                .orderId(payment.getOrderId())
                .build();
        store.put(autoIncrementId, persistedPayment);
        autoIncrementId++;
        return persistedPayment;
    }
}