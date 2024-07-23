package com.cryptomarket.cryptoexchange.services.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoBriefInfoKafkaDto implements Serializable {
    private String symbol;
    private String name;
    private double currentPrice;
    private float percentChange1h;
    private float percentChange24h;
    private float percentChange7d;
    private double circulatingSupply;
    private double totalSupply;
    private double maxSupply;
}
