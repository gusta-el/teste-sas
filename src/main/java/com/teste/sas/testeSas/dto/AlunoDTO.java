package com.teste.sas.testeSas.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlunoDTO {
	
	@NonNull
	private Long numeroInscricao;
	@NonNull
	private String aluno;
	
	
	
}
