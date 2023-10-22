package com.inditex.interview.inditexpriceservice;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PriceResponse(
    long productId,
    long brandId,
    long tariff,
    LocalDateTime startDate,
    LocalDateTime endDate,
    double finalPrice) {}
