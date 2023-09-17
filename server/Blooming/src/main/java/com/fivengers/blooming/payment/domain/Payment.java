package com.fivengers.blooming.payment.domain;

import com.fivengers.blooming.Artist.domain.Artist;
import com.fivengers.blooming.Member.domain.Member;
import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Payment extends BaseTime {

  private Long id;
  private Member member;
  private Artist artist;
  private PaymentType paymentType;
  private Long projectId;
  private String paymentKey;
  private String orderId;
  private Long amount;

  @Builder
  public Payment(Long id, Member member, Artist artist, PaymentType paymentType, Long projectId,
      String paymentKey, String orderId, Long amount) {
    this.id = id;
    this.member = member;
    this.artist = artist;
    this.paymentType = paymentType;
    this.projectId = projectId;
    this.paymentKey = paymentKey;
    this.orderId = orderId;
    this.amount = amount;
  }
}
