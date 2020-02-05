package com.teste.sas.testeSas.utils;

import java.util.Objects;
import java.util.function.Supplier;

import com.teste.sas.testeSas.handler.BusinessExpection;

public class UtilsTesteSas {

	public static final String QUESTOES_BLANK = "          ";
	public static final Character QUESTAO_NAO_RESPONDIDA = ' ';
	public static final String METHOD_ARGUMENT_NOT_VALID_MESSAGE = "Ocorreu um erro ao realizar a requisição.";
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Ocorreu um erro inesperado.";
	public static final String PROVA_NAO_ENCONTRADA_MESSAGE = "Prova não encontrada!";
	public static final String QUESTAO_NAO_ENCONTRADA_MESSAGE = "Questão não encontrada!";
	public static final String SIMULADO_NAO_ENCONTRADO_MESSAGE = "Simulado não encontrado!";
	public static final String SIMULADO_EM_ANDAMENTO_MESSAGE = "Simulado em andamento!";	
	
	public static void assertBusinessException(boolean expression, Supplier<String> message) {
		Objects.requireNonNull(message);
		if (!expression) {
			throw new BusinessExpection(message.get());
		}
	}
	
}
