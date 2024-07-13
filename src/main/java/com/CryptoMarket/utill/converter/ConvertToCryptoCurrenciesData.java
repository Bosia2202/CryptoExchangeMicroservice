package com.CryptoMarket.utill.converter;

import com.CryptoMarket.interfeces.ConvertInterface;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation.BriefDataAboutCrypto;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.chart.ChartData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ConvertToCryptoCurrenciesData implements ConvertInterface {

//    @Override
//    public CurrentInfoAboutCryptocurrency concatenation(CurrentInfoAboutCryptocurrency currentInfoAboutCryptocurrency, ChartData chartData) {
//        currentInfoAboutCryptocurrency.setChart(chartData.getData().getQuotes());
//        return currentInfoAboutCryptocurrency;
//    }

    @Override
    public ArrayList<CurrentInfoAboutCryptocurrency> getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(ArrayList<BriefDataAboutCrypto> briefDataAboutCryptoArrayList) {
        return briefDataAboutCryptoArrayList.stream().map(this::convertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency).collect(Collectors.toCollection(ArrayList::new));
    }

    private CurrentInfoAboutCryptocurrency convertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(BriefDataAboutCrypto briefDataAboutCrypto) {
        CurrentInfoAboutCryptocurrency temp = new CurrentInfoAboutCryptocurrency();
        temp.setId(briefDataAboutCrypto.getId());
        temp.setSymbol(briefDataAboutCrypto.getSymbol());
        temp.setMaxSupply(briefDataAboutCrypto.getMaxSupply());
        temp.setTotalSupply(briefDataAboutCrypto.getTotalSupply());
        temp.setCirculatingSupply(briefDataAboutCrypto.getCirculatingSupply());
        temp.setPrice(briefDataAboutCrypto.getQuote().getUsd().getCurrentPrice());
        temp.setPercentChange1h((float) briefDataAboutCrypto.getQuote().getUsd().getPercentChange1h());
        temp.setPercentChange24h((float) briefDataAboutCrypto.getQuote().getUsd().getPercentChange24h());
        temp.setPercentChange7d((float) briefDataAboutCrypto.getQuote().getUsd().getPercentChange7d());
        return temp;
    }
}
