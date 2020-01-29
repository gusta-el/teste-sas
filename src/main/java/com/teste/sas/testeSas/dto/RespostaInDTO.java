package com.teste.sas.testeSas.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespostaInDTO {
	
	@Min(message = "Número de questão inválido. Por favor verificar (1 ~ 10)", value = 1)
	@Max(message = "Número de questão inválido. Por favor verificar (1 ~ 10)", value = 10)
	@NotNull(message = "valor numeroQuestao é obrigatório")
	Integer numeroQuestao;
	@NotNull(message = "valor alternativaSelecionada é obrigatório")
	Character alternativaSelecionada;
	
}
