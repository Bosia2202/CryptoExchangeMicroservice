package com.cryptomarket.cryptoexchange.kafka;

import com.cryptomarket.cryptoexchange.dto.coins.CryptoBriefInfoDto;
import com.cryptomarket.cryptoexchange.models.CryptoBriefInfo;
import com.cryptomarket.cryptoexchange.services.database.CryptoBriefInfoKafkaDto;

import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JsonKafkaProducerService {

    private KafkaTemplate<String, CryptoBriefInfoKafkaDto> kafkaTemplate;

    @Autowired
    public JsonKafkaProducerService(KafkaTemplate<String, CryptoBriefInfoKafkaDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //TODO:Реализовать CompletableFuture<SendResult<String,CryptoBriefInfoKafkaDto>> future
    //https://www.youtube.com/watch?v=wlFnlNthAPQ
    public void sendCryptocurrenciesToKafka(List<CryptoBriefInfoKafkaDto> cryptoBriefInfoList) {
        cryptoBriefInfoList.stream().forEach(info -> kafkaTemplate.send("cryptocurrency-prices", info));
    }
}
