package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation;

import lombok.Data;
import java.util.ArrayList;

@Data
public class DefaultInfoOfAllCryptocurrencies {
    private ArrayList<BriefDataAboutCrypto> data;
}