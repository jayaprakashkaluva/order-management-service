package com.jp.orders.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericPayload<T> {
	 private String channel;
	 private T payload;
}
