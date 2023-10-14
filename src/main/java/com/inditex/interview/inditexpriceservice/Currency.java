package com.inditex.interview.inditexpriceservice;

import lombok.Getter;

@Getter
public enum Currency {
  USD("USD"),
  EUR("EUR"),
  GBP("GBP");

  private final String code;

  Currency(String code) {
    this.code = code;
  }
}
