package com.teste.sas.testeSas.dto;

import java.time.LocalDate;
import java.util.List;

import com.teste.sas.testeSas.dto.SimuladoDTO.ProvaDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RankingDTO {

	private String nomeSimulado;
	private LocalDate dataDeAplicacao;
	private Integer quantidadeDeProvas;
	private List<ProvaDTO> provas;
	
}
