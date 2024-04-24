package com.CryptoMarket.interfeces;

import com.CryptoMarket.utill.parserJsonCoinMarketCap.CurrentInfoAboutCryptocurrency;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation.BriefDataAboutCrypto;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.chart.ChartData;

import java.util.ArrayList;
public interface ConvertInterface {
    CurrentInfoAboutCryptocurrency concatenation(CurrentInfoAboutCryptocurrency currentInfoAboutCryptocurrency, ChartData chartDataArrayList);
    ArrayList<CurrentInfoAboutCryptocurrency> getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(ArrayList<BriefDataAboutCrypto> briefDataAboutCryptoArrayList);
}
