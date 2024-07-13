package com.CryptoMarket.config;

import com.CryptoMarket.exchanges.CoinMarketCap;
import com.CryptoMarket.interfeces.CryptoExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CryptoExchange cryptoExchange() {
        return new CoinMarketCap();
    }
}
