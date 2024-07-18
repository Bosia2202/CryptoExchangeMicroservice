package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.chart;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class QuoteForChart {
    private Date timestamp;
    @SerializedName("quote")
    private Indicators indicators;
}
