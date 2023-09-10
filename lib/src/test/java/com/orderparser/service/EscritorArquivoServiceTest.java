package com.orderparser.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.orderparser.exception.OrderparserException;

@ExtendWith(MockitoExtension.class)
class EscritorArquivoServiceTest {

	@Mock
	private EscritorArquivoService escritorArquivoServiceMock;

	private ConversorJsonService conversorJsonService;

	private ParserService parserService;

	private EscritorArquivoService escritorArquivoService;

	@BeforeEach
	public void setUp() {
		conversorJsonService = new ConversorJsonService();
		parserService = new ParserService();
		escritorArquivoService = new EscritorArquivoService();
	}

	@Test
	void deveriaEscreverArquivoJson() {

		List<Map<String, Object>> dados = parserService.lerDados("data_test");
		JSONArray json = conversorJsonService.converter(dados);

		escritorArquivoService.escreverArquivoJson(json, "testes.json");

		escritorArquivoServiceMock.escreverArquivoJson(json, "testes_mock.json");
		verify(escritorArquivoServiceMock).escreverArquivoJson(json, "testes_mock.json");
		
		removerArquivoTemporario("testes.json");

	}

	@Test
	void naoDeveriaEscreverArquivoJson() throws IOException {

		Exception exception = assertThrows(OrderparserException.class, () -> {
			escritorArquivoService.escreverArquivoJson(null, "OLOKO.BIXO");
		});

		String mensagemEsperada = "\"jsonArray\" is null";
		String mensagemRetornada = exception.getMessage();

		assertTrue(mensagemRetornada.contains(mensagemEsperada));
		removerArquivoTemporario("OLOKO.BIXO");
	}

	private void removerArquivoTemporario(String prefixoArquivo) {
		try (Stream<Path> arquivosTemporarios = Files.list(Paths.get(""))) {
			List<String> arquivosTeste = arquivosTemporarios.map(e -> e.getFileName().toString())
					.filter(e -> e.contains(prefixoArquivo)).collect(Collectors.toList());
			for (String arquivo : arquivosTeste) {
				Files.deleteIfExists(Paths.get(arquivo));
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover aquivo teste:" + e.getMessage());
		}

	}

}
