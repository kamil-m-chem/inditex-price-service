package com.inditex.interview.inditexpriceservice.services;

import com.inditex.interview.inditexpriceservice.PriceResponse;
import java.time.LocalDateTime;

public interface PriceService {

  PriceResponse queryPrice(LocalDateTime applicationDate, long productId, long brandId);
}
