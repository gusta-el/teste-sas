package com.teste.sas.testeSas.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimuladoDTO {
	
	private String nomeSimulado;
	private LocalDate dataDeAplicacao;
	private Integer quantidadeDeProvas;
	private List<ProvaDTO> provas;
	
	@Getter
	@Builder
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class ProvaDTO {

		Long idProva;
		String nomeProva;
		
	}

	
}
