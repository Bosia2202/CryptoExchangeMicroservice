package com.cryptomarket.cryptoexchange.utill.converter;

import com.cryptomarket.cryptoexchange.interfeces.ConvertInterface;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.BriefDataAboutCrypto;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ConvertToCryptoCurrenciesData implements ConvertInterface {

    @Override
    public ArrayList<CurrentInfoAboutCryptocurrency> getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(ArrayList<BriefDataAboutCrypto> briefDataAboutCryptoArrayList) {
        return briefDataAboutCryptoArrayList.stream().map(this::convertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency).collect(Collectors.toCollection(ArrayList::new));
    }

    private CurrentInfoAboutCryptocurrency convertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(BriefDataAboutCrypto briefDataAboutCrypto) {
        CurrentInfoAboutCryptocurrency temp = new CurrentInfoAboutCryptocurrency();
        temp.setId(briefDataAboutCrypto.getId());
        temp.setName(briefDataAboutCrypto.getName());
        temp.setSymbol(briefDataAboutCrypto.getSymbol());
        temp.setMaxSupply(briefDataAboutCrypto.getMaxSupply());
        temp.setTotalSupply(briefDataAboutCrypto.getTotalSupply());
        temp.setCirculatingSupply(briefDataAboutCrypto.getCirculatingSupply());
        temp.setPrice(briefDataAboutCrypto.getQuote().getUsd().getCurrentPrice());
        temp.setPercentChange1h((float) briefDataAboutCrypto.getQuote().getUsd().getPercentChange1h());
        temp.setPercentChange24h((float) briefDataAboutCrypto.getQuote().getUsd().getPercentChange24h());
        temp.setPercentChange7d((float) briefDataAboutCrypto.getQuote().getUsd().getPercentChange7d());
        temp.setMarketCap(briefDataAboutCrypto.getQuote().getUsd().getMarketCap());
        temp.setFullyDilutedMarketCap(briefDataAboutCrypto.getQuote().getUsd().getFullyDilutedMarketCap());
        temp.setMarketCapDominance(briefDataAboutCrypto.getQuote().getUsd().getMarketCapDominance());
        return temp;
    }
}
