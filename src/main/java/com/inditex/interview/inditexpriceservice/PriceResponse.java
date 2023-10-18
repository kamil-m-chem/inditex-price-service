package com.inditex.interview.inditexpriceservice;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceResponse(
    long productId,
    long brandId,
    long tariff,
    LocalDateTime startDate,
    LocalDateTime endDate,
    double finalPrice) {}
