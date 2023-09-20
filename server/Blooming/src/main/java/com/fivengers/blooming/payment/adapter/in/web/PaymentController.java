package com.fivengers.blooming.payment.adapter.in.web;

import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCompareToTempResponse;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateResponse;
import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PostMapping("/check")
    public ApiResponse<PaymentCompareToTempResponse> compareToTempPayment(@RequestBody @Valid
    PaymentCompareToTempRequest request) {
        return new ApiResponse<>(HttpStatus.OK.value(),
                PaymentCompareToTempResponse.from(paymentUseCase.compareToTempPayment(request)));
    }

//    @PatchMapping("/complete")

}
