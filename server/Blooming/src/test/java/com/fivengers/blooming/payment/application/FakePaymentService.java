package com.fivengers.blooming.payment.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.fixture.payment.adapter.out.persistence.FakePaymentPersistenceJpaAdapter;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.application.port.in.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.application.port.out.PaymentPort;
import com.fivengers.blooming.payment.application.service.PaymentService;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import com.mysema.commons.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FakePaymentService {

    PaymentPort paymentPort;
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
        assertThat(savedPayment.getOrderId()).isEqualTo(request.orderId());
    }

//    @Test
//    @DisplayName("임시 거래 정보와 비교합니다.")
//    void compareTempPayment() {
//        Payment payment = Payment.builder()
//            .id(1L)
//            .paymentKey(null)
//            .projectId(1L)
//            .projectType(ProjectType.CONCERT)
//            .done(false)
//            .amount(500L)
//            .orderId("newOrder")
//            .build();
//
//        paymentPersistenceJpaAdapter.save(payment);
//        PaymentCompareToTempRequest request =
//            new PaymentCompareToTempRequest("newOrder", 500L);
//        Boolean sameAsTemp = paymentService.compareToTempPayment(request);
//        assertThat(sameAsTemp).isEqualTo(true);
//    }

}