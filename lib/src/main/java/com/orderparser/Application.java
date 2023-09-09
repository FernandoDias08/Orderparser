package com.orderparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;

import com.orderparser.exception.OrderparserException;
import com.orderparser.service.FileWriterService;
import com.orderparser.service.JsonConverterService;
import com.orderparser.service.ParserService;
import com.orderparser.service.imp.DataConverter;
import com.orderparser.service.imp.DataReader;
import com.orderparser.service.imp.DataWriter;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		while (true) {
			List<String> prefixosArquivos = List.of("data_1", "data_2");
			String arquivoSaida = "pedidos.json";

			try {

				// UMA HORA DE PRAZO
				long tempoDeEsperaEmMilissegundos = 3600L * 1000;
				DataReader leitor = new ParserService();
				DataConverter conversor = new JsonConverterService();
				DataWriter escritor = new FileWriterService();
				List<Map<String, Object>> dadosMapeados = new ArrayList<>();

				for (String prefixo : prefixosArquivos) {

					dadosMapeados.addAll(leitor.lerDados(prefixo));
				}

				if (dadosMapeados.isEmpty()) {
					throw new OrderparserException("Nenhum dado encontrado nos arquivos.");
				}

				JSONArray dadosJson = conversor.converter(dadosMapeados);
				escritor.escreverArquivoJson(dadosJson, arquivoSaida);

				LOGGER.log(Level.INFO, "Dados --> {0}", dadosJson);

				Thread.sleep(tempoDeEsperaEmMilissegundos);

			} catch (InterruptedException e) {
				LOGGER.log(Level.WARNING, "Thread interrompida!", e);
				Thread.currentThread().interrupt();
			}
		}
	}
}