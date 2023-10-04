package com.fivengers.blooming.payment.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.payment.application.port.in.PaymentUseCase;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentCompareToTempRequest;
import com.fivengers.blooming.payment.application.port.in.dto.PaymentModifyRequest;
import com.fivengers.blooming.payment.application.port.in.dto.TempPaymentCreateRequest;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.project.domain.ProjectType;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest extends RestDocsTest {

    @MockBean
    PaymentUseCase paymentUseCase;

    @Test
    @DisplayName("임시 결제 정보를 저장한다.")
    void createTempPaymentInfo() throws Exception {
        TempPaymentCreateRequest request = TempPaymentCreateRequest.builder()
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .orderId("newOrder")
                .amount(500L)
                .build();
        Long memberId = 1L;
        LocalDateTime now = LocalDateTime.now();
        Payment payment = Payment.builder()
                .id(1L)
                .paymentKey(null)
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .done(false)
                .amount(500L)
                .orderId("newOrder")
                .build();
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", "newOrder");
        map.put("amount", 500L);

        given(paymentUseCase.save(any(TempPaymentCreateRequest.class), any(Long.class))).willReturn(payment);

        ResultActions perform = mockMvc.perform(post("/api/v1/payments/temp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.orderId").value(payment.getOrderId()));

        perform.andDo(print())
                .andDo(document("temp-payment-create",
                        getDocumentRequest(),
                        getDocumentResponse()));

    }

    @Test
    @DisplayName("임시 결제 정보와 비교한다.")
    void compareToTempPayment() throws Exception {
        PaymentCompareToTempRequest request =
                new PaymentCompareToTempRequest("newOrder", 500L);
        LocalDateTime now = LocalDateTime.now();
        Payment payment = Payment.builder()
                .id(1L)
                .paymentKey(null)
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .done(false)
                .amount(500L)
                .orderId("newOrder")
                .build();
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", "newOrder");
        map.put("amount", 500L);

        given(paymentUseCase.compareToTempPayment(any(PaymentCompareToTempRequest.class)))
                .willReturn(true);

        ResultActions perform = mockMvc.perform(post("/api/v1/payments/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.sameAsTemp").value(true));

        perform.andDo(print())
                .andDo(document("payment-compare",
                        getDocumentRequest(),
                        getDocumentResponse()));

    }

    @Test
    @DisplayName("결제를 완료처리한다.")
    void completePayment() throws Exception {
        PaymentModifyRequest request =
                new PaymentModifyRequest("newOrder", "newPaymentKey");
        LocalDateTime now = LocalDateTime.now();
        Payment payment = Payment.builder()
                .id(1L)
                .paymentKey(null)
                .projectId(1L)
                .projectType(ProjectType.CONCERT)
                .done(false)
                .amount(500L)
                .orderId("newOrder")
                .build();
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", "newOrder");
        map.put("amount", 500L);

        doNothing().when(paymentUseCase).modifyPaymentDone(any(PaymentModifyRequest.class));

        ResultActions perform = mockMvc.perform(patch("/api/v1/payments/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        verify(paymentUseCase, times(1))
                .modifyPaymentDone(any(PaymentModifyRequest.class));

        perform.andDo(print())
                .andDo(document("payment-complete",
                        getDocumentRequest(),
                        getDocumentResponse()));

    }


}
