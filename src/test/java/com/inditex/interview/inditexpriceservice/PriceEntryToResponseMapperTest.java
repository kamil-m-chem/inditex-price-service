package com.inditex.interview.inditexpriceservice;

import com.inditex.interview.inditexpriceservice.entities.Brand;
import com.inditex.interview.inditexpriceservice.entities.PriceEntry;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceEntryToResponseMapperTest {

  @Test
  void returnsPriceResponse_WhenGivenNonNullPriceEntry() {

    var startDate = LocalDateTime.of(2022, 12, 11, 12, 12, 12, 12);
    var endDate = LocalDateTime.of(2022, 12, 13, 12, 12, 12, 12);
    long productId = 1L;
    long brandId = 2L;
    var mapper = new PriceEntryToResponseMapper();
    var priceEntry =
        PriceEntry.builder()
            .price(10)
            .id(1L)
            .priority(1)
            .productId(productId)
            .startDate(startDate)
            .endDate(endDate)
            .currency(Currency.EUR)
            .price(10)
            .brand(Brand.builder().brandName("Zara").brandId(brandId).build())
            .build();

    PriceResponse result = mapper.mapToResponse(priceEntry);

    assertNotNull(result);
  }

  @Test
  void mapsFieldsCorrectly_WhenGivenNonNullPriceEntry() {
    // Arrange
    PriceEntryToResponseMapper mapper = new PriceEntryToResponseMapper();
    PriceEntry priceEntry = new PriceEntry();
    priceEntry.setProductId(123);
    Brand brand = new Brand();
    brand.setBrandId(456);
    priceEntry.setBrand(brand);
    priceEntry.setId(789L);
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusHours(1);
    priceEntry.setStartDate(startDate);
    priceEntry.setEndDate(endDate);
    priceEntry.setPrice(10.0);

    // Act
    PriceResponse result = mapper.mapToResponse(priceEntry);

    // Assert
    assertEquals(123, result.productId());
    assertEquals(456, result.brandId());
    assertEquals(789L, result.tariff());
    assertEquals(startDate, result.startDate());
    assertEquals(endDate, result.endDate());
    assertEquals(10.0, result.finalPrice(), 0.001);
  }

  @Test
  void returnsNullWhenGivenNullPriceEntry() {

    PriceEntryToResponseMapper mapper = new PriceEntryToResponseMapper();

    PriceResponse result = mapper.mapToResponse(null);

    assertNull(result);
  }
}
