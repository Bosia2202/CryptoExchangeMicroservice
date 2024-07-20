package com.cryptomarket.cryptoexchange.services.database;

import com.cryptomarket.cryptoexchange.kafka.JsonKafkaProducerService;
import com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate;
import com.cryptomarket.cryptoexchange.models.quote.QuoteByDay;
import com.cryptomarket.cryptoexchange.dto.coins.CryptoBriefInfoDto;
import com.cryptomarket.cryptoexchange.interfeces.CryptoExchange;
import com.cryptomarket.cryptoexchange.models.CryptoBriefInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.cryptomarket.cryptoexchange.repositories.CryptoBriefInfoRepository;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;

import java.time.LocalDateTime;
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
    private final JsonKafkaProducerService jsonKafkaProducerService;
    private static final int UPDATE_DELAY_MINUTES = 15;
    private static final String LOG_DATABASE_UPDATE_SUCCESS = "Database update successful at {}";
    private static final String LOG_DATABASE_UPDATE_FAILED = "Failed to update DataBase";

    public CryptoExchangeDataBaseService(CryptoExchange cryptoExchange,
            CryptoBriefInfoRepository cryptoBriefInfoRepository,
            CryptoWriteService cryptoWriteService, JsonKafkaProducerService jsonKafkaProducerService) {
        this.cryptoExchange = cryptoExchange;
        this.cryptoBriefInfoRepository = cryptoBriefInfoRepository;
        this.cryptoWriteService = cryptoWriteService;
        this.jsonKafkaProducerService = jsonKafkaProducerService;
    }

    @Scheduled(fixedDelay = UPDATE_DELAY_MINUTES, timeUnit = TimeUnit.MINUTES)
    public void updateDataBase() {
        try {
            cryptoExchange.getExchangeInfo().ifPresentOrElse(this::kafkaPushProcess,
                    () -> log.error(LOG_DATABASE_UPDATE_FAILED));
        } catch (Exception e) {
            log.error(LOG_DATABASE_UPDATE_FAILED);
        }
    }

    public List<QuoteByDay> getQuoteInfoAboutCryptocurrencyForDay(String symbol) {
        return cryptoBriefInfoRepository.findBySymbolWithQuotesForDay(symbol);
    }

    public List<QuoteAggregate> getQuoteInfoAboutCryptocurrencyFromDate(String symbol, String dateString) {
        LocalDateTime date = LocalDateTime.parse(dateString);
        return cryptoBriefInfoRepository.findBySymbolWithQuotesFromDate(symbol, date);
    }

    public List<QuoteAggregate> getAllQuoteInfoAboutCryptocurrency(String symbol) {
        return cryptoBriefInfoRepository.findBySymbolWithAllQuotes(symbol);
    }

    public List<QuoteAggregate> getQuotesBetweenDatesAboutCryptocurrency(String symbol, String startDateString,
            String endDateString) {
        LocalDateTime startDate = LocalDateTime.parse(startDateString);
        LocalDateTime endDate = LocalDateTime.parse(endDateString);
        return cryptoBriefInfoRepository.findBySymbolWithQuotesBetweenDates(symbol, startDate, endDate);

    }

    public List<CryptoBriefInfoDto> getActualBriefInfoAboutCryptocurrencies() {
        return cryptoBriefInfoRepository.findAll().stream().map(convertCryptoBriefInfoToCryptoBriefInfoDto).toList();
    }

    public CryptoBriefInfoDto getActualBriefInfoAboutCryptocurrency(String symbol) {
        CryptoBriefInfo cryptoBriefInfo = cryptoBriefInfoRepository.findBySymbol(symbol)
                .orElseThrow(() -> new NoSuchElementException("Actual brief info doesn't found"));
        return convertCryptoBriefInfoToCryptoBriefInfoDto.apply(cryptoBriefInfo);
    }

    private void kafkaPushProcess(CryptocurrenciesData data) {
        List<CryptoBriefInfoKafkaDto> kafkaValues = data.getCurrentInfoAboutCryptocurrencies()
                .stream()
                .map(info -> {
                    CryptoBriefInfo tempCryptoBriefInfo = cryptoWriteService.createAndReturnCryptoBriefInfo(info);
                    cryptoWriteService.createQuote(tempCryptoBriefInfo, info);
                    return new CryptoBriefInfoKafkaDto(info);
                }).toList();
        jsonKafkaProducerService.sendCryptocurrenciesToKafka(kafkaValues);
        log.info(LOG_DATABASE_UPDATE_SUCCESS, LocalDateTime.now());
    }

    private final Function<CryptoBriefInfo, CryptoBriefInfoDto> convertCryptoBriefInfoToCryptoBriefInfoDto = cryptoBriefInfo -> new CryptoBriefInfoDto(
            cryptoBriefInfo.getName(), cryptoBriefInfo.getSymbol(), cryptoBriefInfo.getCurrentPrice());
}
