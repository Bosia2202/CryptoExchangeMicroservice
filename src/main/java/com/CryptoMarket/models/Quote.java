package com.CryptoMarket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "quotes")
@Data
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
    @ManyToOne
    @JoinColumn(name = "crypto_id",nullable = false)
    private CryptoBriefInfo crypto;
}
