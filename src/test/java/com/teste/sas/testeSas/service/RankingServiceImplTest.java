package com.teste.sas.testeSas.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sas.testeSas.dto.RankingDTO;
import com.teste.sas.testeSas.entity.Aluno;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.handler.BusinessExpection;
import com.teste.sas.testeSas.repository.AlunoRepository;
import com.teste.sas.testeSas.repository.SimuladoRepository;
import com.teste.sas.testeSas.service.impl.RankingServiceImpl;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RankingServiceImplTest {

	@Mock
	AlunoRepository alunoRepository;

	@Mock
	SimuladoRepository simuladoRepository;
	
	@Mock
	ProvaAlunoService provaAlunoService;
	
	@InjectMocks
	RankingServiceImpl rankingService;
	
	@Test
    public void simuladoNaoEncontradoRankingServiceTest(){
    	when(simuladoRepository.findById(any())).thenReturn(Optional.empty());
		try {
			rankingService.execute(1L);
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE, e.getMessage());
			verify(simuladoRepository, times(1)).findById(any());
		}    	 
    }
	
	@Test
    public void rankingServiceTest(){
		
		Simulado simulado = new Simulado();
		simulado.setNomeSimulado("Ciências da Natureza");
		
		List<Aluno> alunos = IntStream.range(1, 10).mapToObj(i -> {
			if(i == 1 || i == 2)
				return new Aluno("Teste "+i+" Aluno", 200);			
			return new Aluno("Teste "+i+" Aluno", 3*i);
			
		}).collect(Collectors.toList());
				
    	when(simuladoRepository.findById(any())).thenReturn(Optional.of(simulado));
    	when(alunoRepository.findBySimulado(simulado)).thenReturn(alunos);
    	    	
		RankingDTO rankingDto = rankingService.execute(1L);
		assertEquals("Ciências da Natureza", rankingDto.getNomeSimulado());
		assertEquals(3, rankingDto.getRanking().get(2).getClassificacaoGeral().intValue());
		assertEquals("Teste 9 Aluno", rankingDto.getRanking().get(2).getAluno());
		assertEquals(27, rankingDto.getRanking().get(2).getNotaFinal().intValue());
		verify(simuladoRepository, times(1)).findById(any());
		   	 
    }
	
}
