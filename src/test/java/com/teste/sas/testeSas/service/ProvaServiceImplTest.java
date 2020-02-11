package com.teste.sas.testeSas.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sas.testeSas.dto.Complexidade;
import com.teste.sas.testeSas.dto.GabaritoDTO;
import com.teste.sas.testeSas.entity.Prova;
import com.teste.sas.testeSas.entity.Questao;
import com.teste.sas.testeSas.handler.BusinessExpection;
import com.teste.sas.testeSas.repository.ProvaRepository;
import com.teste.sas.testeSas.service.impl.ProvaServiceImpl;
import com.teste.sas.testeSas.utils.UtilsTesteSas;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProvaServiceImplTest {

	@Mock
	ProvaRepository provaRepository;
	
    @InjectMocks
    private ProvaServiceImpl provaService;
      
    @Test
    public void provaNaoEncontradaProvaServiceTest(){
    	
    	when(provaRepository.findById(any())).thenReturn(Optional.empty());
    
		try {
			provaService.execute(1L);
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE, e.getMessage());
			verify(provaRepository, times(1)).findById(any());
		}
    	 
    }
    
    @Test
    public void executeProvaServiceTest(){
    	
    	when(provaRepository.findById(any())).thenReturn(Optional.of(this.mockProva()));
            	
		GabaritoDTO gabaritoDto = provaService.execute(1L);
		
		assertEquals("Matemática", gabaritoDto.getNomeProva());
		assertEquals("Lorem Ipslum", gabaritoDto.getGabarito().get(2).getEnunciado());
		assertEquals('D', gabaritoDto.getGabarito().get(2).getAlternativaCorreta().charValue());
		assertEquals(3, gabaritoDto.getGabarito().get(2).getNumeroQuestao().intValue());
		assertEquals(Complexidade.MÉDIA.toString(), gabaritoDto.getGabarito().get(2).getNivel());
				
		verify(provaRepository, times(1)).findById(any());
		
    }
    
    private Prova mockProva() {
    	Set<Questao> questoes = new HashSet<Questao>(); 
    	questoes.add(new Questao(1L, "Lorem Ipslum", Complexidade.DIFÍCIL.toString(), 'B', 1));
    	questoes.add(new Questao(2L, "Lorem Ipslum", Complexidade.FÁCIL.toString(), 'A', 2));
    	questoes.add(new Questao(3L, "Lorem Ipslum", Complexidade.MÉDIA.toString(), 'D', 3));
    	return new Prova(1L, "Matemática", questoes);
    	
    }
    
}
