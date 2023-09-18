package com.fivengers.blooming.Concert.domain;

import com.fivengers.blooming.global.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Concert extends BaseTime {

  private Long id;
  private String name;

  public Concert(Long id, String name){
    this.id = id;
    this.name = name;
  }

}
