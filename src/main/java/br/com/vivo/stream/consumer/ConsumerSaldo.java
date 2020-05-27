package br.com.vivo.stream.consumer;

import br.com.vivo.service.ProcessamentoSaldoService;
import br.com.vivo.stream.topics.StreamConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerSaldo {

    @Autowired
    private ProcessamentoSaldoService processamentoSaldoService;

    @KafkaListener(topics = StreamConfig.SALDO_TOPIC, groupId = StreamConfig.GROUP_ID)
    public void consumer(String numero) {
        log.info("Consumed new Topic " + numero);
        processamentoSaldoService.registrar(numero);
    }

}
