package com.orderparser.service;

import java.io.FileWriter;

import org.json.JSONArray;

import com.orderparser.exception.OrderparserException;
import com.orderparser.service.imp.DataWriter;

public class FileWriterService implements DataWriter {

    @Override
    public void escreverArquivoJson(JSONArray jsonArray, String nomeArquivo) {
	try (FileWriter fileWriter = new FileWriter(nomeArquivo)) {
	    fileWriter.write(jsonArray.toString());
	} catch (Exception e) {
	    throw new OrderparserException("Erro ao gerar arquivo JSON:" + e.getMessage(), e);
	}
    }

}