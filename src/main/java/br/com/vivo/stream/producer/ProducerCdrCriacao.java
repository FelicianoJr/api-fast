package br.com.vivo.stream.producer;

import br.com.vivo.dto.CriarCdrDto;
import br.com.vivo.stream.topics.StreamTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerCdrCriacao {

    @Autowired
    private KafkaTemplate<String, CriarCdrDto> kafkaTemplate;

    public void send(CriarCdrDto criarCdrDto) {
        log.info("Produced new Topic", criarCdrDto);
        kafkaTemplate.send(StreamTopics.CDR_TOPIC, criarCdrDto);
    }
}
