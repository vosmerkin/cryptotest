package com.CryptoDealBot.getPrice.service;

import com.CryptoDealBot.getPrice.config.HttpRequestClient;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceService {

  @Autowired
  public final HttpClient client;

  public static final String BASE_URL = "https://fapi.binance.com/fapi/v1/premiumIndex";



  public String getPrice(String symbolName) {

    URI uri;
    URIBuilder builder = null;
    try {
      builder = new URIBuilder(BASE_URL);
      builder.addParameter("symbol", symbolName);
      uri = builder.build();
    } catch (URISyntaxException e) {
      log.info("URISyntaxException exception");
      throw new RuntimeException(e);
    }

    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(uri)
        .build();

//    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = null;
    try {
      response = client.send(request, BodyHandlers.ofString());
    } catch (IOException e) {
      log.info("IOException exception");
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      log.info("InterruptedException exception");
      throw new RuntimeException(e);
    }

    if (response.statusCode() != HttpStatus.OK.value()) {
      log.info("Wrong symbol!!!");
      return "Wrong symbol!!!";
    }

    String responseBody = response.body();

    return responseBody;
  }
}
