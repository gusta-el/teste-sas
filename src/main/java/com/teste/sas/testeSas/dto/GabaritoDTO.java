package com.teste.sas.testeSas.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GabaritoDTO{

	@NonNull
	String nomeProva;
	@NonNull
	List<QuestaoDTO> gabarito;
	
	@Getter
	@Builder
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class QuestaoDTO{

		@NonNull
		String nivel;
		@NonNull
		Integer numeroQuestao;
		@NonNull
		String enunciado;
		@NonNull
		Character alternativaCorreta;
			
	}
	
}
