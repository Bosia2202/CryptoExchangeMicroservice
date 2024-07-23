package com.cryptomarket.cryptoexchange.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "CryptoInfo")
@Data
public class CryptoBriefInfo {
    @Id
    private String symbol;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "current_price")
    private double currentPrice;
    @Column(name = "percent_change_1h")
    private float percentChange1h;
    @Column(name = "percent_change_24h")
    private float percentChange24h;
    @Column(name = "percent_change_7d")
    private float percentChange7d;
    @Column(name = "market_Cap")
    private double marketCap;
    @Column(name = "market_cap_dominance")
    private double marketCapDominance;
    @Column(name = "fully_diluted_market_cap")
    private double fullyDilutedMarketCap;
    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL)
    private List<Quote> quotes;

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    @Override
    public String toString() {
        return "CryptoBriefInfo [symbol=" + symbol + ", name=" + name + ", currentPrice=" + currentPrice
                + ", percentChange1h=" + percentChange1h + ", percentChange24h=" + percentChange24h
                + ", percentChange7d=" + percentChange7d + ", marketCap=" + marketCap + ", marketCapDominance="
                + marketCapDominance + ", fullyDilutedMarketCap=" + fullyDilutedMarketCap + "]";
    }

}
