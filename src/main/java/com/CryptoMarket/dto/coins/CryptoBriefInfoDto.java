package com.CryptoMarket.dto.coins;

import lombok.Data;

@Data
public class CryptoBriefInfoDto {
    private String symbol;
    private String name;
    private double currentPrice;
}
