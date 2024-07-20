package com.cryptomarket.cryptoexchange.services.database;

import com.cryptomarket.cryptoexchange.models.CryptoBriefInfo;
import com.cryptomarket.cryptoexchange.models.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cryptomarket.cryptoexchange.repositories.CryptoBriefInfoRepository;
import com.cryptomarket.cryptoexchange.repositories.QuoteRepository;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;

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
        CryptoBriefInfo tempCryptoBriefInfo = cryptoBriefInfoRepository.findBySymbol(cryptocurrency.getSymbol()).orElseGet(() -> {
            CryptoBriefInfo newCryptoBriefInfo = new CryptoBriefInfo();
            newCryptoBriefInfo.setName(cryptocurrency.getName());
            newCryptoBriefInfo.setSymbol(cryptocurrency.getSymbol());
            return newCryptoBriefInfo;
        });
        tempCryptoBriefInfo.setCurrentPrice(cryptocurrency.getPrice());
        cryptoBriefInfoRepository.save(tempCryptoBriefInfo);
        return tempCryptoBriefInfo;
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
        quote.setMarketCap(cryptocurrency.getMarketCap());
        quote.setFullyDilutedMarketCap(cryptocurrency.getFullyDilutedMarketCap());
        quote.setMarketCapDominance(cryptocurrency.getMarketCapDominance());
        quoteRepository.save(quote);
    }
}

