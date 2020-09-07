package com.jp.orders.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

	private String itemId;
	private String productId;
	private List<Sku> sku;
}
