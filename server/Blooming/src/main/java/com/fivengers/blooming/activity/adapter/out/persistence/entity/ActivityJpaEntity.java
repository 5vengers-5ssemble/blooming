package com.fivengers.blooming.activity.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "activity")
@Getter
public class ActivityJpaEntity extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "activity_id", nullable = false)
  private Long id;

  @Column
  private String name;

  public ActivityJpaEntity(Long id, String name){
    this.id = id;
    this.name = name;
  }

}
