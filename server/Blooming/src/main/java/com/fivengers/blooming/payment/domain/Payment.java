package com.fivengers.blooming.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String paymentKey;
    private String orderId;
    private Long amount;

    public Payment(String orderId, Long amount){
        this.orderId = orderId;
        this.amount = amount;
    }

}
