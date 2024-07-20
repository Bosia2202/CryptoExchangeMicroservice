package com.cryptomarket.cryptoexchange.interfeces;
import java.util.Optional;

import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;



public interface CryptoExchange {
    Optional<CryptocurrenciesData> getExchangeInfo();

}
