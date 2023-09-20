package com.fivengers.blooming.payment.application.service;

import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import com.fivengers.blooming.fixture.Payment.adapter.out.persistence.FakeActivityPersistenceJpaAdapter;
import com.fivengers.blooming.fixture.Payment.adapter.out.persistence.FakeArtistPersistenceJpaAdapter;
import com.fivengers.blooming.fixture.Payment.adapter.out.persistence.FakeConcertPersistenceJpaAdapter;
import com.fivengers.blooming.fixture.Payment.adapter.out.persistence.FakeMemberPersistenceJpaAdapter;
import com.fivengers.blooming.fixture.Payment.adapter.out.persistence.FakePaymentPersistenceJpaAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

    FakePaymentPersistenceJpaAdapter paymentPersistenceJpaAdapter;
    FakeActivityPersistenceJpaAdapter activityPersistenceJpaAdapter;
    FakeConcertPersistenceJpaAdapter concertPersistenceJpaAdapter;
    FakeMemberPersistenceJpaAdapter memberPersistenceJpaAdapter;
    FakeArtistPersistenceJpaAdapter artistPersistenceJpaAdapter;
    PaymentService paymentService;

    @BeforeEach
    void initObjects() {
        this.paymentPersistenceJpaAdapter = new FakePaymentPersistenceJpaAdapter();
        this.activityPersistenceJpaAdapter = new FakeActivityPersistenceJpaAdapter();
        this.artistPersistenceJpaAdapter = new FakeArtistPersistenceJpaAdapter();
        this.concertPersistenceJpaAdapter = new FakeConcertPersistenceJpaAdapter();
        this.memberPersistenceJpaAdapter = new FakeMemberPersistenceJpaAdapter();
        this.paymentService = new PaymentService(this.paymentPersistenceJpaAdapter);
    }

    @Test
    @DisplayName("임시 거래 정보를 저장합니다.")
    void saveTempPayment() {
        TempPaymentCreateRequest request = TempPaymentCreateRequest.builder()
                .memberId(1L)
                .artistId(1L)
                .projectType(ProjectType.CONCERT)
                .projectId(1L)
                .orderId("neworderid-123")
                .amount(100L)
                .build();
        Payment savedPayment = paymentService.save(request);
        Assertions.assertEquals(savedPayment.getOrderId(), request.orderId());
    }

}