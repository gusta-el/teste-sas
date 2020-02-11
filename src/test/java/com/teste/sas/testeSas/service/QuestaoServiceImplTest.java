package com.teste.sas.testeSas.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sas.testeSas.dto.Complexidade;
import com.teste.sas.testeSas.dto.RespostaQuestao;
import com.teste.sas.testeSas.entity.Prova;
import com.teste.sas.testeSas.entity.ProvaAluno;
import com.teste.sas.testeSas.entity.Questao;
import com.teste.sas.testeSas.handler.BusinessExpection;
import com.teste.sas.testeSas.repository.ProvaAlunoRepository;
import com.teste.sas.testeSas.service.impl.QuestaoServiceImpl;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class QuestaoServiceImplTest {

	@Mock
	ProvaAlunoRepository provaAlunoRepository;
	
	@InjectMocks
	QuestaoServiceImpl questaoService;
	
//	@Test
    public void questsoNaoEncontradaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 3;
		Integer nota = 0;
		
		ProvaAluno provaAluno = this.mockProvaAluno(UtilsTesteSas.QUESTOES_BLANK, false, nota);			
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	
		try {
			questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).build());
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.QUESTAO_NAO_ENCONTRADA_MESSAGE, e.getMessage());
			verify(provaAlunoRepository, times(1)).findById(any());
		}    	 
    }
	
	@Test
    public void questaoNaoEncontradaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 3;
		Integer nota = 920;
		
		ProvaAluno provaAluno = this.mockProvaAluno("A  AACCBAA", false, nota);			
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	
		try {
			questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).build());
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.QUESTAO_NAO_ENCONTRADA_MESSAGE, e.getMessage());
			verify(provaAlunoRepository, times(1)).findById(any());
		}    	 
    }
	
	@Test
    public void provaFinalizadaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 3;
		Integer nota = 920;
		
		ProvaAluno provaAluno = this.mockProvaAluno("AB AACCBAA", true, nota);
		
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	
		try {
			questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).build());
		} catch (BusinessExpection e) {
			assertEquals("Prova finalizada e calculada. Nota do aluno: " + nota, e.getMessage());
			verify(provaAlunoRepository, times(1)).findById(any());
		}    	 
    }
	
	@Test
    public void provaNaoEncontradaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		when(provaAlunoRepository.findById(any())).thenReturn(Optional.empty());
    	
		try {
			questaoService.execute(RespostaQuestao.builder().idProva(idProva).build());
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE, e.getMessage());
			verify(provaAlunoRepository, times(1)).findById(any());
		}    	 
    }
		
	@Test
    public void questaoJaRespondidaServiceTest(){

		Integer numeroQuestao = 3;
		ProvaAluno provaAluno = this.mockProvaAluno("ABDAACCBAA", true, 920);
		
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	
		try {
			questaoService.execute(RespostaQuestao.builder().numeroQuestao(numeroQuestao).build());
		} catch (BusinessExpection e) {
			assertEquals("Questão de Nº " + numeroQuestao + " já respondida!", e.getMessage());
			verify(provaAlunoRepository, times(1)).findById(any());
		}    	 
    }
	
	private ProvaAluno mockProvaAluno(String gabarito, Boolean provaFinalizada, Integer nota) {
		
		ProvaAluno provaAluno = new ProvaAluno();
		provaAluno.setGabaritoAluno(gabarito);
		provaAluno.setProvaFinalizada(provaFinalizada);
		provaAluno.setNota(nota);
		
		Prova prova = new Prova(1L, "Matemática", LongStream.range(1, 10).mapToObj(i -> {
			if(i != 2)
				return new Questao(i, "Lorem Ipslum", Complexidade.DIFÍCIL.toString(), 'B', (int) i+1);
			return new Questao(i, "Lorem Ipslum", Complexidade.DIFÍCIL.toString(), 'B', (int) i);
		}).collect(Collectors.toSet()));
		
		provaAluno.setProva(prova);
		return provaAluno;
		
	}
	
	
	
}
