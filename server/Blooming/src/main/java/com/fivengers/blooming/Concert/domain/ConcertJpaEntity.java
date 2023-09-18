package com.fivengers.blooming.Concert.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "concert")
@Getter
public class ConcertJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "concert_id", nullable = false)
  private Long id;

  @Column
  private String name;

  public ConcertJpaEntity(Long id, String name){
    this.id = id;
    this.name = name;
  }
}
