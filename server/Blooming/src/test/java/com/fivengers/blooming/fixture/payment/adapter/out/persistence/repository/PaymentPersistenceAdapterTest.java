package com.fivengers.blooming.fixture.payment.adapter.out.persistence.repository;

import com.fivengers.blooming.payment.adapter.out.persistence.entity.PaymentJpaEntity;
import com.fivengers.blooming.payment.adapter.out.persistence.repository.PaymentPersistenceJpaAdapter;
import com.fivengers.blooming.payment.adapter.out.persistence.repository.PaymentSpringDataJpaRepository;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.project.domain.ProjectType;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentPersistenceAdapterTest {

    @Autowired
    PaymentSpringDataJpaRepository paymentSpringDataJpaRepository;
    @Autowired
    PaymentPersistenceJpaAdapter paymentPersistenceJpaAdapter;

    PaymentJpaEntity paymentJpaEntity;

    @BeforeEach
    void initObjects() {
        paymentJpaEntity = PaymentJpaEntity.builder()
                .id(1L)
                .paymentKey("newPaymentKey")
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .done(false)
                .amount(500L)
                .orderId("newOrder")
                .build();
        paymentSpringDataJpaRepository.save(paymentJpaEntity);
    }

    @Test
    @DisplayName("orderId로 Payment를 조회한다.")
    void findByOrderId() {
        Payment foundPayment = paymentPersistenceJpaAdapter.findByOrderId(
                paymentJpaEntity.getOrderId());

        assertThat(foundPayment.getOrderId()).isEqualTo(paymentJpaEntity.getOrderId());
    }


}
