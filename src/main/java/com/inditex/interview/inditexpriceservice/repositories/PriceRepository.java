package com.inditex.interview.inditexpriceservice.repositories;

import com.inditex.interview.inditexpriceservice.entities.PriceEntry;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<PriceEntry, Long> {

  @Query(
      "SELECT e FROM PriceEntry e WHERE e.productId = :productId AND e.brand.brandId = :brandId AND e.startDate < :applicationDate AND e.endDate > :applicationDate ORDER BY e.priority DESC")
  List<PriceEntry>
      findByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
          @Param("productId") Long productId,
          @Param("brandId") Long brandId,
          @Param("applicationDate") LocalDateTime applicationDate);
}
