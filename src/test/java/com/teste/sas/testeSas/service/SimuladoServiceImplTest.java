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
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sas.testeSas.dto.SimuladoDTO;
import com.teste.sas.testeSas.entity.Prova;
import com.teste.sas.testeSas.entity.Simulado;
import com.teste.sas.testeSas.handler.BusinessExpection;
import com.teste.sas.testeSas.repository.SimuladoRepository;
import com.teste.sas.testeSas.service.impl.SimuladoServiceImpl;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class SimuladoServiceImplTest {

	@Mock
	SimuladoRepository simuladoRepository;	
	
	@InjectMocks
	SimuladoServiceImpl simuladoService;

	@Test
    public void simuladoServiceTest(){

		Integer quantidadeDeProvas = 6;
    	when(simuladoRepository.findById(any())).thenReturn(Optional.of(this.mockSimulados(quantidadeDeProvas)));
    	    	
    	SimuladoDTO simuladoDto = simuladoService.execute(1L);
		assertEquals("Ciências da Natureza", simuladoDto.getNomeSimulado());
		assertEquals(quantidadeDeProvas, simuladoDto.getQuantidadeDeProvas());
		verify(simuladoRepository, times(1)).findById(any());
		   	 
    }

	@Test
    public void simuladosServiceTest(){
		
		Integer quantidadeDeProvas = 2;
		Iterable<Simulado> simulados = IntStream.range(1, 3).mapToObj(quantidadeProvas -> {
			return this.mockSimulados(quantidadeProvas);
		}).collect(Collectors.toList());
		
    	when(simuladoRepository.findAll()).thenReturn(simulados);
    	    	
    	List<SimuladoDTO> simuladosDto = simuladoService.execute();
		assertEquals("Ciências da Natureza", simuladosDto.get(1).getNomeSimulado());
		assertEquals(quantidadeDeProvas, simuladosDto.get(1).getQuantidadeDeProvas());
		verify(simuladoRepository, times(1)).findAll();
		   	 
    }
	
    @Test
    public void simuladoNaoEncontradoSimuladoServiceTest(){
    	
    	when(simuladoRepository.findById(any())).thenReturn(Optional.empty());
    
		try {
			simuladoService.execute(1L);
		} catch (BusinessExpection e) {
			assertEquals(UtilsTesteSas.SIMULADO_NAO_ENCONTRADO_MESSAGE, e.getMessage());
			verify(simuladoRepository, times(1)).findById(any());
		}
    	 
    }
    
	private Simulado mockSimulados(Integer quantidadeDeProvas) {

		return new Simulado("Ciências da Natureza", LongStream.range(1, 1 + quantidadeDeProvas).mapToObj(i -> {
			return new Prova(i, "Prova " + i);
		}).collect(Collectors.toSet()));

	}

}
