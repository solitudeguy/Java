package com.sourav.kafka.consumer.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.google.gson.Gson;

@Configuration
@EnableKafka
public class KafkaConfig {
	
	@Value("spring.kafka.bootstrap-servers")
	private String BOOTSTRAP_SERVERS_CONFIG ;
	@Value("spring.kafka.consumer.key-deserializer")
	private String KEY_DESERIALIZER_CLASS_CONFIG ;
	@Value("spring.kafka.consumer.value-deserializer")
	private String VALUE_DESERIALIZER_CLASS_CONFIG ;
	@Value("spring.kafka.consumer.enable-auto-commit")
	private String ENABLE_AUTO_COMMIT_CONFIG ;
	@Value("spring.kafka.consumer.group-id")
	private String GROUP_ID_CONFIG ;
	@Bean
    public ConsumerFactory<String, String> consumerFatory() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.getBoolean(ENABLE_AUTO_COMMIT_CONFIG));
        return new DefaultKafkaConsumerFactory<String, String>(config, new StringDeserializer(), new StringDeserializer());
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFatory());
        concurrentKafkaListenerContainerFactory.setMissingTopicsFatal(false);
        return concurrentKafkaListenerContainerFactory;
    }
 
    @Bean
    public Gson gsonJsonConverter() {
        return new Gson();
    }
}
