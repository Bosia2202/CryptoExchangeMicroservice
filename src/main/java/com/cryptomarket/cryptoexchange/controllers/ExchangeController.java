package com.cryptomarket.cryptoexchange.controllers;

import com.cryptomarket.cryptoexchange.services.database.CryptoExchangeDataBaseService;
import com.cryptomarket.cryptoexchange.dto.coins.CryptoBriefInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private CryptoExchangeDataBaseService cryptoExchangeDataBaseService;

    @Autowired
    public ExchangeController(CryptoExchangeDataBaseService cryptoExchangeDataBaseService) {
        this.cryptoExchangeDataBaseService = cryptoExchangeDataBaseService;
    }

    @GetMapping("/all")
    public List<CryptoBriefInfoDto> showActualBriefInformationAboutCryptocurrencies() {
        return cryptoExchangeDataBaseService.getActualBriefInfoAboutCryptocurrencies();
    }

}
