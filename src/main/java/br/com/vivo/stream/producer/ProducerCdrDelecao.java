package br.com.vivo.stream.producer;

import br.com.vivo.stream.topics.StreamConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerCdrDelecao {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String id) {
        log.info("Produced new Topic", id);
        kafkaTemplate.send(StreamConfig.CDR_DEL_TOPIC, id);
    }
}
