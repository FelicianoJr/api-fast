package br.com.vivo.scheduler;

import br.com.vivo.stream.producer.ProducerFechamentoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerFechamentoConta {

    @Autowired
    private ProducerFechamentoConta producerFechamentoConta;

    @Scheduled(cron = "${fechamento.conta.scheduler.cron}")
    public void fecharConta() {
            producerFechamentoConta.send();
    }
}
