package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CryptocurrenciesData {
    private ArrayList<CurrentInfoAboutCryptocurrency> currentInfoAboutCryptocurrencies;

    public void addElement(CurrentInfoAboutCryptocurrency currentInfoAboutCryptocurrency) {
        currentInfoAboutCryptocurrencies.add(currentInfoAboutCryptocurrency);
    }
}
