package com.cryptomarket.cryptoexchange.models.quote;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuoteByDay {
        private LocalDateTime date;
        private double price;
        private double circulatingSupply;
        private double totalSupply;
        private double maxSupply;
}
