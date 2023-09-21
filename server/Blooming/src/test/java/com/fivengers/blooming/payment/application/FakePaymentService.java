package com.fivengers.blooming.payment.application;

import com.fivengers.blooming.fixture.payment.adapter.out.persistence.FakePaymentPersistenceJpaAdapter;
import com.fivengers.blooming.payment.application.port.in.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.application.service.PaymentService;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FakePaymentService {

    FakePaymentPersistenceJpaAdapter paymentPersistenceJpaAdapter;
    PaymentService paymentService;

    @BeforeEach
    void initObjects() {
        this.paymentPersistenceJpaAdapter = new FakePaymentPersistenceJpaAdapter();
        this.paymentService = new PaymentService(this.paymentPersistenceJpaAdapter);
    }

    @Test
    @DisplayName("임시 거래 정보를 저장합니다.")
    void saveTempPayment() {
        TempPaymentCreateRequest request = TempPaymentCreateRequest.builder()
                .projectType(ProjectType.CONCERT)
                .projectId(1L)
                .orderId("neworderid-123")
                .amount(100L)
                .build();
        Payment savedPayment = paymentService.save(request);
        Assertions.assertEquals(savedPayment.getOrderId(), request.orderId());
    }

}