package com.fivengers.blooming.payment.domain;

import com.fivengers.blooming.global.audit.BaseTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment extends BaseTime {

    private Long id;
    private ProjectType projectType;
    private Long projectId;
    private String paymentKey;
    private String orderId;
    private Long amount;
    private Boolean done;

    @Builder
    public Payment(Long id, ProjectType projectType, Long projectId,
            String paymentKey, String orderId, Long amount, Boolean done) {
        this.id = id;
        this.projectType = projectType;
        this.projectId = projectId;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return Objects.equals(this.orderId, payment.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

}
