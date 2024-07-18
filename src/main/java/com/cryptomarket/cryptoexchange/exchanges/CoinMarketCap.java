package com.cryptomarket.cryptoexchange.exchanges;

import com.cryptomarket.cryptoexchange.interfeces.ConvertInterface;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.briefInformation.DefaultInfoOfAllCryptocurrencies;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CryptocurrenciesData;
import com.cryptomarket.cryptoexchange.utill.parserJsonCoinMarketCap.finalView.CurrentInfoAboutCryptocurrency;
import com.cryptomarket.cryptoexchange.interfeces.CryptoExchange;
import com.cryptomarket.cryptoexchange.interfeces.ParserInterface;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Component
@Slf4j
@NoArgsConstructor
public class CoinMarketCap implements CryptoExchange {

    private CloseableHttpClient client;
    @Value("${cryptoApi.key}")
    private String cryptoApiKey;
    @Value("${cryptoApi.timeInterval}")
    private String timeInterval;
    @Value("${cryptoApi.amountOfPoints}")
    private String amountOfPoints;
    @Value("${cryptoApi.limit}")
    private String limit;
    @Value("${cryptoApi.latestUrl}")
    private String latestUrl;
    private ConvertInterface converter;
    private ParserInterface parser;

    @Autowired
    public CoinMarketCap(ConvertInterface converter, ParserInterface parser) {
        this.converter = converter;
        this.parser = parser;
    }

    @PostConstruct
    private void init(){
        this.client = HttpClients.createDefault();
        log.debug("Start CoinMarketCap");
    }

    @Override
    public Optional<CryptocurrenciesData> getExchangeInfo(){
        return getBriefInfoAboutCryptocurrencies(client).map(this::getCryptoCurrenciesData);
    }

    private CryptocurrenciesData getCryptoCurrenciesData(String stringJsonBriefInfoAboutCryptocurrencies) {
        CryptocurrenciesData returnValue = new CryptocurrenciesData();
        DefaultInfoOfAllCryptocurrencies defaultInfoOfAllCryptocurrencies = parser.parsingOfAllCryptocurrenciesFromApi(stringJsonBriefInfoAboutCryptocurrencies);
        ArrayList<CurrentInfoAboutCryptocurrency> cryptocurrencyArrayList = converter.getConvertDefaultInfoOfAllCryptocurrencyToCurrentInfoAboutCryptocurrency(
                defaultInfoOfAllCryptocurrencies.getData());
        returnValue.setCurrentInfoAboutCryptocurrencies(cryptocurrencyArrayList);
        return returnValue;
    }

    private Optional<String> getBriefInfoAboutCryptocurrencies(CloseableHttpClient client)  {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("limit",limit));
        Optional<HttpGet> requestOptional = buildGetRequest(latestUrl,parameters);
        if (requestOptional.isPresent()) {
            try {
                CloseableHttpResponse response = client.execute(requestOptional.get());
                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity);
                response.close();
                return Optional.of(json);
            } catch (IOException exception) {
                log.error("The GET request could not be sent.\nException: "+ exception.getMessage());
            }
        } else {
            log.error("The GET request could not be received");
        }
        return Optional.empty();
    }

    private Optional<HttpGet> buildGetRequest(String uri, List<NameValuePair> parameters) {
        try {
            URIBuilder query = new URIBuilder(uri);
            query.addParameters(parameters);
            HttpGet request = new HttpGet(query.build());
            request.setHeader(HttpHeaders.ACCEPT, "application/json");
            request.addHeader("X-CMC_PRO_API_KEY", cryptoApiKey);
            return Optional.of(request);
    }catch (URISyntaxException uriSyntaxException){
            log.error("buildGetRequest error.\n Error: " + uriSyntaxException.getMessage());
            return Optional.empty();
        }
    }

    @PreDestroy
    private void preDestroy() {
        try {
            client.close();
            log.debug("End CoinMarketCap");
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
    }


}