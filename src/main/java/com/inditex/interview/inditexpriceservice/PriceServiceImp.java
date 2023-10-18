package com.inditex.interview.inditexpriceservice;

import com.inditex.interview.inditexpriceservice.entities.PriceEntry;
import java.time.LocalDateTime;
import java.util.List;

import com.inditex.interview.inditexpriceservice.exceptions.PriceEntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImp implements PriceService {
  private final PriceRepository priceRepository;
  private final PriceEntryToResponseMapper priceEntryToResponseMapper;

  @Autowired
  public PriceServiceImp(
      PriceRepository priceRepository, PriceEntryToResponseMapper priceEntryToResponseMapper) {
    this.priceRepository = priceRepository;
    this.priceEntryToResponseMapper = priceEntryToResponseMapper;
  }

  public PriceResponse queryPrice(LocalDateTime applicationDate, long productId, long brandId) {
    List<PriceEntry> matchingPricesOrderedByPriority =
        priceRepository
            .findByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                productId, brandId, applicationDate);

    if (matchingPricesOrderedByPriority.isEmpty()) {
      throw new PriceEntryNotFoundException("No matching price entry found.");
    }

    PriceEntry thePriceEntry = matchingPricesOrderedByPriority.get(0);

    return priceEntryToResponseMapper.mapToResponse(thePriceEntry);
  }
}
