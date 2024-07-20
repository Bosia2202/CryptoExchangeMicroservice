package com.cryptomarket.cryptoexchange.exchanges;

import com.cryptomarket.cryptoexchange.interfeces.ConvertInterface;
import com.cryptomarket.cryptoexchange.interfeces.CryptoExchange;
import com.cryptomarket.cryptoexchange.interfeces.ParserInterface;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
@Slf4j
@NoArgsConstructor
public class CoinMarketCap implements CryptoExchange {

    private ConvertInterface converter;
    private ParserInterface parser;

    @Qualifier("coinMarketCap")
    private HttpGet httpRequest;

    @Autowired
    public CoinMarketCap(ConvertInterface converter, ParserInterface parser) {
        this.converter = converter;
        this.parser = parser;
    }

    @Override
    public Optional<CryptocurrenciesData> getExchangeInfo() {
        try {
            CryptocurrenciesData data = getCryptoCurrenciesData(getBriefInfoAboutCryptocurrencies(HttpClients.createDefault()));
            return Optional.of(data);
        } catch (IOException ioException) {
            log.info("Failed to get information from CoinMarketCap");
            return Optional.empty();
        }
    }

    private CryptocurrenciesData getCryptoCurrenciesData(String stringJsonBriefInfoAboutCryptocurrencies) {
        CryptocurrenciesData returnValue = new CryptocurrenciesData();
        DefaultInfoOfAllCryptocurrencies defaultInfoOfAllCryptocurrencies = parser.parsingOfAllCryptocurrenciesFromApi(stringJsonBriefInfoAboutCryptocurrencies);
        ArrayList<CurrentInfoAboutCryptocurrency> cryptocurrencyArrayList = converter.getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(
                defaultInfoOfAllCryptocurrencies.getData());
        returnValue.setCurrentInfoAboutCryptocurrencies(cryptocurrencyArrayList);
        return returnValue;
    }

    private String getBriefInfoAboutCryptocurrencies(CloseableHttpClient client) throws IOException {
        CloseableHttpResponse response = client.execute(httpRequest);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);
        response.close();
        return json;
    }

}