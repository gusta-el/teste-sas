package com.teste.sas.testeSas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sas.testeSas.entity.ProvaAluno;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.repository.ProvaAlunoRepository;
import com.teste.sas.testeSas.repository.SimuladoRepository;
import com.teste.sas.testeSas.service.ProvaAlunoService;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvaAlunoServiceImpl implements ProvaAlunoService{

	@Autowired
	ProvaAlunoRepository provaAlunoRepository;

	@Autowired
	SimuladoRepository simuladoRepository;
	
	@Override
	public void calcularNotas(Long idSimulado) {
		
		Optional<Simulado> simuladoOp = simuladoRepository.findById(idSimulado);
		UtilsTesteSas.assertBusinessException(simuladoOp.isPresent(), () -> UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE);
		
		Simulado simulado = simuladoOp.get();
		
	}

	
	
}
