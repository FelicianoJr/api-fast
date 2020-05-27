package br.com.vivo.stream.producer;

import br.com.vivo.stream.topics.StreamConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerFechamentoConta {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        log.info("Produced new Topic - fechamento" );
        kafkaTemplate.send(StreamConfig.FECHAR_CONTA_TOPIC,"");
    }
}
