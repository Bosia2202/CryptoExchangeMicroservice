package com.cryptomarket.cryptoexchange.interfeces;

import java.util.ArrayList;

import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.BriefDataAboutCrypto;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;

public interface ConvertInterface {
    ArrayList<CurrentInfoAboutCryptocurrency> getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(
            ArrayList<BriefDataAboutCrypto> briefDataAboutCryptoArrayList);
}
