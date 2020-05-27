package br.com.vivo.stream.consumer;

import br.com.vivo.service.FechamentoContaService;
import br.com.vivo.stream.topics.StreamTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerFechamentoConta {

    @Autowired
    private FechamentoContaService fechamentoContaService;

    @KafkaListener(topics = StreamTopics.FECHAR_CONTA_TOPIC, groupId = StreamTopics.GROUP_ID)
    public void consumer() {
        log.info("Consumed new Topic - fechamento " );
        fechamentoContaService.fecharConta();
    }

}
