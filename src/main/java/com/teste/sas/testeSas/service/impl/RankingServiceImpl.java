package com.teste.sas.testeSas.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sas.testeSas.dto.RankingDTO;
import com.teste.sas.testeSas.dto.RankingDTO.AlunoDTO;
import com.teste.sas.testeSas.entity.Aluno;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.repository.AlunoRepository;
import com.teste.sas.testeSas.repository.SimuladoRepository;
import com.teste.sas.testeSas.service.ProvaAlunoService;
import com.teste.sas.testeSas.service.RankingService;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{
	
	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	SimuladoRepository simuladoRepository;
	
	@Autowired
	ProvaAlunoService provaAlunoService;
	
	@Override
	public RankingDTO execute(Long idSimulado) {
		
		provaAlunoService.execute(idSimulado);
		
		Optional<Simulado> simuladoOp = simuladoRepository.findById(idSimulado);
		UtilsTesteSas.assertBusinessException(simuladoOp.isPresent(), () -> UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE);
				
		return RankingDTO.builder()
				.nomeSimulado(simuladoOp.get().getNomeSimulado())
				.ranking(this.gerarRankingAlunos(simuladoOp.get()))
				.build();
		
	}
	
	private List<AlunoDTO> gerarRankingAlunos(Simulado simulado){
		
		List<Aluno> alunos = alunoRepository.findBySimulado(simulado);
		
		Comparator<Aluno> comparator = Comparator.comparing(Aluno::getNotaFinal); 
		Collections.sort(alunos, comparator.reversed());
		
		List<AlunoDTO> alunosDto = IntStream.range(1, 6).mapToObj(i -> AlunoDTO.builder()
				.classificacaoGeral(i)
				.aluno(alunos.get(i-1).getNomeAluno())
				.notaFinal(alunos.get(i-1).getNotaFinal())
				.build())
				.collect(Collectors.toList());
		
		alunosDto.stream().reduce((aluno, alunoNext) -> {
			
			if(aluno.getNotaFinal().equals(alunoNext.getNotaFinal())) 
				alunoNext.setClassificacaoGeral(aluno.getClassificacaoGeral());
			return alunoNext;
			
		});	
		
		return alunosDto;
		
	}
	

}
