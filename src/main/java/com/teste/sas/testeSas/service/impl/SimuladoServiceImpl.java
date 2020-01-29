package com.teste.sas.testeSas.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sas.testeSas.dto.SimuladoDTO;
import com.teste.sas.testeSas.dto.SimuladoDTO.ProvaDTO;
import com.teste.sas.testeSas.entity.Prova;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.repository.SimuladoRepository;
import com.teste.sas.testeSas.service.SimuladoService;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimuladoServiceImpl implements SimuladoService {

	@Autowired
	SimuladoRepository simuladoRepository;
	
	@Override
	public SimuladoDTO execute(Long idSimulado){

		Optional<Simulado> simuladoOp = simuladoRepository.findById(idSimulado);
		return transform(simuladoOp);

	}

	@Override
	public List<SimuladoDTO> execute() {

		Iterable<Simulado> simulados = simuladoRepository.findAll();

		return StreamSupport.stream(simulados.spliterator(), false)
				.map(simulado -> transform(Optional.ofNullable(simulado))).collect(Collectors.toList());

	}

	private SimuladoDTO transform(Optional<Simulado> simuladoOp) {
		SimuladoDTO simuladoDto = new SimuladoDTO();
		
		UtilsTesteSas.assertBusinessException(simuladoOp.isPresent(), () -> UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE);
		
		simuladoOp.ifPresent(simulado -> {
			simuladoDto.setNomeSimulado(simulado.getNomeSimulado());
			simuladoDto.setQuantidadeDeProvas(simulado.getProvas().size());
			simuladoDto.setDataDeAplicacao(simulado.getDataSimulado());
			simuladoDto.setProvas(extractProvas(simulado.getProvas()));

		});
		return simuladoDto;
	}

	private List<ProvaDTO> extractProvas(Set<Prova> provas) {
		return provas.stream()
				.map(prova -> ProvaDTO.builder().idProva(prova.getIdProva()).nomeProva(prova.getNomeProva()).build())
				.sorted(Comparator.comparingLong(ProvaDTO::getIdProva)).collect(Collectors.toList());

	}

}
