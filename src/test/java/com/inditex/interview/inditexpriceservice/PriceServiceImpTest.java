package com.inditex.interview.inditexpriceservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.interview.inditexpriceservice.entities.Brand;
import com.inditex.interview.inditexpriceservice.entities.PriceEntry;
import com.inditex.interview.inditexpriceservice.exceptions.PriceEntryNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceImpTest {

  @Mock private PriceRepository priceRepository;

  @Mock private PriceEntryToResponseMapper priceEntryToResponseMapper;

  @InjectMocks private PriceServiceImp priceService;

  @BeforeEach
  public void setup() {}

  @Test
  void queryPrice_ShouldReturnCorrectPriceResponse_WhenThereIsAMatch() {
    // Create test data
    var applicationDate = LocalDateTime.of(2022, 12, 12, 12, 12, 12, 12);
    var startDate = LocalDateTime.of(2022, 12, 11, 12, 12, 12, 12);
    var endDate = LocalDateTime.of(2022, 12, 13, 12, 12, 12, 12);
    long productId = 1L;
    long brandId = 2L;
    // Mock the behavior of the PriceRepository
    List<PriceEntry> matchingPrices = new ArrayList<>();
    PriceEntry expectedPriceEntry =
        PriceEntry.builder()
            .price(10)
            .id(1L)
            .priority(1)
            .productId(1)
            .startDate(startDate)
            .endDate(endDate)
            .currency(Currency.EUR)
            .price(10)
            .brand(Brand.builder().brandName("Zara").brandId(1).build())
            .build();
    matchingPrices.add(expectedPriceEntry);

    doReturn(matchingPrices)
        .when(priceRepository)
        .findByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
            anyLong(), anyLong(), any(LocalDateTime.class));

    PriceResponse expectedResponse =
        PriceResponse.builder()
            .productId(1)
            .brandId(2L)
            .tariff(1L)
            .startDate(startDate)
            .endDate(endDate)
            .finalPrice(10)
            .build();
    when(priceEntryToResponseMapper.mapToResponse(expectedPriceEntry)).thenReturn(expectedResponse);

    // Perform the test
    PriceResponse result = priceService.queryPrice(applicationDate, productId, brandId);

    // Assert the result
    assertEquals(expectedResponse, result);
    verify(priceRepository, times(1))
        .findByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
            anyLong(), anyLong(), any(LocalDateTime.class));
  }

  @Test
  void testQueryPriceNoMatch() {
    // Create test data
    LocalDateTime applicationDate = LocalDateTime.now();
    long productId = 1L;
    long brandId = 2L;

    doReturn(new ArrayList<>())
        .when(priceRepository)
        .findByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
            anyLong(), anyLong(), any(LocalDateTime.class));

    // Perform the test, which should throw a PriceEntryNotFoundException
    assertThrows(
        PriceEntryNotFoundException.class,
        () -> priceService.queryPrice(applicationDate, productId, brandId));
  }
}
