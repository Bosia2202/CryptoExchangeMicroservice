package com.cryptomarket.cryptoexchange.services.database;

import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class CryptoBriefInfoKafkaDto implements Serializable{
    private final String symbol;
    private final String name;
    private final double currentPrice;
    private final float percentChange1h;
    private final float percentChange24h;
    private final float percentChange7d;
    private final double circulatingSupply;
    private final double totalSupply;
    private final double maxSupply;

    public CryptoBriefInfoKafkaDto(CurrentInfoAboutCryptocurrency cryptocurrencyParsingInfo) {
        this.symbol = cryptocurrencyParsingInfo.getSymbol();
        this.name = cryptocurrencyParsingInfo.getName();
        this.currentPrice = cryptocurrencyParsingInfo.getPrice();
        this.percentChange1h = cryptocurrencyParsingInfo.getPercentChange1h();
        this.percentChange24h = cryptocurrencyParsingInfo.getPercentChange24h();
        this.percentChange7d = cryptocurrencyParsingInfo.getPercentChange7d();
        this.circulatingSupply = cryptocurrencyParsingInfo.getCirculatingSupply();
        this.totalSupply = cryptocurrencyParsingInfo.getTotalSupply();
        this.maxSupply = cryptocurrencyParsingInfo.getMaxSupply();
    }

}
