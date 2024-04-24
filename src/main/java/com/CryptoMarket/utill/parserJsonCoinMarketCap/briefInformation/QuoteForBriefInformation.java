package com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class QuoteForBriefInformation {
    @SerializedName("USD")
    private USDForBriefInformation usd;
}
