package com.CryptoMarket.interfeces;

import com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.chart.ChartData;

public interface ParserInterface {

    DefaultInfoOfAllCryptocurrencies parsingOfAllCryptocurrenciesFromApi (String response);
    ChartData parsingOfChartCryptocurrenciesFromApi (String response);

}
