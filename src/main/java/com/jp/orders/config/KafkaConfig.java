package com.jp.orders.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Priority;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class KafkaConfig {

	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "oms");
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> listenerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		listenerFactory.setConsumerFactory(consumerFactory());
		return listenerFactory;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
