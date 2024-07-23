package com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView;
import lombok.Data;

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
    private double marketCap;
    private double marketCapDominance;
    private double fullyDilutedMarketCap;
}
