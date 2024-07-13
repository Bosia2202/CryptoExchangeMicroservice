package com.CryptoMarket.controllers;

import com.CryptoMarket.exchanges.CoinMarketCap;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class ControllerApi {
    private CoinMarketCap coinMarketCap;
    @Autowired
    public ControllerApi(CoinMarketCap coinMarketCap) {
        this.coinMarketCap = coinMarketCap;
    }

}
