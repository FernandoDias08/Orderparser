package com.orderparser.enums;

public enum OrderColumnsEnum {

	NAME("name"), USER_ID("user_id"), ORDERS("orders"), DATE("date"), TOTAL("total"), ORDER_ID("order_id"),
	PRODUCTS("products"), VALUE("value"), PRODUCT_ID("product_id");

	private String value;

	private OrderColumnsEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}