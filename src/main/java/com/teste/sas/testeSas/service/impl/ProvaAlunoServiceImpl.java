package com.teste.sas.testeSas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sas.testeSas.entity.Aluno;
import com.teste.sas.testeSas.entity.ProvaAluno;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.repository.AlunoRepository;
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
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Override
	public void execute(Long idSimulado) {
		
		Optional<Simulado> simuladoOp = simuladoRepository.findById(idSimulado);
		UtilsTesteSas.assertBusinessException(simuladoOp.isPresent(), () -> UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE);
		
		UtilsTesteSas.assertBusinessException(simuladoOp.get().getSimuladoEncerrado(), () -> UtilsTesteSas.SIMULADO_EM_ANDAMENTO_MESSAGE);
		
		this.calcularNotas(simuladoOp.get());
			
	}
	
	private void calcularNotas(Simulado simulado) {
		List<Aluno> alunos = alunoRepository.findBySimulado(simulado);
		List<List<ProvaAluno>> alunosProvas = alunos.stream().map(aluno -> provaAlunoRepository.findByAluno(aluno)).collect(Collectors.toList());
		
		alunosProvas.forEach(provas -> {
			Aluno aluno = alunoRepository.findByIdAluno(provas.get(0).getAluno().getIdAluno());
					
			Integer somaNotas = StreamSupport.stream(provas.spliterator(), false).filter(ProvaAluno::getProvaFinalizada).collect(Collectors.summingInt(ProvaAluno::getNota));	
			aluno.setNotaFinal(somaNotas / provas.size());
			alunoRepository.save(aluno);
		});
	}

}
