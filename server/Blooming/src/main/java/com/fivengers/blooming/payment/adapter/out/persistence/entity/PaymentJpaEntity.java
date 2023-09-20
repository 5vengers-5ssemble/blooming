package com.fivengers.blooming.payment.adapter.out.persistence.entity;

import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Column
    private ProjectType projectType;

    @Column
    private Long projectId;

    @Column(nullable = true)
    private String paymentKey;

    @Column
    private String orderId;

    @Column
    private Long amount;

    @Column
    private Boolean done;

    @Builder
    public PaymentJpaEntity(Long id, ProjectType projectType, Long projectId,
            String paymentKey, String orderId, Long amount, Boolean done) {
        this.id = id;
        this.projectType = projectType;
        this.projectId = projectId;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.done = done;
    }

    public void update(Boolean done) {
        this.done = done;
    }
}
