package com.cryptomarket.cryptoexchange.config;

import com.cryptomarket.cryptoexchange.exceptions.ApiRequestRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ExchangesConfig {
    @Value("${cryptoApi.key}")
    private String cryptoApiKey;
    @Value("${cryptoApi.limit}")
    private String limit;

    @Value("${cryptoApi.latestUrl}")
    private String latestUrl;

    @Bean
    public HttpGet coinMarketCap() {
          try {
              URIBuilder query = new URIBuilder(latestUrl);
              List<NameValuePair> parameters = new ArrayList<>();
              parameters.add(new BasicNameValuePair("limit", limit));
              query.addParameters(parameters);
              HttpGet request = new HttpGet(query.build());
              request.setHeader(HttpHeaders.ACCEPT, "application/json");
              request.addHeader("X-CMC_PRO_API_KEY", cryptoApiKey);
              log.info("Request to CoinMarketCap created");
              return request;
          }
          catch (URISyntaxException uriSyntaxException) {
            throw new ApiRequestRuntimeException("Failed to create URI");
          }
    }
}
