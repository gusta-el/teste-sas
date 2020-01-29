package com.teste.sas.testeSas.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespostaQuestao {

	Long idProva;
	Integer numeroQuestao;
	Character alternativaSelecionada;
	
}
