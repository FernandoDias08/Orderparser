package com.orderparser;

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
	String prefixo = "data_1";
	String arquivoSaida = "pedidos.json";

	try {

	    DataReader leitor = new ParserService();
	    DataConverter conversor = new JsonConverterService();
	    DataWriter escritor = new FileWriterService();

	    List<Map<String, Object>> dadosMapeados = leitor.lerDados(prefixo);

	    if (dadosMapeados.isEmpty()) {
		throw new OrderparserException("Nenhum dado encontrado nos arquivos.");
	    }

	    JSONArray dadosJson = conversor.converter(dadosMapeados);
	    escritor.escreverArquivoJson(dadosJson, arquivoSaida);
	    

	    LOGGER.log(Level.INFO, "Dados --> {0}", dadosJson);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}