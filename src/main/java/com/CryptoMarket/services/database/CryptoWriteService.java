package com.CryptoMarket.services.database;

import com.CryptoMarket.models.CryptoBriefInfo;
import com.CryptoMarket.models.Quote;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CryptoMarket.repositories.CryptoBriefInfoRepository;
import com.CryptoMarket.repositories.QuoteRepository;

import java.time.LocalDateTime;

@Service
public class CryptoWriteService {
    private CryptoBriefInfoRepository cryptoBriefInfoRepository;
    private QuoteRepository quoteRepository;

    @Autowired
    public CryptoWriteService(CryptoBriefInfoRepository cryptoBriefInfoRepository, QuoteRepository quoteRepository) {
        this.cryptoBriefInfoRepository = cryptoBriefInfoRepository;
        this.quoteRepository = quoteRepository;
    }

    @Transactional
    public CryptoBriefInfo createAndReturnCryptoBriefInfo(CurrentInfoAboutCryptocurrency cryptocurrency) {
        CryptoBriefInfo cryptoBriefInfo = cryptoBriefInfoRepository.findBySymbol(cryptocurrency.getSymbol()).orElseGet(() -> {
            CryptoBriefInfo newCryptoBriefInfo = new CryptoBriefInfo();
            newCryptoBriefInfo.setName(cryptocurrency.getName());
            newCryptoBriefInfo.setSymbol(cryptocurrency.getSymbol());
            return newCryptoBriefInfo;
        });
        cryptoBriefInfo.setCurrentPrice(cryptocurrency.getPrice());
        cryptoBriefInfoRepository.save(cryptoBriefInfo);
        return cryptoBriefInfo;
    }

    @Transactional
    public void createQuote(CryptoBriefInfo cryptoBriefInfo, CurrentInfoAboutCryptocurrency cryptocurrency) {
        Quote quote = new Quote();
        quote.setCrypto(cryptoBriefInfo);
        quote.setDate(LocalDateTime.now());
        quote.setPrice(cryptoBriefInfo.getCurrentPrice());
        quote.setMaxSupply(cryptocurrency.getMaxSupply());
        quote.setCirculatingSupply(cryptocurrency.getCirculatingSupply());
        quote.setTotalSupply(cryptocurrency.getTotalSupply());
        quote.setPercentChange1h(cryptocurrency.getPercentChange1h());
        quote.setPercentChange24h(cryptocurrency.getPercentChange24h());
        quote.setPercentChange7d(cryptocurrency.getPercentChange7d());
        quoteRepository.save(quote);
    }
}
