package com.orderparser.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orderparser.exception.OrderparserException;
import com.orderparser.service.imp.CarregadorTemplate;

public class CarregadorTemplateTexto implements CarregadorTemplate {

	private final Map<String, List<String>> cache = new HashMap<>();
	private static final String DIRETORIO = "arquivos/";

	@Override
	public List<String> carregarTemplate(String prefixoNomeArquivo) {
		String nomeArquivo = prefixoNomeArquivo + ".txt";
		return cache.computeIfAbsent(nomeArquivo, this::carregarArquivo);
	}

	private List<String> carregarArquivo(String nomeArquivo) {
		String arquivoPath = DIRETORIO + nomeArquivo;
		List<String> linhas = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(ClassLoader.getSystemResourceAsStream(arquivoPath)))) {

			String line;
			int linhaAtual = 0;

			while ((line = reader.readLine()) != null) {
				if (linhaAtual >= 0) {
					linhas.add(line);
				}
				linhaAtual++;
			}
		} catch (Exception e) {
			throw new OrderparserException("Erro ao carregar o arquivo: " + nomeArquivo, e);
		}

		return linhas;
	}

}