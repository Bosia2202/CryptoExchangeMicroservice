package com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.Date;

@Data
public class USDForBriefInformation {
    @SerializedName("price")
    private double currentPrice;
    @SerializedName("volume_24h")
    private double volume24h;
    @SerializedName("volume_change_24h")
    private double volumeChange24h;
    @SerializedName("percent_change_1h")
    private double percentChange1h;
    @SerializedName("percent_change_24h")
    private double percentChange24h;
    @SerializedName("percent_change_7d")
    private double percentChange7d;
    @SerializedName("market_cap")
    private double marketCap;
    @SerializedName("market_cap_dominance")
    private double marketCapDominance;
    @SerializedName("fully_diluted_market_cap")
    private double fullyDilutedMarketCap;
    @SerializedName("last_updated")
    private Date lastUpdated;
}
