package com.CryptoMarket.services;

import com.CryptoMarket.interfeces.CryptoExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CryptoBriefInfoRepository;
import repositories.QuoteRepository;

@Service
public class CryptoExchangeForDataBaseService {
    private CryptoExchange cryptoExchange;
    private CryptoBriefInfoRepository cryptoBriefInfoRepository;
    private QuoteRepository quoteRepository;
    @Autowired
    public CryptoExchangeForDataBaseService(CryptoExchange cryptoExchange, CryptoBriefInfoRepository cryptoBriefInfoRepository, QuoteRepository quoteRepository) {
        this.cryptoExchange = cryptoExchange;
        this.cryptoBriefInfoRepository = cryptoBriefInfoRepository;
        this.quoteRepository = quoteRepository;
    }

    @Scheduled
    @Transactional
    public void updateQuote(){

    }
}
