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
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column (name = "current_price")
    private double currentPrice;
    @OneToMany(mappedBy = "crypto")
    private List<Quote> quotes;

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }
}
