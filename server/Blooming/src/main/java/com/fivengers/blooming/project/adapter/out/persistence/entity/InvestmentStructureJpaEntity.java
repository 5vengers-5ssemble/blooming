package com.fivengers.blooming.project.adapter.out.persistence.entity;


import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 투자 구조 테이블
 * 사용하지 않음!!
 */
@Entity
@Table(name = "investment_structure")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestmentStructureJpaEntity extends BaseTime {

    @Id
    @Column(name = "project_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    private Long totalAmount;
    private Long customerTransaction;
    private Integer dividendRatio;

    @Builder
    public InvestmentStructureJpaEntity(
            Long id,
            ProjectJpaEntity project,
            Long totalAmount,
            Long customerTransaction,
            Integer dividendRatio) {
        this.id = id;
        this.project = project;
        this.totalAmount = totalAmount;
        this.customerTransaction = customerTransaction;
        this.dividendRatio = dividendRatio;
    }
}
