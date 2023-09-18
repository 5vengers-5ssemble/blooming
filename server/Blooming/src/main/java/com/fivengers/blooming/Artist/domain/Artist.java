package com.fivengers.blooming.Artist.domain;

import com.fivengers.blooming.global.audit.BaseTime;
import lombok.Getter;

@Getter
public class Artist extends BaseTime {

  private Long id;
  private String name;

  public Artist(Long id, String name){
    this.id = id;
    this.name = name;
  }

}
