package com.teste.sas.testeSas.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimuladoDTO {
	
	String nomeSimulado;
	LocalDate dataDeAplicacao;
	Integer quantidadeDeProvas;
	List<ProvaDTO> provas;
	
	@Getter
	@Builder
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class ProvaDTO {

		Long idProva;
		String nomeProva;
		
	}

	
}
