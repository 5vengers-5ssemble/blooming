package com.fivengers.blooming.payment.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.fixture.payment.adapter.out.persistence.FakePaymentPersistenceJpaAdapter;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentModifyRequest;
import com.fivengers.blooming.payment.application.port.in.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.application.port.out.PaymentPort;
import com.fivengers.blooming.payment.application.service.PaymentService;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.project.domain.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

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
                .orderId("newOrder")
                .amount(100L)
                .build();
        Long memberId = 1L;
        Payment savedPayment = paymentService.save(request, memberId);
        assertThat(savedPayment.getOrderId()).isEqualTo(request.orderId());
    }

    @Test
    @DisplayName("임시 거래 정보와 비교합니다.")
    void compareTempPayment() {
        Payment payment = Payment.builder()
                .id(1L)
                .paymentKey(null)
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .done(false)
                .amount(500L)
                .orderId("newOrder")
                .build();

        paymentPersistenceJpaAdapter.save(payment);
        PaymentCompareToTempRequest request =
                new PaymentCompareToTempRequest("newOrder", 500L);
        Boolean sameAsTemp = paymentService.compareToTempPayment(request);
        assertThat(sameAsTemp).isEqualTo(true);
    }

    @Test
    @DisplayName("결제를 완료합니다.")
    void completePayment() {
        Payment payment = Payment.builder()
                .id(1L)
                .paymentKey("newPaymentKey")
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .done(false)
                .amount(500L)
                .orderId("newOrder")
                .build();

        paymentPersistenceJpaAdapter.save(payment);
        PaymentModifyRequest request =
                new PaymentModifyRequest("newOrder", "newPaymentKey");
        paymentService.modifyPaymentDone(request);

        assertThat(
                paymentPersistenceJpaAdapter.findByOrderId(request.orderId()).getDone()).isEqualTo(
                true);
    }


}