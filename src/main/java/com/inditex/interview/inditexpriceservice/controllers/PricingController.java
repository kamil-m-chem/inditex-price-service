package com.inditex.interview.inditexpriceservice.controllers;

import com.inditex.interview.inditexpriceservice.PriceResponse;
import com.inditex.interview.inditexpriceservice.services.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "PricingController", description = "Operations related to pricing")
public class PricingController {
  private final PriceService priceService;

  @Autowired
  public PricingController(PriceService priceService) {
    this.priceService = priceService;
  }

  @GetMapping("/price")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Get price information by parameters",
      description = "This endpoint retrieves price information based on the provided parameters.")
  public PriceResponse queryPrice(
      @Parameter(
              description = "The application date in the format 'yyyy-MM-dd-HH.mm.ss'",
              required = true)
          @RequestParam
          @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
          LocalDateTime applicationDate,
      @Parameter(description = "The product ID", required = true) @RequestParam long productId,
      @Parameter(description = "The brand ID", required = true) @RequestParam long brandId) {
    return priceService.queryPrice(applicationDate, productId, brandId);
  }
}
