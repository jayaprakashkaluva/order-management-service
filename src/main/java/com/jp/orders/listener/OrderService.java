package com.jp.orders.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.orders.entity.Order;
import com.jp.orders.exception.DataNotFoundException;
import com.jp.orders.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	public OrderRepository orderRepository;

	public Order getOrder(String orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new DataNotFoundException("No Order Found"));
	}
	public List<Order> getOrders(String email) {
		return orderRepository.findByEmail(email);
	}
	
	public List<Order> getOrders(String email,String zipCode) {
		return orderRepository.findByEmailAndBillingAddress_Zipcode(email, zipCode);
	}
}
