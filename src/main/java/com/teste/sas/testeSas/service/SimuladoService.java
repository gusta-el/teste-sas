package com.teste.sas.testeSas.service;

import java.util.List;

import com.teste.sas.testeSas.dto.SimuladoDTO;

public interface SimuladoService {
	
	SimuladoDTO execute(Long idProva);
	List<SimuladoDTO> execute();
	
}
