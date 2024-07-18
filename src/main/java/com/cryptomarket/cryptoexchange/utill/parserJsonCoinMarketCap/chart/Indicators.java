package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.chart;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Indicators {
    @SerializedName("USD")
    private USDForChart usd;
}
