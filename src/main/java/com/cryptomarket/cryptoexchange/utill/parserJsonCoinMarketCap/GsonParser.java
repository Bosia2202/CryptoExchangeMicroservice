package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap;

import com.cryptomarket.cryptoexchange.interfeces.ParserInterface;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;
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
        return gson.fromJson(response, DefaultInfoOfAllCryptocurrencies.class);
    }

}
