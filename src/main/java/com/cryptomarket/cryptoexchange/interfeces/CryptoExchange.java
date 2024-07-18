package com.cryptomarket.cryptoexchange.interfeces;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;

import java.util.Optional;

public interface CryptoExchange {
    Optional<CryptocurrenciesData> getExchangeInfo();

}
