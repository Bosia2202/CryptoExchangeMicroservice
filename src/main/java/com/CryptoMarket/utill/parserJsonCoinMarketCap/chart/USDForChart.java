package com.CryptoMarket.utill.parserJsonCoinMarketCap.chart;

import lombok.Data;
import java.util.Date;

@Data
public class USDForChart {
    private double price;
    private double volume_24h;
    private double market_cap;
    private double circulating_supply;
    private double total_supply;
    private Date timestamp;
}
