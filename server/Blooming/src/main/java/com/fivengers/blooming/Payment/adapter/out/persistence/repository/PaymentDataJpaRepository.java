package com.fivengers.blooming.Payment.adapter.out.persistence.repository;

import com.fivengers.blooming.Payment.adapter.out.persistence.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {

}
