package com.fivengers.blooming.payment.adapter.in.web;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.PaymentCompareToTempResponse;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentModifyRequest;
import com.fivengers.blooming.payment.application.port.in.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.adapter.in.web.dto.TempPaymentCreateResponse;
import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    TempPaymentCreateRequest request, @AuthenticationPrincipal LoginUser loginUser) {
        return ApiResponse.ok(TempPaymentCreateResponse.from(
                paymentUseCase.save(request, loginUser.getMemberId())));
    }

    @PostMapping("/check")
    public ApiResponse<PaymentCompareToTempResponse> compareToTempPayment(@RequestBody @Valid
    PaymentCompareToTempRequest request) {
        return ApiResponse.ok(
                PaymentCompareToTempResponse.from(paymentUseCase.compareToTempPayment(request)));
    }

    @PatchMapping("/complete")
    public ApiResponse<Object> completePayment(@RequestBody @Valid
    PaymentModifyRequest request) {
        paymentUseCase.modifyPaymentDone(request);
        return ApiResponse.noContent().build();
    }
}
