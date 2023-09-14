package com.fivengers.blooming.payment.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCreateRequest;
import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.payment.domain.Payment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @PostMapping("temp")
    public ApiResponse<Payment> createTempPaymentInfo(@RequestBody @Valid
    PaymentCreateRequest paymentCreateRequest) {

        return new ApiResponse<>(HttpStatus.OK.value(), paymentUseCase.save(paymentCreateRequest));

    }

}
