package com.CryptoMarket.interfeces;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;

import java.util.Optional;

public interface CryptoExchange {
    Optional<CryptocurrenciesData> getExchangeInfo();

}
