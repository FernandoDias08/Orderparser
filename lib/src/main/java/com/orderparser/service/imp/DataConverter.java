package com.orderparser.service.imp;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

public interface DataConverter {

	JSONArray converter(List<Map<String, Object>> listaDeMapas);

}