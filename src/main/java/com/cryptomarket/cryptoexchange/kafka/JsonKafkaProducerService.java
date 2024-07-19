package com.cryptomarket.cryptoexchange.kafka;

import com.cryptomarket.cryptoexchange.dto.coins.CryptoBriefInfoDto;
import com.cryptomarket.cryptoexchange.models.CryptoBriefInfo;
import com.cryptomarket.cryptoexchange.services.database.CryptoBriefInfoKafkaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JsonKafkaProducerService {

    private KafkaTemplate<String, List<CryptoBriefInfo>> kafkaTemplate;

    @Autowired
    public JsonKafkaProducerService(KafkaTemplate<String, List<CryptoBriefInfo>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCryptocurrenciesToKafka(List<CryptoBriefInfoKafkaDto> cryptoBriefInfoList) {
        Message<List<CryptoBriefInfoKafkaDto>> message = MessageBuilder
                .withPayload(cryptoBriefInfoList)
                .setHeader(KafkaHeaders.TOPIC,"cryptocurrency-prices")
                .build();
        kafkaTemplate.send(message);
        log.info("Send to Kafka -> " + message.toString());
    }
}
