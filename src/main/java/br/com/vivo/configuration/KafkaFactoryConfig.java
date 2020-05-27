package br.com.vivo.configuration;

import br.com.vivo.dto.CriarCdrDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaFactoryConfig {

    @Bean
    public Map<String, Object> producerConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, CriarCdrDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConsumerConfigs());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory2() {
        return new DefaultKafkaProducerFactory<>(producerConsumerConfigs());
    }
    @Bean
    public ConsumerFactory<String, String> consumerFactory2() {
        return new DefaultKafkaConsumerFactory<>(
                producerConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>());
    }
    @Bean
    public ConsumerFactory<String, CriarCdrDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                producerConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(CriarCdrDto.class));
    }

    @Bean
    public KafkaTemplate<String, CriarCdrDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate2() {
        return new KafkaTemplate<>(producerFactory2());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CriarCdrDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CriarCdrDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory2());
        return factory;
    }
}
