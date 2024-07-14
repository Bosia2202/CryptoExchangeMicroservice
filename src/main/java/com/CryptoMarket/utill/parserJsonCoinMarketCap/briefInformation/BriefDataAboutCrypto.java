package com.CryptoMarket.utill.parserJsonCoinMarketCap.briefInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class BriefDataAboutCrypto {
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("num_market_pairs")
    private int numMarketPairs;
    @SerializedName("circulating_supply")
    private double circulatingSupply;
    @SerializedName("total_supply")
    private double totalSupply;
    @SerializedName("max_supply")
    private double maxSupply;
    @SerializedName("last_updated")
    private Date lastUpdated;
    @SerializedName("date_added")
    private Date dateAdded;
    private QuoteForBriefInformation quote;



}
