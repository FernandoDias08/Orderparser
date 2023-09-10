package com.orderparser.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;

import com.orderparser.enums.OrderColumnsEnum;
import com.orderparser.service.imp.DataConverter;

public class ConversorJsonService implements DataConverter {

    @Override
    public JSONArray converter(List<Map<String, Object>> listaDeMapas) {
	List<Map<String, Object>> lista = listaDeMapas.stream()
		.collect(Collectors.groupingBy(e -> e.get(OrderColumnsEnum.USER_ID.getValue()))).entrySet().stream()
		.map(entry -> {
		    // PREENCHE OS DADOS DE USUARIO
		    Map<String, Object> purchase = new HashMap<>();
		    purchase.put(OrderColumnsEnum.USER_ID.getValue(), Long.valueOf(entry.getKey().toString()));
		    purchase.put(OrderColumnsEnum.NAME.getValue(),
			    entry.getValue().get(0).get(OrderColumnsEnum.NAME.getValue()).toString());

		    // PREENCHE OS PEDIDOS RELACIONADOS
		    List<Map<String, Object>> orders = entry.getValue().stream().map(e -> {
			Map<String, Object> order = new HashMap<>();
			order.put(OrderColumnsEnum.ORDER_ID.getValue(),
				Long.valueOf(e.get(OrderColumnsEnum.ORDER_ID.getValue()).toString()));
			order.put(OrderColumnsEnum.TOTAL.getValue(),
				entry.getValue().stream()
					.filter(f -> f.get(OrderColumnsEnum.ORDER_ID.getValue())
						.equals(e.get(OrderColumnsEnum.ORDER_ID.getValue())))
					.map(f -> new BigDecimal(f.get(OrderColumnsEnum.VALUE.getValue()).toString()))
					.reduce(BigDecimal.ZERO, BigDecimal::add));
			order.put(OrderColumnsEnum.DATE.getValue(), e.get(OrderColumnsEnum.DATE.getValue()).toString());
			return order;
		    }).distinct().toList();
		    purchase.put(OrderColumnsEnum.ORDERS.getValue(), orders);

		    // PREENCHE OS PRODUTOS RELACIONADOS
		    orders.forEach(order -> order.put(OrderColumnsEnum.PRODUCTS.getValue(),
			    listaDeMapas.stream()
				    .filter(dado -> Long
					    .valueOf(dado.get(OrderColumnsEnum.USER_ID.getValue()).toString())
					    .equals(purchase.get(OrderColumnsEnum.USER_ID.getValue()))
					    && Long.valueOf(dado.get(OrderColumnsEnum.ORDER_ID.getValue()).toString())
						    .equals(order.get(OrderColumnsEnum.ORDER_ID.getValue())))
				    .map(dadoFiltrado -> {
					Map<String, Object> product = new HashMap<>();
					product.put(OrderColumnsEnum.PRODUCT_ID.getValue(), Long.valueOf(
						dadoFiltrado.get(OrderColumnsEnum.PRODUCT_ID.getValue()).toString()));
					product.put(OrderColumnsEnum.VALUE.getValue(), new BigDecimal(
						dadoFiltrado.get(OrderColumnsEnum.VALUE.getValue()).toString()));
					return product;
				    }).toList()));

		    return purchase;

		}).toList();
	
	return new JSONArray(lista);
    }
}