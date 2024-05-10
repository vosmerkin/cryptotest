package com.CryptoDealBot.getPrice.config;

import java.net.http.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration which set HttpClient.
 */


@Configuration
public class HttpRequestClient {
  public static final String BASE_URL = "https://fapi.binance.com/fapi/v1/premiumIndex";
  @Bean
  public HttpClient client() {

    HttpClient client = HttpClient.newHttpClient();



    return client;
  }
}
