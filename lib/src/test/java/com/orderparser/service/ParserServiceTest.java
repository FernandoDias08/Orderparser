package com.orderparser.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.orderparser.enums.OrderColumnsEnum;
import com.orderparser.exception.OrderparserException;

@ExtendWith(MockitoExtension.class)
class ParserServiceTest {
	
    private ParserService parserService;

    @BeforeEach
    public void setUp() {
        parserService = new ParserService();
    }

    @Test
    void deveriaLerDados() throws IOException {
        List<Map<String, Object>> dados = parserService.lerDados("data_test");

        assertNotNull(dados);
        assertFalse(dados.isEmpty());
        assertEquals(1, dados.size());

        Map<String, Object> primeiroRegistro = dados.get(0);
        assertEquals("0000000075", primeiroRegistro.get(OrderColumnsEnum.USER_ID.getValue()));
        assertEquals("Bobbie Batz", primeiroRegistro.get(OrderColumnsEnum.NAME.getValue()));
        assertEquals("000000798", primeiroRegistro.get(OrderColumnsEnum.ORDER_ID.getValue()));
        assertEquals("000000002", primeiroRegistro.get(OrderColumnsEnum.PRODUCT_ID.getValue()));
        assertEquals("1578.57", primeiroRegistro.get(OrderColumnsEnum.VALUE.getValue()));
        assertEquals("20211116", primeiroRegistro.get(OrderColumnsEnum.DATE.getValue()));

    }
    
    @Test
    void naoDeveriaLerDados() throws IOException {

	    Exception exception = assertThrows(OrderparserException.class, () -> {
	    	parserService.lerDados("PI-PI-PI-TCHU");
		    });

		    String mensagemEsperada = "Erro ao ler arquivo: Erro ao carregar o arquivo: PI-PI-PI-TCHU.txt";
		    String mensagemRetornada = exception.getMessage();
		    
		    assertEquals(mensagemEsperada, mensagemRetornada);
    }
}


