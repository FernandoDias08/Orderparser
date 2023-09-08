package com.orderparser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orderparser.enums.OrderColumnsEnum;
import com.orderparser.exception.OrderparserException;
import com.orderparser.service.imp.CarregadorTemplate;
import com.orderparser.service.imp.DataReader;

public class ParserService implements DataReader {

	@Override
	public List<Map<String, Object>> lerDados(String prefixo) {

		try {
			CarregadorTemplate carregadorTexto = new CarregadorTemplateTexto();

			List<String> linhas = carregadorTexto.carregarTemplate(prefixo);
			return linhas.parallelStream().map(this::mapearDados).toList();

		} catch (Exception e) {
			throw new OrderparserException("Erro ao ler arquivo: " + e.getMessage(), e);
		}

	}

	private Map<String, Object> mapearDados(String linha) {
		Map<String, Object> map = new HashMap<>();
		map.put(OrderColumnsEnum.USER_ID.getValue(), linha.substring(0, 10).strip());
		map.put(OrderColumnsEnum.NAME.getValue(), linha.substring(11, 55).stripLeading());
		map.put(OrderColumnsEnum.ORDER_ID.getValue(), linha.substring(56, 65).strip());
		map.put(OrderColumnsEnum.PRODUCT_ID.getValue(), linha.substring(66, 76).strip());
		map.put(OrderColumnsEnum.VALUE.getValue(), linha.substring(77, 87).stripLeading());
		map.put(OrderColumnsEnum.DATE.getValue(), linha.substring(87).strip());
		return map;
	}

}