package com.cryptomarket.cryptoexchange.controllers;

import com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate;
import com.cryptomarket.cryptoexchange.models.quote.QuoteByDay;
import com.cryptomarket.cryptoexchange.services.database.CryptoExchangeDataBaseService;
import com.cryptomarket.cryptoexchange.dto.coins.CryptoBriefInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private CryptoExchangeDataBaseService cryptoExchangeDataBaseService;

    @Autowired
    public ExchangeController(CryptoExchangeDataBaseService cryptoExchangeDataBaseService) {
        this.cryptoExchangeDataBaseService = cryptoExchangeDataBaseService;
    }

    @GetMapping("/quote/all")
    public List<CryptoBriefInfoDto> showActualBriefInformationAboutCryptocurrencies() {
        return cryptoExchangeDataBaseService.getActualBriefInfoAboutCryptocurrencies();
    }

    @GetMapping("/quote")
    public List<QuoteByDay> showQuoteInfoAboutCryptocurrencyForDay(@RequestParam String symbol){
        return cryptoExchangeDataBaseService.getQuoteInfoAboutCryptocurrencyForDay(symbol);
    }

    @GetMapping("/quote")
    public List<QuoteAggregate> showQuoteInfoAboutCryptocurrencyFromDate(@RequestParam String symbol, @RequestBody String localDateTime){
        return cryptoExchangeDataBaseService.getQuoteInfoAboutCryptocurrencyFromDate(symbol,localDateTime);
    }

    @GetMapping("/quote")
    public List<QuoteAggregate> showAllQuoteInfoAboutCryptocurrency(@RequestParam String symbol){
        return cryptoExchangeDataBaseService.getAllQuoteInfoAboutCryptocurrency(symbol);
    }

    @GetMapping("/quote")
    public List<QuoteAggregate> showQuotesBetweenDatesAboutCryptocurrency(@RequestParam String symbol, @RequestBody String startDate, @RequestBody String endDate){
        return cryptoExchangeDataBaseService.getQuotesBetweenDatesAboutCryptocurrency(symbol,startDate,endDate);
    }

}
