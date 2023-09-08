package com.orderparser.service.imp;

import java.util.List;
import java.util.Map;

public interface DataReader {
	List<Map<String, Object>> lerDados(String prefixo);
}