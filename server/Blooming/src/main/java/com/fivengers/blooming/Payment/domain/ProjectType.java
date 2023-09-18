package com.fivengers.blooming.Payment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ProjectType {
  CONCERT("concert"), ACTIVITY("activity");

  @Getter
  private final String value;

  ProjectType(String value){
    this.value = value;
  }

  @JsonCreator
  public static ProjectType from(String value){
    for(ProjectType projectType : ProjectType.values()){
      if(projectType.getValue().equals(value)){
        return projectType;
      }
    }
    return null;
  }

  @JsonValue
  public String getValue(){
    return value;
  }

}
