package com.orderparser.service.imp;

import org.json.JSONArray;

public interface DataWriter {
    void escreverArquivoJson(JSONArray jsonArray, String nomeArquivo);
}