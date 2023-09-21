package com.fivengers.blooming.project.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "investment_goods")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestmentGoodsJpaEntity extends BaseTime {

    @Id
    @Column(name = "project_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id")
    @MapsId
    private ProjectJpaEntity project;

    private String name;
    private String category;
    private String artistName;
    private String agency;

    @Builder
    public InvestmentGoodsJpaEntity(
            Long id,
            ProjectJpaEntity project,
            String name,
            String category,
            String artistName,
            String agency) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.category = category;
        this.artistName = artistName;
        this.agency = agency;
    }
}
