package com.fivengers.blooming.artist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "artist")
@Getter
public class ArtistJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "artist_id", nullable = false)
  private Long id;

  @Column
  private String name;

  public ArtistJpaEntity(Long id, String name){
    this.id = id;
    this.name = name;
  }
}
