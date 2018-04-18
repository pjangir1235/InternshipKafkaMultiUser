package com.risk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.risk.constants.CommonConstant;

public class Translations {
  @JsonProperty(CommonConstant.OTHERS)
  String other;

  public Translations() {
    super();
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }
}
