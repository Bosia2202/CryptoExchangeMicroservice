package com.cryptomarket.cryptoexchange.models.quote;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuoteAggregate {
    private LocalDateTime date;
    private double averagePrice;
    private double minPrice;
    private double maxPrice;
}