package com.inditex.interview.inditexpriceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.interview.inditexpriceservice.exceptions.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class PricingControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  private static final long productId = 35455;
  private static final long brandId = 1;

  @ParameterizedTest
  @CsvSource({
    "2020-06-14-10.00.00,1", // when only one entry match
    "2020-06-14-16.00.00,2", // when priority need to be applied
    "2020-06-14-21.00.00,1", // when end data os before application date minor priority should be
    // applied
    "2020-06-15-10.00.00,3", // when priority need to be applied
    "2020-06-16-21.00.00,4" // highest priority
  })
  void getQueryPrice_ShouldReturnCorrectEntryPrice_WhenOnlyOneEntryMatch(
      String applicationDate, long expectedTariff) throws Exception {

    // Perform a GET request to query the price
    MvcResult result = performGetCall(applicationDate).andExpect(status().isOk()).andReturn();

    // response content to a PriceResponse object
    PriceResponse priceResponse = convertMvcResultToPriceResponse(result);

    assertEquals(expectedTariff, priceResponse.tariff());
  }

  @Test
  void getQueryPrice_shouldReturnProperMsg_WhenNoPriceEntryFound() throws Exception {
    // Define the request parameters
    String unreachableApplicationDate = "2222-12-22-22.22.22";

    // Perform a GET request to query the price
    MvcResult result = performGetCall(unreachableApplicationDate).andExpect(status().isNotFound()).andReturn();

    // Convert the response content to a PriceResponse object
    String content = result.getResponse().getContentAsString();
    ObjectMapper objectMapper = new ObjectMapper();
    ErrorResponse errorResponse = objectMapper.readValue(content, ErrorResponse.class);

    String expectedError = "Price entry not found";
    String expectedMessage = "No matching price entry found.";

    assertEquals(expectedError, errorResponse.error());
    assertEquals(expectedMessage, errorResponse.message());
  }

  private PriceResponse convertMvcResultToPriceResponse(MvcResult result)
      throws UnsupportedEncodingException, JsonProcessingException {
    String content = result.getResponse().getContentAsString();
    return objectMapper.readValue(content, PriceResponse.class);
  }

  private ResultActions performGetCall(String applicationDate) throws Exception {
    return mockMvc.perform(
        MockMvcRequestBuilders.get("/api/prices")
            .param("applicationDate", applicationDate)
            .param("productId", String.valueOf(productId))
            .param("brandId", String.valueOf(brandId))
            .contentType(MediaType.APPLICATION_JSON));
  }
}
