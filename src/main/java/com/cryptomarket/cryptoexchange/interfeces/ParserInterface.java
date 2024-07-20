package com.cryptomarket.cryptoexchange.interfeces;

import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;

public interface ParserInterface {

    DefaultInfoOfAllCryptocurrencies parsingOfAllCryptocurrenciesFromApi (String response);

}
