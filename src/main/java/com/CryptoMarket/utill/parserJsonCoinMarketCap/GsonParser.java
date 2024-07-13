package com.CryptoMarket.utill.parserJsonCoinMarketCap;

import com.CryptoMarket.interfeces.ParserInterface;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.chart.ChartData;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
@Component
public class GsonParser implements ParserInterface {
    private final Gson gson;
    public GsonParser() {
       this.gson = new Gson();
    }
    @Override
    public DefaultInfoOfAllCryptocurrencies parsingOfAllCryptocurrenciesFromApi(String response) {
        return gson.fromJson(response,DefaultInfoOfAllCryptocurrencies.class);
    }
    @Override
    public ChartData parsingOfChartCryptocurrenciesFromApi(String response) {
        return gson.fromJson(response,ChartData.class);
    }
}
