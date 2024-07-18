package com.cryptomarket.cryptoexchange.interfeces;

import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.chart.ChartData;

public interface ParserInterface {

    DefaultInfoOfAllCryptocurrencies parsingOfAllCryptocurrenciesFromApi (String response);
    ChartData parsingOfChartCryptocurrenciesFromApi (String response);

}
