package br.com.vivo.stream.consumer;

import br.com.vivo.service.CdrService;
import br.com.vivo.stream.topics.StreamTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerCdrDelecao {

    @Autowired
    private CdrService cdrService;

    @KafkaListener(topics = StreamTopics.CDR_DEL_TOPIC, groupId = StreamTopics.GROUP_ID)
    public void consumer(String id) {
        log.info("Consumed new Topic " + id);
        cdrService.deletar(Long.parseLong(id));
    }

}
