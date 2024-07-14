package com.CryptoMarket.utill.parserJsonCoinMarketCap.finalView;
import com.CryptoMarket.utill.parserJsonCoinMarketCap.chart.QuoteForChart;
import lombok.Data;


import java.util.ArrayList;
@Data
public class CurrentInfoAboutCryptocurrency {
    private int id;
    private String name;
    private String symbol;
    private double price;
    private float percentChange1h;
    private float percentChange24h;
    private float percentChange7d;
    private double circulatingSupply;
    private double totalSupply;
    private double maxSupply;
}
