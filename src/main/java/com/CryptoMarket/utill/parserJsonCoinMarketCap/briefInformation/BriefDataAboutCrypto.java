package com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class BriefDataAboutCrypto {
    private int id;
    private String name;
    private String symbol;
    @SerializedName("num_market_pairs")
    private int numMarketPairs;
    @SerializedName("circulating_supply")
    private int circulatingSupply;
    @SerializedName("total_supply")
    private int totalSupply;
    @SerializedName("max_supply")
    private int maxSupply;
    @SerializedName("last_updated")
    private Date lastUpdated;
    @SerializedName("date_added")
    private Date dateAdded;
    private QuoteForBriefInformation quote;



}
