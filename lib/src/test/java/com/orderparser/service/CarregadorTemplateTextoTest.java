package com.orderparser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.orderparser.exception.OrderparserException;

@ExtendWith(MockitoExtension.class)
class CarregadorTemplateTextoTest {

	private CarregadorTemplateTexto carregadorTemplateTexto;

	@BeforeEach
	public void setUp() {
		carregadorTemplateTexto = new CarregadorTemplateTexto();
	}

	@Test
	void deveriaCarregarTemplate() {

		List<String> linhasArquivo = carregadorTemplateTexto.carregarTemplate("data_test");

		assertNotNull(linhasArquivo);
		assertFalse(linhasArquivo.isEmpty());
		assertEquals(1, linhasArquivo.size());

	}

	@Test
	void naoDeveriaCarregarTemplate() {

		Exception exception = assertThrows(OrderparserException.class, () -> {
			carregadorTemplateTexto.carregarTemplate("PI-PI-PI-TCHU");
		});

		String mensagemEsperada = "Erro ao carregar o arquivo: PI-PI-PI-TCHU.txt";
		String mensagemRetornada = exception.getMessage();

		assertEquals(mensagemEsperada, mensagemRetornada);
	}

}
