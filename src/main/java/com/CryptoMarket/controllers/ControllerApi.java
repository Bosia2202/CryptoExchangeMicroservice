package com.CryptoMarket.controllers;

import com.CryptoMarket.apiExchange.CoinMarketCap;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class ControllerApi {
    private CoinMarketCap coinMarketCap;
    @Autowired
    public ControllerApi(CoinMarketCap coinMarketCap) {
        this.coinMarketCap = coinMarketCap;
    }
    @GetMapping("/key")
    public void key() {
       //coinMarketCap.sss();
    }
}
