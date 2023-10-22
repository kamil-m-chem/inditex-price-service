package com.inditex.interview.inditexpriceservice;

import com.inditex.interview.inditexpriceservice.entities.PriceEntry;
import org.springframework.stereotype.Component;

@Component
public class PriceEntryToResponseMapper {

  public PriceResponse mapToResponse(PriceEntry priceEntry) {
    if (priceEntry == null) {
      return null;
    }

    long brandId = priceEntry.getBrand() != null ? priceEntry.getBrand().getBrandId() : 0;

    return new PriceResponse(
        priceEntry.getProductId(),
        brandId,
        priceEntry.getId(),
        priceEntry.getStartDate(),
        priceEntry.getEndDate(),
        priceEntry.getPrice());
  }
}
