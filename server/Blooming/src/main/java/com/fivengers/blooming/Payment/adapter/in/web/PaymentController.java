package com.fivengers.blooming.Payment.adapter.in.web;

import com.fivengers.blooming.Payment.adapter.in.web.dto.PaymentModifyRequest;
import com.fivengers.blooming.Payment.adapter.in.web.dto.PaymentModifyResponse;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateResponse;
import com.fivengers.blooming.Payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @PostMapping("/temp")
    public ApiResponse<TempPaymentCreateResponse> createTempPaymentInfo(@RequestBody @Valid
    TempPaymentCreateRequest request) {
        return new ApiResponse<>(HttpStatus.OK.value(),
                TempPaymentCreateResponse.from(paymentUseCase.save(request)));
    }

    @PostMapping("/request")
    public ApiResponse<PaymentModifyResponse> modifyPaymentRequest(@RequestBody @Valid
    PaymentModifyRequest request) {
        return new ApiResponse<>(HttpStatus.OK.value(),
                PaymentModifyResponse.from(paymentUseCase.sendRequest(request)));
    }

}
