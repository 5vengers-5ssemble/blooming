package com.fivengers.blooming.payment.adapter.out.persistence;

import com.fivengers.blooming.Artist.domain.Artist;
import com.fivengers.blooming.Artist.domain.ArtistJpaEntity;
import com.fivengers.blooming.Member.domain.Member;
import com.fivengers.blooming.Member.domain.MemberJpaEntity;
import com.fivengers.blooming.payment.domain.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @ManyToOne
    private MemberJpaEntity memberJpaEntity;

    @ManyToOne
    private ArtistJpaEntity artistJpaEntity;

    @Column
    private PaymentType paymentType;

    @Column
    private long projectId;

    @Column(name = "payment_key", nullable = false)
    private String paymentKey;

    @Column(name = "order_id")
    private String orderId;

    @Column
    private Long amount;

}
