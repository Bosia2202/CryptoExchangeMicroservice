package com.CryptoMarket.interfeces;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface APIExchangeInterface {
    CloseableHttpResponse getAllCryptoInfo();
    CloseableHttpResponse getSelectCryptoChart(String id);

}
