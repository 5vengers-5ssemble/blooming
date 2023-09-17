package com.fivengers.blooming.payment.adapter.in.web.dto;

public record PaymentModifyOnSuccessRequest(long memberId,
                                            String paymentKey,
                                            String orderId,
                                            int amount,
                                            String paymentType){

}
