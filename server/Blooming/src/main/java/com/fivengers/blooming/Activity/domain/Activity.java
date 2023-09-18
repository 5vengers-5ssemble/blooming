package com.fivengers.blooming.Activity.domain;

import com.fivengers.blooming.global.audit.BaseTime;
import lombok.Getter;

@Getter
public class Activity extends BaseTime {

  private Long id;
  private String name;

  public Activity(Long id, String name){
    this.id = id;
    this.name = name;
  }

}
