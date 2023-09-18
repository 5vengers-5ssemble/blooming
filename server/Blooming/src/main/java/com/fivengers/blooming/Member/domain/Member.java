package com.fivengers.blooming.Member.domain;

import com.fivengers.blooming.global.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member extends BaseTime {

  private Long id;
  private String name;

  public Member(Long id, String name){
    this.id = id;
    this.name = name;
  }

}
