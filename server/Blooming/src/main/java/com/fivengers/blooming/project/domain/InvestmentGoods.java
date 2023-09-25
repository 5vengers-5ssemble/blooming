package com.fivengers.blooming.project.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InvestmentGoods {

    private Project project;

    private String name;
    private String category;
    private String artistName;
    private String agency;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public InvestmentGoods(
            Project project,
            String name,
            String category,
            String artistName,
            String agency,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.project = project;
        this.name = name;
        this.category = category;
        this.artistName = artistName;
        this.agency = agency;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
