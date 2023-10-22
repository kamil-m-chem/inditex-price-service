package com.inditex.interview.inditexpriceservice.services;

import com.inditex.interview.inditexpriceservice.PriceEntryToResponseMapper;
import com.inditex.interview.inditexpriceservice.PriceResponse;
import com.inditex.interview.inditexpriceservice.entities.PriceEntry;
import com.inditex.interview.inditexpriceservice.exceptions.PriceEntryNotFoundException;
import com.inditex.interview.inditexpriceservice.repositories.PriceRepository;
import java.time.LocalDateTime;
import java.util.List;
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
