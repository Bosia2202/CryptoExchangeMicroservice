package com.cryptomarket.cryptoexchange.dto.coins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoBriefInfoDto {
    private String symbol;
    private String name;
    private double currentPrice;
}
