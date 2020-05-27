package br.com.vivo.stream.consumer;

import br.com.vivo.dto.CriarCdrDto;
import br.com.vivo.service.CdrService;
import br.com.vivo.stream.producer.ProducerSaldo;
import br.com.vivo.stream.topics.StreamTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerCdrCriacao {

    @Autowired
    private CdrService cdrService;

    @Autowired
    private ProducerSaldo producerSaldo;

    @KafkaListener(topics = StreamTopics.CDR_TOPIC, groupId = StreamTopics.GROUP_ID)
    public void consumer(CriarCdrDto dto) {
        log.info("Consumed new Topic " + dto);
        cdrService.criar(dto);
        producerSaldo.send(dto.getOrigem());
    }

}
