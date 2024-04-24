package com.CryptoMarket.utill.parserJsonCoinMarketCap.chart;

import lombok.Data;

import java.util.ArrayList;
@Data
public class ChartDataFromJson{
    private int id;
    private String name;
    private String symbol;
    private ArrayList<QuoteForChart> quotes;
}
