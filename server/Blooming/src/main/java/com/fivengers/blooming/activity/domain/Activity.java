package com.fivengers.blooming.activity.domain;

import com.fivengers.blooming.global.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Activity extends BaseTime {

  private Long id;
  private String name;

  public Activity(Long id, String name){
    this.id = id;
    this.name = name;
  }

}
