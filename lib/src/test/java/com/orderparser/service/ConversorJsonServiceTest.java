package com.orderparser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.orderparser.enums.OrderColumnsEnum;

@ExtendWith(MockitoExtension.class)
class ConversorJsonServiceTest {

	private ConversorJsonService conversorJsonService;

	@BeforeEach
	public void setUp() {
		conversorJsonService = new ConversorJsonService();
	}

	@Test
	void deveriaConverter() {
		JSONArray json = conversorJsonService.converter(criarMapDados());
		assertEquals(2, json.length());
		assertEquals("JOHN MAC'N ROLL", json.getJSONObject(0).get(OrderColumnsEnum.NAME.getValue()));
		assertEquals(1, json.getJSONObject(0).getJSONArray(OrderColumnsEnum.ORDERS.getValue()).length());
		assertEquals(2, json.getJSONObject(0).getJSONArray(OrderColumnsEnum.ORDERS.getValue()).getJSONObject(0)
				.getJSONArray(OrderColumnsEnum.PRODUCTS.getValue()).length());
		assertEquals(2, json.getJSONObject(1).getJSONArray(OrderColumnsEnum.ORDERS.getValue()).length());

	}

	private List<Map<String, Object>> criarMapDados() {
		Map<String, Object> linha1 = new HashMap<>();
		Map<String, Object> linha2 = new HashMap<>();
		Map<String, Object> linha3 = new HashMap<>();
		Map<String, Object> linha4 = new HashMap<>();
		
		linha1.put(OrderColumnsEnum.USER_ID.getValue(), "123456");
		linha1.put(OrderColumnsEnum.NAME.getValue(), "JOHN MAC'N ROLL");
		linha1.put(OrderColumnsEnum.ORDER_ID.getValue(), "0000000753");
		linha1.put(OrderColumnsEnum.PRODUCT_ID.getValue(), "0000000003");
		linha1.put(OrderColumnsEnum.VALUE.getValue(), "301.50"); // UMA CALÇA PARA UMA JOVEM DE 16 ANOS
		linha1.put(OrderColumnsEnum.DATE.getValue(), "20210308");

		linha2.put(OrderColumnsEnum.USER_ID.getValue(), "123456");
		linha2.put(OrderColumnsEnum.NAME.getValue(), "JOHN MAC'N ROLL");
		linha2.put(OrderColumnsEnum.ORDER_ID.getValue(), "0000000753");
		linha2.put(OrderColumnsEnum.PRODUCT_ID.getValue(), "0000000004");
		linha2.put(OrderColumnsEnum.VALUE.getValue(), "8001.99"); // SEU PODER É MAIS DE 8000!
		linha2.put(OrderColumnsEnum.DATE.getValue(), "20210308");
		
		linha3.put(OrderColumnsEnum.USER_ID.getValue(), "789456");
		linha3.put(OrderColumnsEnum.NAME.getValue(), "JIMMY LEROY");
		linha3.put(OrderColumnsEnum.ORDER_ID.getValue(), "0000000754");
		linha3.put(OrderColumnsEnum.PRODUCT_ID.getValue(), "0000000005");
		linha3.put(OrderColumnsEnum.VALUE.getValue(), "300.99"); // SEU PODER É MAIS DE 8000!
		linha3.put(OrderColumnsEnum.DATE.getValue(), "20210308");
		
		linha4.put(OrderColumnsEnum.USER_ID.getValue(), "789456");
		linha4.put(OrderColumnsEnum.NAME.getValue(), "JIMMY LEROY");
		linha4.put(OrderColumnsEnum.ORDER_ID.getValue(), "0000000755");
		linha4.put(OrderColumnsEnum.PRODUCT_ID.getValue(), "0000000006");
		linha4.put(OrderColumnsEnum.VALUE.getValue(), "1.99"); // SEU PODER É MAIS DE 8000!
		linha4.put(OrderColumnsEnum.DATE.getValue(), "20210309");

		return List.of(linha1, linha2, linha3, linha4);
	}

}
