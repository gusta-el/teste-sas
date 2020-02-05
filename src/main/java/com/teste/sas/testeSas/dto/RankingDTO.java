package com.teste.sas.testeSas.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RankingDTO {

	String nomeSimulado;
	List<AlunoDTO> ranking;
	
	@Getter
	@Builder
	@Setter
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class AlunoDTO {
		
		@NonNull
		String aluno;
		@NonNull
		Integer notaFinal;
		@NonNull
		Integer classificacaoGeral;
			
		
	}
	
}
