package com.fivengers.blooming.Payment.adapter.in.web;

import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateResponse;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.Payment.adapter.in.web.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.Payment.application.port.in.PaymentUseCase;
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

  @PostMapping("/temp")
  public ApiResponse<TempPaymentCreateResponse> createTempPaymentInfo(@RequestBody @Valid
  TempPaymentCreateRequest tempPaymentCreateRequest) {
    return new ApiResponse<>(HttpStatus.OK.value(), paymentUseCase.save(
        tempPaymentCreateRequest));
  }

}
