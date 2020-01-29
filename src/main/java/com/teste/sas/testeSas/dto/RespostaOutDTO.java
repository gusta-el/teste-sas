package com.teste.sas.testeSas.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
	
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespostaOutDTO {

	Integer numeroQuestao;
	Character alternativaEscolhida;
	Character alternativaCorreta;
	Complexidade complexidade;
	
}
