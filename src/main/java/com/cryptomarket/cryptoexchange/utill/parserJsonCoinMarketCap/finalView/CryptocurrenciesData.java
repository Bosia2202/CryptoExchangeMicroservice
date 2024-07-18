package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CryptocurrenciesData {
    private ArrayList<CurrentInfoAboutCryptocurrency> currentInfoAboutCryptocurrencies;

    public void addElement(CurrentInfoAboutCryptocurrency currentInfoAboutCryptocurrency) {
        currentInfoAboutCryptocurrencies.add(currentInfoAboutCryptocurrency);
    }
}
