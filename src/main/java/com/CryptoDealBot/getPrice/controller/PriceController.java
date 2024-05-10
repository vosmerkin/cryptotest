package com.CryptoDealBot.getPrice.controller;

import com.CryptoDealBot.getPrice.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/get_price")
public class PriceController {

  private final PriceService priceService;


  /**
   * Retrieves a request for viewing Mark Price and Funding Rate info.
   *
   * @param symbolName The name of the index.
   * @return {@link String} info from https://fapi.binance.com/fapi/v1/premiumIndex?symbol=
   */
  @GetMapping
  public ResponseEntity<String> getPrice(
      @RequestParam(value = "symbol", required = false) String symbolName) {
    log.info("Received request with 'symbol' parameter - {}" , symbolName);
    return ResponseEntity.status(HttpStatus.OK).body(priceService.getPrice(symbolName));
  }
}
