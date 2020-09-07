package com.jp.orders.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Discounts {
	private BigDecimal discount;
	private String type;

}
