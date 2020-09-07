package com.jp.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jp.orders.entity.Order;
import com.jp.orders.listener.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private KafkaListenerEndpointRegistry registry;

	@GetMapping("/stop/{listenerID}")
	public void stop(@PathVariable String listenerID) {
		registry.getListenerContainer(listenerID).pause();
	}

	@GetMapping("/resume/{listenerID}")
	public void resume(@PathVariable String listenerID) {
		registry.getListenerContainer(listenerID).resume();
	}

	@GetMapping("/orders/{orderId}")
	public Order getOrder(@PathVariable String orderId) {
		return orderService.getOrder(orderId);
	}

	@GetMapping("/orders")
	public List<Order> getOrderByEmail(@RequestParam String email, String zipCode) {
		if (null != email && null != zipCode) {
			return orderService.getOrders(email, zipCode);
		} else if (null != email) {
			return orderService.getOrders(email);
		}
		return null;
	}
}
