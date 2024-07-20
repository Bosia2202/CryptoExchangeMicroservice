package com.cryptomarket.cryptoexchange.models;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue
    private UUID uuid;
    private double price;
    private float percentChange1h;
    private float percentChange24h;
    private float percentChange7d;
    private double circulatingSupply;
    private double totalSupply;
    private double maxSupply;
    private LocalDateTime date;
    private double marketCap;
    private double marketCapDominance;
    private double fullyDilutedMarketCap;
    @ManyToOne
    @JoinColumn(name = "crypto_id",nullable = false)
    private CryptoBriefInfo crypto;

}
