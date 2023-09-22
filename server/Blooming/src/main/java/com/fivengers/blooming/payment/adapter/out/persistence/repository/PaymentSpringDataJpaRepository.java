package com.fivengers.blooming.payment.adapter.out.persistence.repository;

import com.fivengers.blooming.payment.adapter.out.persistence.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSpringDataJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {

    PaymentJpaEntity findByOrderId(String orderId);


}
