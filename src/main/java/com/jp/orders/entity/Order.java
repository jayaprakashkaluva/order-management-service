package com.jp.orders.entity;

import java.util.List;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Order {

	@Field
	@Id
	private String orderId;
	@Field
	private String email;
	@Field
	private Address shippingAddress;
	@Field
	private Address billingAddress;
	@Field
	private Cost charges;
	@Field
	private Discounts discounts;
	@Field
	private List<Item> items;
	
	public String typeKey() {
	    return "Order";
	}
}
