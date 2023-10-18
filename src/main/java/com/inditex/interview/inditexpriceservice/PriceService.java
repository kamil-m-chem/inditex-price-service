package com.inditex.interview.inditexpriceservice;

import java.time.LocalDateTime;

public interface PriceService {

    PriceResponse queryPrice(LocalDateTime applicationDate, long productId, long brandId);
}
