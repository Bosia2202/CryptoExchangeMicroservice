package com.cryptomarket.cryptoexchange.services.database;

import com.cryptomarket.cryptoexchange.kafka.JsonKafkaProducerService;
import com.cryptomarket.cryptoexchange.models.Quote;
import com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate;
import com.cryptomarket.cryptoexchange.models.quote.QuoteByDay;
import com.cryptomarket.cryptoexchange.dto.coins.CryptoBriefInfoDto;
import com.cryptomarket.cryptoexchange.interfeces.CryptoExchange;
import com.cryptomarket.cryptoexchange.models.CryptoBriefInfo;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.cryptomarket.cryptoexchange.repositories.CryptoBriefInfoRepository;
import com.cryptomarket.cryptoexchange.repositories.QuoteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@Slf4j
public class CryptoExchangeDataBaseService {
    private CryptoExchange cryptoExchange;
    private final CryptoBriefInfoRepository cryptoBriefInfoRepository;
    private final CryptoWriteService cryptoWriteService;

    private JsonKafkaProducerService jsonKafkaProducerService;

    @Autowired
    public CryptoExchangeDataBaseService(
            CryptoExchange cryptoExchange, CryptoBriefInfoRepository cryptoBriefInfoRepository, QuoteRepository quoteRepository,
            CryptoWriteService cryptoWriteService, JsonKafkaProducerService jsonKafkaProducerService) {
        this.cryptoExchange = cryptoExchange;
        this.cryptoBriefInfoRepository = cryptoBriefInfoRepository;
        this.cryptoWriteService = cryptoWriteService;
        this.jsonKafkaProducerService = jsonKafkaProducerService;
    }

    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.MINUTES)
    public void updateDataBase() {
        cryptoExchange.getExchangeInfo().ifPresent(data -> {
            List<CryptoBriefInfoKafkaDto> kafkaValues = new ArrayList<>(100);
            for (CurrentInfoAboutCryptocurrency currentInfoAboutCryptocurrency : data.getCurrentInfoAboutCryptocurrencies()) {
                CryptoBriefInfo tempCryptoBriefInfo = cryptoWriteService.createAndReturnCryptoBriefInfo(currentInfoAboutCryptocurrency);
                cryptoWriteService.createQuote(tempCryptoBriefInfo,currentInfoAboutCryptocurrency);
                kafkaValues.add(new CryptoBriefInfoKafkaDto(currentInfoAboutCryptocurrency));
            }
            jsonKafkaProducerService.sendCryptocurrenciesToKafka(kafkaValues);
        });
        log.info("DataBase update " + LocalDateTime.now().toString());
    }

    public List<QuoteByDay> getQuoteInfoAboutCryptocurrencyForDay(String symbol) {
        List<QuoteByDay> temp = cryptoBriefInfoRepository.findBySymbolWithQuotesForDay(symbol);
        if (temp.isEmpty()) {
            throw new NoSuchElementException("Quote info about cryptocurrency doesn't exist");
        }
        return temp;
    }

    public List<QuoteAggregate> getQuoteInfoAboutCryptocurrencyFromDate(String symbol, LocalDateTime date) {
        List<QuoteAggregate> temp = cryptoBriefInfoRepository.findBySymbolWithQuotesFromDate(symbol, date);
        if (temp.isEmpty()) {
            throw new NoSuchElementException("Quote info about cryptocurrency doesn't exist");
        }
        return temp;
    }

    public List<QuoteAggregate> getAllQuoteInfoAboutCryptocurrency(String symbol) {
        List<QuoteAggregate> temp = cryptoBriefInfoRepository.findBySymbolWithAllQuotes(symbol);
        if (temp.isEmpty()) {
            throw new NoSuchElementException("Quote info about cryptocurrency doesn't exist");
        }
        return temp;
    }

    public List<QuoteAggregate> getQuotesBetweenDatesAboutCryptocurrency(String symbol, LocalDateTime startDate, LocalDateTime endDate) {
        List<QuoteAggregate> temp = cryptoBriefInfoRepository.findBySymbolWithQuotesBetweenDates(symbol, startDate, endDate);
        if (temp.isEmpty()) {
            throw new NoSuchElementException("Quote info about cryptocurrency doesn't exist");
        }
        return temp;
    }

    public double getCurrentPriceAboutCryptocurrency(String symbol) {
        CryptoBriefInfo cryptocurrency = cryptoBriefInfoRepository.findBySymbol(symbol).orElseThrow(() -> new RuntimeException("Cryptocurrency doesn't exist"));
        return cryptocurrency.getCurrentPrice();
    }

    public List<CryptoBriefInfoDto> getActualBriefInfoAboutCryptocurrencies() {
        return cryptoBriefInfoRepository.findAll().stream().map(convertCryptoBriefInfoToCryptoBriefInfoDto).toList();
    }

    protected final Function<CryptoBriefInfo, CryptoBriefInfoDto> convertCryptoBriefInfoToCryptoBriefInfoDto = cryptoBriefInfo -> {
        CryptoBriefInfoDto cryptoBriefInfoDto = new CryptoBriefInfoDto();
        cryptoBriefInfoDto.setName(cryptoBriefInfo.getName());
        cryptoBriefInfoDto.setSymbol(cryptoBriefInfo.getSymbol());
        cryptoBriefInfoDto.setCurrentPrice(cryptoBriefInfo.getCurrentPrice());
        return cryptoBriefInfoDto;
    };
}
