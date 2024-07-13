package com.CryptoMarket.interfeces;

import com.CryptoMarket.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation.BriefDataAboutCrypto;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.chart.ChartData;

import java.util.ArrayList;
public interface ConvertInterface {
    ArrayList<CurrentInfoAboutCryptocurrency> getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(ArrayList<BriefDataAboutCrypto> briefDataAboutCryptoArrayList);
}
