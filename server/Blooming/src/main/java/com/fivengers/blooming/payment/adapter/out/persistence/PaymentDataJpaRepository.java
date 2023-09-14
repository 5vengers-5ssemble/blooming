package com.fivengers.blooming.payment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {

}
