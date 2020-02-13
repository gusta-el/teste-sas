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
import com.teste.sas.testeSas.dto.RespostaOutDTO;
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
	
	@Test
    public void finalizarProvaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 2;
		Integer nota = 0;
		Character alternativaCorreta = 'B';
		Character alternativaSelecionada = 'B';
		
		ProvaAluno provaAluno = this.mockProvaAluno("A BDACDDAB", false, nota, Complexidade.DIFÍCIL);			
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	when(provaAlunoRepository.save(any())).thenReturn(any());
    			
    	RespostaOutDTO respostaOutDto = questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).alternativaSelecionada('B').build());
		
		assertEquals(alternativaCorreta, respostaOutDto.getAlternativaCorreta());
		assertEquals(alternativaSelecionada, respostaOutDto.getAlternativaEscolhida());
		assertEquals(Complexidade.DIFÍCIL, respostaOutDto.getComplexidade());
		assertEquals(numeroQuestao, respostaOutDto.getNumeroQuestao());
				
		verify(provaAlunoRepository, times(1)).findById(any());
		verify(provaAlunoRepository, times(1)).save(any());
		
    }
	
	@Test
    public void responderQuestaoFacilQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 2;
		Integer nota = 0;
		Character alternativaCorreta = 'B';
		Character alternativaSelecionada = 'B';
		
		ProvaAluno provaAluno = this.mockProvaAluno(UtilsTesteSas.QUESTOES_BLANK, false, nota, Complexidade.FÁCIL);			
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	when(provaAlunoRepository.save(any())).thenReturn(any());
    			
    	RespostaOutDTO respostaOutDto = questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).alternativaSelecionada('B').build());
		
		assertEquals(alternativaCorreta, respostaOutDto.getAlternativaCorreta());
		assertEquals(alternativaSelecionada, respostaOutDto.getAlternativaEscolhida());
		assertEquals(Complexidade.FÁCIL, respostaOutDto.getComplexidade());
		assertEquals(numeroQuestao, respostaOutDto.getNumeroQuestao());
				
		verify(provaAlunoRepository, times(1)).findById(any());
		verify(provaAlunoRepository, times(1)).save(any());
		
    }
	
	@Test
    public void responderQuestaoMediaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 2;
		Integer nota = 0;
		Character alternativaCorreta = 'B';
		Character alternativaSelecionada = 'B';
		
		ProvaAluno provaAluno = this.mockProvaAluno(UtilsTesteSas.QUESTOES_BLANK, false, nota, Complexidade.MÉDIA);			
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	when(provaAlunoRepository.save(any())).thenReturn(any());
    			
    	RespostaOutDTO respostaOutDto = questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).alternativaSelecionada('B').build());
		
		assertEquals(alternativaCorreta, respostaOutDto.getAlternativaCorreta());
		assertEquals(alternativaSelecionada, respostaOutDto.getAlternativaEscolhida());
		assertEquals(Complexidade.MÉDIA, respostaOutDto.getComplexidade());
		assertEquals(numeroQuestao, respostaOutDto.getNumeroQuestao());
				
		verify(provaAlunoRepository, times(1)).findById(any());
		verify(provaAlunoRepository, times(1)).save(any());
		
    }
	
	@Test
    public void responderQuestaoDificilQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 2;
		Integer nota = 0;
		Character alternativaCorreta = 'B';
		Character alternativaSelecionada = 'B';
		
		ProvaAluno provaAluno = this.mockProvaAluno(UtilsTesteSas.QUESTOES_BLANK, false, nota, Complexidade.DIFÍCIL);			
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	when(provaAlunoRepository.save(any())).thenReturn(any());
    			
    	RespostaOutDTO respostaOutDto = questaoService.execute(RespostaQuestao.builder().idProva(idProva).numeroQuestao(numeroQuestao).alternativaSelecionada('B').build());
		
		assertEquals(alternativaCorreta, respostaOutDto.getAlternativaCorreta());
		assertEquals(alternativaSelecionada, respostaOutDto.getAlternativaEscolhida());
		assertEquals(Complexidade.DIFÍCIL, respostaOutDto.getComplexidade());
		assertEquals(numeroQuestao, respostaOutDto.getNumeroQuestao());
				
		verify(provaAlunoRepository, times(1)).findById(any());
		verify(provaAlunoRepository, times(1)).save(any());
		
    }
	
	@Test
    public void questaoNaoEncontradaQuestaoServiceTest(){
    	
		Long idProva = 1L;
		Integer numeroQuestao = 3;
		Integer nota = 920;
		
		ProvaAluno provaAluno = this.mockProvaAluno("A  AACCBAA", false, nota, Complexidade.DIFÍCIL);			
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
		
		ProvaAluno provaAluno = this.mockProvaAluno("AB AACCBAA", true, nota, Complexidade.DIFÍCIL);
		
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
		ProvaAluno provaAluno = this.mockProvaAluno("ABDAACCBAA", true, 920, Complexidade.DIFÍCIL);
		
    	when(provaAlunoRepository.findById(any())).thenReturn(Optional.of(provaAluno));
    	
		try {
			questaoService.execute(RespostaQuestao.builder().numeroQuestao(numeroQuestao).build());
		} catch (BusinessExpection e) {
			assertEquals("Questão de Nº " + numeroQuestao + " já respondida!", e.getMessage());
			verify(provaAlunoRepository, times(1)).findById(any());
		}    	 
    }
	
	private ProvaAluno mockProvaAluno(String gabarito, Boolean provaFinalizada, Integer nota, Complexidade complexidade) {
		
		ProvaAluno provaAluno = new ProvaAluno();
		provaAluno.setGabaritoAluno(gabarito);
		provaAluno.setProvaFinalizada(provaFinalizada);
		provaAluno.setNota(nota);
		
		Prova prova = new Prova(1L, "Matemática", LongStream.range(1, 10).mapToObj(i -> {
			if(i != 2)
				return new Questao(i, "Lorem Ipslum", complexidade.getNormalized(), 'B', (int) i+1);
			return new Questao(i, "Lorem Ipslum", complexidade.getNormalized(), 'B', (int) i);
		}).collect(Collectors.toSet()));
		
		provaAluno.setProva(prova);
		return provaAluno;
		
	}
	
	
	
}
