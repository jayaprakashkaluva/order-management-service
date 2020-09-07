package com.jp.orders.listener;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.orders.entity.Order;
import com.jp.orders.repository.OrderRepository;
import com.jp.orders.vo.GenericPayload;

@Service
public class OrderProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessor.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void postProcess() {
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}
	@KafkaListener(id="processor",topics = {"orders","products"})
	public void process(ConsumerRecord<String, String> message) {
		LOGGER.info("message is {}", message);
		LOGGER.info("key is {}", message.key());
		LOGGER.info("value is {}", message.value());
		LOGGER.info("partition is {},offset is{}", message.partition(), message.offset());
	
		try {
			GenericPayload<?> payload  = objectMapper.readValue(message.value(), new TypeReference<GenericPayload<Order>>(){}); 
			switch(payload.getChannel()) {
			case "PRODUCTS":
				break;
			case "ORDERS":
			orderRepository.save((Order) payload.getPayload());
			}
		} catch (IOException e) {
			LOGGER.error("unable to process order", e);
		}
	}
}
