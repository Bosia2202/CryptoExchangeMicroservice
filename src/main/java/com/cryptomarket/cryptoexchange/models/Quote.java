package com.cryptomarket.cryptoexchange.models;

import jakarta.persistence.*;
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
    private double circulatingSupply;
    private double totalSupply;
    private double maxSupply;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoBriefInfo crypto;

    @Override
    public String toString() {
        return "Quote [uuid=" + uuid + ", price=" + price + ", circulatingSupply=" + circulatingSupply
                + ", totalSupply=" + totalSupply + ", maxSupply=" + maxSupply + ", date=" + date + "]";
    }

}
