package com.teste.sas.testeSas.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sas.testeSas.entity.Aluno;
import com.teste.sas.testeSas.entity.ProvaAluno;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.handler.BusinessExpection;
import com.teste.sas.testeSas.repository.AlunoRepository;
import com.teste.sas.testeSas.repository.ProvaAlunoRepository;
import com.teste.sas.testeSas.repository.SimuladoRepository;
import com.teste.sas.testeSas.service.impl.ProvaAlunoServiceImpl;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProvaAlunoServiceImplTest {

    @InjectMocks
    private ProvaAlunoServiceImpl provaAlunoService;
	
	@Mock
	private SimuladoRepository simuladoRepository;

	@Mock
	private AlunoRepository alunoRepository;
		
	@Mock
	ProvaAlunoRepository provaAlunoRepository;
		   
    @Test
    public void provaAlunoServiceExecuteTest(){

    	Simulado simulado = new Simulado();
    	simulado.setNomeSimulado("Ciências da Natureza");
    	simulado.setIdSimulado(1L);
    	simulado.setSimuladoEncerrado(true);
    	
    	Aluno aluno1 = new Aluno();
    	aluno1.setIdAluno(1L);
    	aluno1.setNomeAluno("Gustavo");
    	Aluno aluno2 = new Aluno();
    	aluno2.setIdAluno(2L);
    	aluno2.setNomeAluno("Carmen");
    	
    	List<ProvaAluno> provaAluno1 = Arrays.asList(new ProvaAluno(1L, aluno1, 550, true), new ProvaAluno(1L, aluno1, 200, true), new ProvaAluno(1L, aluno1, 100, true));
    	List<ProvaAluno> provaAluno2 = Arrays.asList(new ProvaAluno(2L, aluno2, 800, true), new ProvaAluno(2L, aluno2, 350, true), new ProvaAluno(2L, aluno2, 482, true));
    	    	
    	when(simuladoRepository.findById(any())).thenReturn(Optional.of(simulado));
    	when(alunoRepository.findBySimulado(any())).thenReturn(Arrays.asList(aluno1, aluno2));
    	when(provaAlunoRepository.findByAluno(aluno1)).thenReturn(provaAluno1);
    	when(provaAlunoRepository.findByAluno(aluno2)).thenReturn(provaAluno2);
    	when(alunoRepository.findByIdAluno(1L)).thenReturn(aluno1);
    	when(alunoRepository.findByIdAluno(2L)).thenReturn(aluno2);
     	
    	provaAlunoService.execute(1L);
    	
    	assertEquals(283, aluno1.getNotaFinal().intValue());
    	assertEquals(544, aluno2.getNotaFinal().intValue());

    	verify(simuladoRepository, times(1)).findById(any());
    	verify(alunoRepository, times(1)).findBySimulado(any());
    	verify(alunoRepository, times(2)).findByIdAluno(any());
    	verify(provaAlunoRepository, times(2)).findByAluno(any());
    	
    }
    
    @Test
    public void simuladoNaoEncontradoProvaAlunoServiceTest(){
    	
    	when(simuladoRepository.findById(any())).thenReturn(Optional.empty());
    
		try {
			provaAlunoService.execute(1L);
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE, e.getMessage());
			verify(simuladoRepository, times(1)).findById(any());
		}
    	 
    }
    
    @Test
    public void simuladoEmAndamentoProvaAlunoServiceTest(){
    	
    	Simulado simulado = new Simulado();
    	simulado.setSimuladoEncerrado(false);
    	
    	when(simuladoRepository.findById(any())).thenReturn(Optional.of(simulado));
    
		try {
			provaAlunoService.execute(1L);
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.SIMULADO_EM_ANDAMENTO_MESSAGE, e.getMessage());
			verify(simuladoRepository, times(1)).findById(any());
		}
    	 
    }

    
}
